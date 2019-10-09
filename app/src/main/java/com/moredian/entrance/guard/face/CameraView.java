package com.moredian.entrance.guard.face;

import android.content.Context;
import android.content.res.TypedArray;
import android.hardware.Camera;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.moredian.entrance.guard.R;


/**
 * Created by zjmantou on 2017/7/31.
 */

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = CameraView.class.getSimpleName();

    private static final int DEFAULT_CAMERA_PREVIEW_WIDTH = 720;
    private static final int DEFAULT_CAMERA_PREVIEW_HEIGHT = 1280;

    private static final int DEFAULT_CAMERA_PREVIEW_FORMAT = 15;

    private int mPreviewWidth = DEFAULT_CAMERA_PREVIEW_WIDTH;
    private int mPreviewHeight = DEFAULT_CAMERA_PREVIEW_HEIGHT;

    //private int mDisplayWidth = DEFAULT_CAMERA_DISPLAY_WIDTH;
    //private int mDisplayHeight = DEFAULT_CAMERA_DISPLAY_HEIGHT;

    private int mCameraId = -1;
    private Camera mCamera;
    private Camera.PreviewCallback mPreviewCallback;
    private int mDisplayDegree;

    private Camera.FaceDetectionListener mFaceDetectionListener;
    private boolean isStartFaceDetection;

    private SurfaceHolder mSurface;

    private HandlerThread mCameraThread = null;
    private Handler mCameraHandler = null;
    private boolean mHasOpened;

    public static int mVideoPreviewWidth = 720;
    public static int mVideoPreviewHeight = 1280;


    public CameraView(Context context) {
        this(context, null);
    }

    public boolean hasOpened() {
        return mHasOpened;
    }

    public CameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
        try {
            //获取mPreviewWidth，mPreviewHeight属性
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CameraView);
            this.mPreviewWidth = typedArray.getInteger(R.styleable.CameraView_previewwidth, DEFAULT_CAMERA_PREVIEW_WIDTH);
            this.mPreviewHeight = typedArray.getInteger(R.styleable.CameraView_previewheight, DEFAULT_CAMERA_PREVIEW_HEIGHT);
            typedArray.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过回调方法监听SurfaceView的生命周期
        getHolder().addCallback(this);
    }

    /**
     * descirption: 初始化mCameraId mDisplayDegree 俩接口对象
     */
    public void init(int cameraId, int displayDegree, Camera.PreviewCallback previewCallback, Camera.FaceDetectionListener faceDetectionListener) {
        this.mCameraId = cameraId;
        this.mDisplayDegree = displayDegree;
        this.mPreviewCallback = previewCallback;
        this.mFaceDetectionListener = faceDetectionListener;
        mVideoPreviewWidth = 720;
        mVideoPreviewHeight = 1280;
        /*if (DeviceType.isG2()) {
            mVideoPreviewWidth = 480;
            mVideoPreviewHeight = 800;
        } else if (DeviceType.isMg1()) {
                mVideoPreviewWidth = 720;
                mVideoPreviewHeight = 1280;
        } else {
            mVideoPreviewWidth = 1280;
            mVideoPreviewHeight = 720;
        }*/
    }


    private Runnable startCameraPreviewRunnable = new Runnable() {
        @Override
        public void run() {
            if (mSurface == null || mCamera != null || mCameraId == -1) {
                mCameraHandler.postDelayed(startCameraPreviewRunnable, 500);
                return;
            }
            final int pw = mPreviewWidth;
            final int ph = mPreviewHeight;
            mCamera = CameraUtil.openCamera(mCameraId, new CameraUtil.CameraWritter() {
                @Override
                public void onCameraCreate(Camera camera) {
                    Camera.Parameters parameters = camera.getParameters();
                    //取到最大的误差不大于10的Camera Size
                    Camera.Size size = CameraUtil.choosePreferredSize(parameters.getSupportedPreviewSizes(),
                            mPreviewWidth, mPreviewHeight);
                    parameters.setPreviewSize(size.width, size.height);
                    //获取不小于15 的最大帧率
                    int rate = CameraUtil.choosePreferredRate(
                            parameters.getSupportedPreviewFrameRates(),
                            DEFAULT_CAMERA_PREVIEW_FORMAT);
                    parameters.setPreviewFrameRate(rate);
                    camera.setParameters(parameters);
                    //根据displayDegree来调整获取要旋转的度数，保证正确显示
                    int orientation = CameraUtil.getSuitableCameraDisplayOrientation(mDisplayDegree, mCameraId);
                    camera.setDisplayOrientation(orientation);
                    mPreviewWidth = size.width;
                    mPreviewHeight = size.height;
                }
            });

            if (mCamera != null) {
                try {
                    if (mPreviewWidth != pw && mPreviewHeight != ph) {
                        mPreviewWidth = pw;
                        mPreviewHeight = ph;
                        requestLayout();
                    }

                    CameraUtil.startPreview(mCamera, mSurface);
                    mCamera.setPreviewCallback(mPreviewCallback);
                    //摄像头对焦成功后调用一次，上者会不停调用
                    //mCamera.setOneShotPreviewCallback(mPreviewCallback);

                    //增加人脸检测
                    if (CameraUtil.getMaxNumDetectedFaces(mCamera) > 0 && mFaceDetectionListener != null) {
                        isStartFaceDetection = true;
                        //开启人脸检测
                        mCamera.setFaceDetectionListener(mFaceDetectionListener);
                        mCamera.startFaceDetection();
                    }
                    mHasOpened = true;
                } catch (Throwable e) {
                    e.printStackTrace();
                    CameraUtil.closeCamera(mCamera);
                    mCamera = null;
                    //这里有空指针异常
                    if (mCameraHandler != null) {
                        mCameraHandler.postDelayed(startCameraPreviewRunnable, 500);
                    }
                }
            } else {
                if (mCameraHandler != null) {
                    mCameraHandler.postDelayed(startCameraPreviewRunnable, 500);
                }
            }
        }
    };

    private Runnable stopCameraPreviewRunnable = new Runnable() {
        @Override
        public void run() {
            if (mCamera != null) {
                if (isStartFaceDetection) {
                    try {
                        //关闭人脸检测
                        mCamera.setFaceDetectionListener(null);
                        mCamera.stopFaceDetection();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }

                CameraUtil.closeCamera(mCamera);
                isStartFaceDetection = false;
                mCamera = null;
            }
        }
    };

    /**
     * 开始预览
     */
    private void startCameraPreview() {
        if (mCameraHandler != null) {
            mCameraHandler.removeCallbacks(stopCameraPreviewRunnable);
            mCameraHandler.post(startCameraPreviewRunnable);
        }
    }

    /**
     * 停止摄像头预览
     */
    private void stopCameraPreview() {
        if (mCameraHandler != null) {
            mCameraHandler.removeCallbacks(startCameraPreviewRunnable);
            mCameraHandler.post(stopCameraPreviewRunnable);
        }
    }

    public void onPause() {
        stopCameraPreview();
    }

    public void onResume() {
        startCameraPreview();
    }

    /**
     * descirption: 开启消息机制
     */
    public void start() {
        if (mCameraHandler == null) {
            mCameraThread = new HandlerThread("CameraPreviewThread");
            mCameraThread.start();
            mCameraHandler = new Handler(mCameraThread.getLooper());
        }
    }

    /**
     * descirption: 关闭消息机制
     */
    public void stop() {
        if (mCameraThread != null) {
            mCameraThread.quit();
        }
        mCameraHandler = null;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSurface = holder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mSurface = null;
    }
}
