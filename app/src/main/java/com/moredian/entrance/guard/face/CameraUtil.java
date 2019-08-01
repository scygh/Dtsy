package com.moredian.entrance.guard.face;

import android.app.Activity;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zjmantou on 2017/7/31.
 */

public class CameraUtil {

    private static final String TAG = "CameraUtil";

    public static int getSuitableCameraDisplayOrientation(int displayDegree, int cameraId) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, cameraInfo);
        int orientation;
        if(cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            orientation = (cameraInfo.orientation + displayDegree) % 360;
            orientation = (360 - orientation) % 360;
        } else {
            orientation = (cameraInfo.orientation - displayDegree + 360) % 360;
        }

        return orientation;
    }

    public static Camera.Size choosePreferredSize(List<Camera.Size> sizes, int targetWidth, int targetHeight) {
        double aspectRatio = 1.0f * targetWidth / targetHeight;
        List<Camera.Size> options = new ArrayList<>();
        for (Camera.Size option : sizes) {
            if (option.width == targetWidth && option.height == targetHeight)
                return option;
            if (Math.abs((int) (option.height * aspectRatio) - option.width) < 10) {
                options.add(option);
            }
        }
        if (options.size() > 0) {
            return Collections.max(options, new CompareSizesByArea());
        } else {
            return sizes.get(sizes.size()-1);
        }
    }

    public static int choosePreferredRate(List<Integer> list, int targetRate) {
        List<Integer> supportRates = new ArrayList(list);
        //从小到大排序
        Collections.sort(supportRates, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        //支持帧率列表里是否包括要设置的值
        if(!supportRates.contains(targetRate)) {
            int max = supportRates.get(supportRates.size() - 1);
            if(max < targetRate) {
                targetRate = max;
            } else {
                for (int rate : supportRates) {
                    if (rate > targetRate) {
                        targetRate = rate;
                        break;
                    }
                }
            }
        }
        return targetRate;
    }

    public static int getBackCameraId() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();

        for(int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if(cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                Log.d(TAG, "back camera id :" + i);
                return i;
            }
        }
        return -1;
    }

    public static int getFrontCameraId() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();

        for(int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if(cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                Log.d(TAG, "front camera id :" + i);
                return i;
            }
        }
        return -1;
    }


    public static Camera openCamera(int cameraId, CameraWritter writter) {
        Camera camera = null;
        try {
            camera = Camera.open(cameraId);
            if(camera != null) {
                //FaceTrackManager.getInstance().initTrack(context);
                if(writter != null) {
                    writter.onCameraCreate(camera);
                }
            }
        } catch (Throwable e) {
            Log.e(TAG,"opencamera id:"+cameraId+",exp:"+e.getMessage());
            closeCamera(camera);
            camera = null;
        }
        return camera;
    }

    public static void closeCamera(Camera camera) {
        if(camera != null) {
            try {
                camera.stopPreview();
                camera.setPreviewCallback(null);
                camera.setPreviewCallbackWithBuffer(null);
                camera.release();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void startPreview(Camera camera, SurfaceHolder holder) throws Throwable {
        if (camera != null) {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        }
    }


    public static int getMaxNumDetectedFaces(Camera camera) {
        if(camera != null) {
            try {
                return camera.getParameters().getMaxNumDetectedFaces();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private static class CompareSizesByArea implements Comparator<Camera.Size> {
        @Override
        public int compare(Camera.Size lhs, Camera.Size rhs) {
            return Long.signum((long) lhs.width * lhs.height -
                    (long) rhs.width * rhs.height);
        }
    }

    public interface CameraWritter {
        void onCameraCreate(Camera camera);
    }


    public static int getRotation(Activity activity) {
        WindowManager manager = activity.getWindowManager();
        int rotation = manager.getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        return degrees;
    }
}
