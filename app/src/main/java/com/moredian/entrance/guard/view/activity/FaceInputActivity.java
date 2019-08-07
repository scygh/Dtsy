package com.moredian.entrance.guard.view.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.face.CameraUtil;
import com.moredian.entrance.guard.face.CameraView;
import com.moredian.entrance.guard.face.drawface.DrawerSurfaceView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FaceInputActivity extends AppCompatActivity {

    private static final String TAG = "FaceInputActivity";

    @BindView(R.id.camera_view)
    CameraView mRgbCameraView;
    @BindView(R.id.nir_camera)
    CameraView mNirCameraView;
    @BindView(R.id.faceView)
    DrawerSurfaceView mDrawFaceView;
    @BindView(R.id.nir_tips)
    ImageView mNirTipsView;
    @BindView(R.id.detect_tips_tv)
    TextView mDetectResultView;
    @BindView(R.id.iv_rgb)
    ImageView mRgbFaceView;
    @BindView(R.id.iv_nir)
    ImageView mNirFaceView;
    @BindView(R.id.persondetail_sure)
    Button beSure;
    @BindView(R.id.persondetail_cancle)
    Button beCancle;
    private byte[] rgb_data;
    private byte[] image;
    private static boolean mShowCallbackFace = true;
    private static int mCheckRgbCameraOpenCount = 0;
    private MyReceiver mReceiver = new MyReceiver();
    private static final int KEY_DETECT_HIDE = 0;
    private static final int KEY_DETECT_FACE_SIZE_ERROR = 1;
    private static final int KEY_DETECT_FACE_ANGLE_ERROR = 2;
    private static final int KEY_DETECT_FACE_QUALITY_ERROR = 3;
    private static final int KEY_DETECT_USER_NAME = 4;

    private static final int KEY_OPEN_NIR_CAMERA = 1000;
    private static final int OPEN_MIR_CAMERA_DELAY = 3 * 1000;

    private static final String SIZE_INCORRECT_TIPS = "请让我看清全部的脸";
    private static final String ANGLE_INCORRECT_TIPS = "请摆正脸";
    private static final String QUALITY_INCORRECT_TIPS = "请保持静止";

    private static final String DETECT_RESULT_ACTION = "com.moredian.facetrack.detectResult";
    private static final String NIR_RESULT_ACTION = "com.moredian.facetrack.nirResult";
    private static final String OFFLINE_RECOGNIZE_ACTION = "com.moredian.facetrack.offLineRecognize";
    private static final String ONLINE_RECOGNIZE_ACTION = "com.moredian.facetrack.onLineRecognize";

    private static final String CHECK_STATUS = "status";
    private static final String CHECK_FAIL_REASON = "failed_reason";
    private static final String RGB_DATA = "rgb_data";
    private static final String NIR_DATA = "nir_data";
    private static final String FACE_COUNT = "face_count";
    private static final String TRACK_ID = "track_id";
    private static final String USER_NAME = "user_name";
    private static final String PERSON_ID = "memberID";

    private static final String DETECT_FAIL_REASON_NOFACE = "detect_no_face";
    private static final String DETECT_FAIL_REASON_FACE_SIZE_INCORRECT = "detect_size_incorrect";
    private static final String DETECT_FAIL_REASON_FACE_ANGLE_INCORRECT = "detect_angle_incorrect";
    private static final String DETECT_FAIL_REASON_FACE_QUALITY_TOO_LOW = "detect_quality_too_low";

    private static final String NIR_DETECT_FAIL_REASON = "track_fmp_fail";

    private static final String RECOGNIZE_FAIL_REASON1 = "track_id_has_no_state";
    private static final String RECOGNIZE_FAIL_REASON2 = "track_id_has_no_local_userid";
    private static final String RECOGNIZE_FAIL_REASON3 = "track_id_has_no_online_response";

    public static Intent getFaceInputActivityIntent(Context context) {
        Intent intent = new Intent(context, FaceInputActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_face_input);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        initCamera();
        initReceiver();
    }

    private void initReceiver() {
        IntentFilter intentFilter1 = new IntentFilter(DETECT_RESULT_ACTION);
        IntentFilter intentFilter2 = new IntentFilter(NIR_RESULT_ACTION);
        IntentFilter intentFilter3 = new IntentFilter(OFFLINE_RECOGNIZE_ACTION);
        IntentFilter intentFilter4 = new IntentFilter(ONLINE_RECOGNIZE_ACTION);
        registerReceiver(mReceiver, intentFilter1);
        registerReceiver(mReceiver, intentFilter2);
        registerReceiver(mReceiver, intentFilter3);
        registerReceiver(mReceiver, intentFilter4);
    }

    private void initCamera() {
        int display_degree = CameraUtil.getRotation(this);
        if (mRgbCameraView != null) {
            mRgbCameraView.init(CameraUtil.getBackCameraId(), display_degree, previewCallback, faceDetectionListener);
            mRgbCameraView.requestLayout();
            mRgbCameraView.start();
        }
        if (mNirCameraView != null) {
            mNirCameraView.init(CameraUtil.getFrontCameraId(), display_degree, nirPreviewCallback, null);
            mNirCameraView.requestLayout();
            mNirCameraView.start();
        }
    }

    private Camera.PreviewCallback previewCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
        }
    };

    private Camera.FaceDetectionListener faceDetectionListener = new Camera.FaceDetectionListener() {
        @Override
        public void onFaceDetection(final Camera.Face[] faces, Camera camera) {
            if (mDrawFaceView != null) {
                mDrawFaceView.setFaces(faces);
            }
        }
    };

    private Camera.PreviewCallback nirPreviewCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
        }
    };

    /**
     * descirption: 先开启mRgbCameraView ，后开启mNirCameraView，如果后者不成功则不断请求开启
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onresume");
        if (mRgbCameraView != null) {
            mRgbCameraView.onResume();
        }
        if (mNirCameraView != null) {
            if (mRgbCameraView.hasOpened()) {
                mNirCameraView.onResume();
            } else {
                mHandler.sendEmptyMessageDelayed(KEY_OPEN_NIR_CAMERA, OPEN_MIR_CAMERA_DELAY);
            }
        }
    }

    /**
     * descirption: mNirTipsView恢复默认 ，两个Cameraview 暂停
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        if (mNirTipsView != null) {
            mNirTipsView.setBackground(getResources().getDrawable(R.drawable.shap_nir_tip_default));
        }
        if (mRgbCameraView != null) {
            mRgbCameraView.onPause();
        }
        if (mNirCameraView != null) {
            mNirCameraView.onPause();
        }
    }

    /**
     * descirption: 恢复初始，解绑广播
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        if (mRgbCameraView != null) {
            mRgbCameraView.stop();
            mRgbCameraView = null;
        }
        if (mNirCameraView != null) {
            mNirCameraView.stop();
            mNirCameraView = null;
        }
        try {
            unregisterReceiver(mReceiver);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //如果人脸识别已打开，红外生物识别获取焦点，如果没打开则延迟重来
                case KEY_OPEN_NIR_CAMERA:
                    if (mRgbCameraView.hasOpened()) {
                        mNirCameraView.onResume();
                    } else {
                        mCheckRgbCameraOpenCount++;
                        mHandler.sendEmptyMessageDelayed(KEY_OPEN_NIR_CAMERA, OPEN_MIR_CAMERA_DELAY + mCheckRgbCameraOpenCount * 1000);
                    }
                    break;
                //隐藏显示的文字
                case KEY_DETECT_HIDE:
                    mHandler.removeMessages(KEY_DETECT_HIDE);
                    Log.e(TAG, "hide detect result");
                    if (mDetectResultView.getVisibility() != View.GONE) {
                        mDetectResultView.setVisibility(View.GONE);
                    }
                    break;
                //靠近一点提醒，后隐藏
                case KEY_DETECT_FACE_SIZE_ERROR:
                    mHandler.removeMessages(KEY_DETECT_FACE_SIZE_ERROR);
                    Log.e(TAG, "face size incorrect");
                    if (!SIZE_INCORRECT_TIPS.equals(mDetectResultView.getText())) {
                        mDetectResultView.setText(SIZE_INCORRECT_TIPS);
                    }
                    if (mDetectResultView.getVisibility() != View.VISIBLE) {
                        mDetectResultView.setVisibility(View.VISIBLE);
                    }
                    mHandler.sendEmptyMessageDelayed(KEY_DETECT_HIDE, 2000);
                    break;
                //摆正提醒，后隐藏
                case KEY_DETECT_FACE_ANGLE_ERROR:
                    mHandler.removeMessages(KEY_DETECT_FACE_ANGLE_ERROR);
                    Log.e(TAG, "face angle incorrect");
                    if (!ANGLE_INCORRECT_TIPS.equals(mDetectResultView.getText())) {
                        mDetectResultView.setText(ANGLE_INCORRECT_TIPS);
                    }
                    if (mDetectResultView.getVisibility() != View.VISIBLE) {
                        mDetectResultView.setVisibility(View.VISIBLE);
                    }
                    mHandler.sendEmptyMessageDelayed(KEY_DETECT_HIDE, 2000);
                    break;
                //提醒保持静止提醒，后隐藏
                case KEY_DETECT_FACE_QUALITY_ERROR:
                    mHandler.removeMessages(KEY_DETECT_FACE_QUALITY_ERROR);
                    Log.e(TAG, "face quality incorrect");
                    if (!QUALITY_INCORRECT_TIPS.equals(mDetectResultView.getText())) {
                        mDetectResultView.setText(QUALITY_INCORRECT_TIPS);
                    }
                    if (mDetectResultView.getVisibility() != View.VISIBLE) {
                        mDetectResultView.setVisibility(View.VISIBLE);
                    }
                    mHandler.sendEmptyMessageDelayed(KEY_DETECT_HIDE, 2000);
                    break;
                //显示识别获取的名字后隐藏
                case KEY_DETECT_USER_NAME:
                    mHandler.removeMessages(KEY_DETECT_USER_NAME);
                    String username = msg.getData().getString(USER_NAME);
                    Log.e(TAG, "show username：" + username);
                    if (!username.equals(mDetectResultView.getText())) {
                        mDetectResultView.setText(username);
                    }
                    if (mDetectResultView.getVisibility() != View.VISIBLE) {
                        mDetectResultView.setVisibility(View.VISIBLE);
                    }
                    mHandler.sendEmptyMessageDelayed(KEY_DETECT_HIDE, 2000);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @OnClick({R.id.persondetail_sure, R.id.persondetail_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.persondetail_sure:
                if (image != null) {
                    // TODO: 2019/8/6 处理图片质量

                    Intent intent = new Intent();
                    intent.putExtra(Constants.INTENT_FACEINPUT_RGBDATA, image);
                    setResult(Constants.FACE_INPUT_RESULTCODE, intent);
                    finish();
                } else {
                    ToastUtils.showShort("录入失败，请重新录入人脸");
                }
                break;
            case R.id.persondetail_cancle:
                finish();
                break;
        }
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //判断是否成功
            if (action != null && !action.equals("")) {
                boolean status = intent.getBooleanExtra(CHECK_STATUS, false);
                rgb_data = intent.getByteArrayExtra(RGB_DATA);
                // 彩色摄像头抓到的人脸
                if (rgb_data != null && rgb_data.length > 0) {
                    Bitmap rgbBitmap = BitmapFactory.decodeByteArray(rgb_data, 0, rgb_data.length);
                    mRgbFaceView.setImageBitmap(rgbBitmap);
                    if (mShowCallbackFace) {
                        mRgbFaceView.setVisibility(View.VISIBLE);
                    }
                }
                //红外摄像头抓到的人脸
                byte[] nir_data = intent.getByteArrayExtra(NIR_DATA);
                if (nir_data != null && nir_data.length > 0) {
                    Bitmap rgbBitmap = BitmapFactory.decodeByteArray(nir_data, 0, nir_data.length);
                    mNirFaceView.setImageBitmap(rgbBitmap);
                    if (mShowCallbackFace) {
                        mNirFaceView.setVisibility(View.VISIBLE);
                    }
                }
                //如果获取失败则做相应的处理
                String reason = "";
                if (!status) {
                    reason = intent.getStringExtra(CHECK_FAIL_REASON);
                    Log.d(TAG, "receive:" + action + ",status:  " + status + ",reason  :" + reason);
                    //人脸质量是否合格
                    if (action.equals(DETECT_RESULT_ACTION)) {
                        if (reason.contains(DETECT_FAIL_REASON_FACE_SIZE_INCORRECT)) {
                            mHandler.sendEmptyMessage(KEY_DETECT_FACE_SIZE_ERROR);
                        } else if (reason.contains(DETECT_FAIL_REASON_NOFACE)) {
                            mNirTipsView.setBackground(getResources().getDrawable(R.drawable.shap_nir_tip_default));
                        } else if (reason.contains(DETECT_FAIL_REASON_FACE_ANGLE_INCORRECT)) {
                            mHandler.sendEmptyMessage(KEY_DETECT_FACE_ANGLE_ERROR);
                        } else if (reason.contains(DETECT_FAIL_REASON_FACE_QUALITY_TOO_LOW)) {
                            mHandler.sendEmptyMessage(KEY_DETECT_FACE_QUALITY_ERROR);
                        }
                        //是否是活体
                    } else if (action.equals(NIR_RESULT_ACTION)) {
                        mNirTipsView.setBackground(getResources().getDrawable(R.drawable.shap_nir_tip_fail));
                    }
                } else {
                    //人脸质量是否合格
                    if (action.equals(DETECT_RESULT_ACTION)) {
                        int facecount = intent.getIntExtra(FACE_COUNT, 0);
                        String trackids = "";
                        long trackid = 0l;
                        if (facecount > 0) {
                            for (int i = 0; i < facecount; i++) {
                                trackid = intent.getLongExtra(TRACK_ID + i, 0l);
                                trackids = trackids + "," + trackid;
                            }
                        }
                    } else if (action.equals(NIR_RESULT_ACTION)) {
                        long trackid = intent.getLongExtra(TRACK_ID, 0l);
                        image = rgb_data;
                        mNirTipsView.setBackground(getResources().getDrawable(R.drawable.shap_nir_tip_succ));
                    } else if (action.equals(OFFLINE_RECOGNIZE_ACTION) || action.equals(ONLINE_RECOGNIZE_ACTION)) {
                        long trackid = intent.getLongExtra(TRACK_ID, 0l);
                        String username = intent.getStringExtra(USER_NAME);
                        String menberId = intent.getStringExtra(PERSON_ID);
                        Message msg = new Message();
                        msg.what = KEY_DETECT_USER_NAME;
                        Bundle b = new Bundle();
                        b.putString(USER_NAME, username + "<" + menberId + ">");
                        msg.setData(b);
                        mHandler.sendMessage(msg);
                    }
                }
            }
        }
    }
}
