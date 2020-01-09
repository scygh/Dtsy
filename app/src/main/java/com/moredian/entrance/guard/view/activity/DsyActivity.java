package com.moredian.entrance.guard.view.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.blankj.utilcode.util.SPUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.app.MainApplication;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.FaceExpense;
import com.moredian.entrance.guard.entity.GetMealList;
import com.moredian.entrance.guard.entity.PostFaceExpenseBody;
import com.moredian.entrance.guard.face.CameraUtil;
import com.moredian.entrance.guard.face.CameraView;
import com.moredian.entrance.guard.face.drawface.DrawerSurfaceView;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.utils.AudioUtils;
import com.moredian.entrance.guard.utils.ToastHelper;
import com.moredian.entrance.guard.view.adapter.MealPagerAdapter;
import com.moredian.entrance.guard.view.designview.ComonDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/16 17:43
 */
public class DsyActivity extends BaseActivity {

    private static final String TAG = "DsyActivity";

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
    @BindView(R.id.tv_pattern_title)
    TextView tvPatternTitle;
    @BindView(R.id.tv_pattern)
    TextView tvPattern;
    @BindView(R.id.tv_money)
    EditText tvMoney;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_paycount)
    TextView tvPaycount;
    @BindView(R.id.rl_data)
    RelativeLayout rlData;
    @BindView(R.id.meal_viewpager)
    ViewPager mealViewpager;
    @BindView(R.id.dsy_refresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.dsy_switch)
    Switch able_switch;
    @BindView(R.id.main_menu)
    Button mainMenu;
    private boolean ableTag = true;
    private byte[] rgb_data;
    private String memberId;
    private static boolean mShowCallbackFace = false;
    private static int mCheckRgbCameraOpenCount = 0;
    private MyReceiver mReceiver = new MyReceiver();
    private String username;
    private List<GetMealList.ContentBean> datas = new ArrayList<>();
    private List<String[]> times = new ArrayList<>();
    MealPagerAdapter adapter;
    private LoadingDailog dialog;
    private boolean isEnterPressed = false;
    //private GestureDetector gestureDetector;


    public static Intent getDsyActivityIntent(Context context) {
        Intent intent = new Intent(context, DsyActivity.class);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.activity_dsy;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int e = event.getKeyCode();
        //确认金额
        if (e == KeyEvent.KEYCODE_ENTER) {
            String money = tvMoney.getText().toString();
            if (!money.equals("刷脸支付") && !money.equals("")) {
                if (Double.parseDouble(money) > 0) {
                    isEnterPressed = true;
                    tvMoney.setTextColor(getResources().getColor(R.color.colorPrimary));
                    if (p == 1) {
                        tvMoney.setEnabled(false);
                    }
                    AudioUtils.getInstance().speakText("请识别人脸");
                }
            }
        }
        //键盘清空时，需要重新确认金额
        if (e == KeyEvent.KEYCODE_DEL) {
            if (p == 1) {
                tvMoney.setEnabled(true);
            }
            if (TextUtils.isEmpty(tvMoney.getText())) {
                tvMoney.setTextColor(getResources().getColor(R.color.color_grey));
                isEnterPressed = false;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (!TextUtils.isEmpty(pattern)) {
            tvPattern.setText(pattern);
            if (pattern.equals("自动消费")) {
                tvMoney.setText(SPUtils.getInstance().getFloat(Constants.AUTO_AMOUNT) + "");
            }
        }
        if (p == 3) {
            rlData.setVisibility(View.GONE);
            mealViewpager.setVisibility(View.VISIBLE);
            api.getMealList(token);
        }
        if (p == 1) {
            tvMoney.setEnabled(true);
        }
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (p == 3) {
                    api.getMealList(token);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        able_switch.setChecked(true);
        able_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ableTag = true;
                } else {
                    ableTag = false;
                }
            }
        });
    }

    @Override
    public void initData() {
        AudioUtils.getInstance().init(getApplicationContext());
        initReceiver();
        initRequest();
        initCamera();
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(this)
                .setMessage("初始化...")
                .setCancelable(true)
                .setCancelOutside(true);
        dialog = loadBuilder.create();
        dialog.show();
       /* gestureDetector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                ToastHelper.showToast("您确定要退出吗");
                MainApplication.getSerialPortUtils().closeSerialPort();
                finish();
                return super.onDoubleTap(e);
            }
        });*/
    }

    /**
     * descirption: 初始化相机
     */
    private void initCamera() {
        int display_degree = CameraUtil.getRotation(DsyActivity.this);
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

    /**
     * descirption: 初始化广播
     */
    private void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.DETECT_RESULT_ACTION);
        filter.addAction(Constants.NIR_RESULT_ACTION);
        filter.addAction(Constants.OFFLINE_RECOGNIZE_ACTION);
        filter.addAction(Constants.ONLINE_RECOGNIZE_ACTION);
        registerReceiver(mReceiver, filter);
    }

    /**
     * descirption: 初始化请求返回
     */
    private void initRequest() {
        api.setGetResponseListener(new Api.GetResponseListener() {
            @Override
            public void onRespnse(Object o) {
                //{"Content":{"ExpenseDetail":{"Id":"00000000-0000-0000-0000-000000000000","UserId":"48aa1ec4-3dda-403c-b1a8-904532123fbe","Number":120,"DeviceType":2,"DeviceId":10000,"Pattern":2,"DetailType":0,"PayCount":46,"Finance":0,"OriginalAmount":1.0,"Amount":1.0,"Balance":9975.75,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"0001-01-01 00:00:00","CreateTime":"0001-01-01 00:00:00","Description":"扣款合计1.0元;账户合计扣款1.0元;账户余额合计9975.75元.","OfflinePayCount":null},"ListEMGoodsDetail":null,"ThirdPartyExpense":null,"TradingState":0},"Result":true,"Message":"Success!","StatusCode":200}
                if (o instanceof FaceExpense) {//人脸消费
                    int fin = ((FaceExpense) o).getContent().getExpenseDetail().getFinance();
                    if (fin == 2 || fin == 3 || fin == 4) {
                        AudioUtils.getInstance().speakText("计次卡消费" + (int) ((FaceExpense) o).getContent().getExpenseDetail().getAmount() + "次");
                    } else {
                        AudioUtils.getInstance().speakText("支付" + ((FaceExpense) o).getContent().getExpenseDetail().getAmount() + "元");
                    }
                    startActivity(ConsumeResultActivity.getFaceConsumeSuccessActivityIntent(DsyActivity.this, ((FaceExpense) o).getContent()));
                    if (p != 3) {
                        updateText(((FaceExpense) o).getContent().getExpenseDetail().getUserId(), ((FaceExpense) o).getContent().getExpenseDetail().getBalance(), ((FaceExpense) o).getContent().getExpenseDetail().getPayCount());
                    }
                } else if (o instanceof GetMealList) {
                    datas.clear();
                    datas.addAll(((GetMealList) o).getContent());
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    } else {
                        adapter = new MealPagerAdapter(datas, DsyActivity.this);
                        mealViewpager.setAdapter(adapter);
                        mealViewpager.setPageMargin(15);
                        //mealViewpager.setPageTransformer(false, new LoopTransformer());
                        mealViewpager.setOffscreenPageLimit(datas.size());
                    }
                    setDefiCurrentItem((GetMealList) o);
                }
            }

            @Override
            public void onFail(String err) {
                AudioUtils.getInstance().speakText(err);
                mHandler.sendEmptyMessageDelayed(Constants.KEY_CLEAR, 3000);
                startActivity(ConsumeResultActivity.getConsumeFailActivityIntent(DsyActivity.this));
            }
        });
    }

    private boolean leftTop = false;
    private boolean rightTop = false;
    private boolean rightBottom = false;
    private boolean leftBpttom = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (x < 200 && y < 200) {
            leftTop = true;
            Log.d(TAG, "leftTop" + x + "，" + y);
        } else if (x > 520 && y < 200) {
            rightTop = true;
            Log.d(TAG, "rightTop" + x + "，" + y);
        } else if (x > 520 && y > 1080) {
            rightBottom = true;
            Log.d(TAG, "rightBottom" + x + "，" + y);
        } else if (x < 200 && y > 1080) {
            leftBpttom = true;
            Log.d(TAG, "leftBpttom" + x + "，" + y);
        }
        if (leftTop && leftBpttom && rightBottom && rightTop) {
            MainApplication.getSerialPortUtils().closeSerialPort();
            finish();
        }
        return super.onTouchEvent(event);
    }

    /**
     * descirption: 跳转到当前Pager
     */
    private void setDefiCurrentItem(GetMealList o) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentDate = format.format(date).substring(0, 11);
        String currentTime = format.format(date);
        times.clear();
        for (int i = 0; i < datas.size(); i++) {
            String begintime = currentDate + o.getContent().get(i).getBeginTime();
            String endtime = currentDate + o.getContent().get(i).getEndTime();
            String[] timearr = new String[]{begintime, endtime};
            times.add(timearr);
        }
        try {
            for (int j = 0; j < times.size(); j++) {
                if (format.parse(times.get(j)[0]).getTime() > format.parse(times.get(j)[1]).getTime()) {
                    mealViewpager.setCurrentItem(times.size() - 1);
                    adapter.setCurrentPosition(times.size() - 1);
                    break;
                }
                if (format.parse(times.get(j)[0]).getTime() <= format.parse(currentTime).getTime() && format.parse(times.get(j)[1]).getTime() >= format.parse(currentTime).getTime()) {
                    mealViewpager.setCurrentItem(j);
                    adapter.setCurrentPosition(j);
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * descirption: 更新数据
     */
    private void updateText(String name, double balance, int paycount) {
        isEnterPressed = false;
        if (p == 1) {
            tvMoney.setEnabled(true);
        }
        Message message = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putDouble("balance", balance);
        bundle.putInt("paycount", paycount);
        message.setData(bundle);
        message.what = Constants.KEY_SET_TEXT;
        mHandler.sendMessage(message);
        mHandler.sendEmptyMessageDelayed(Constants.KEY_CLEAR, 3000);
    }

    /**
     * descirption: 人脸消费
     */
    private void faceConsume() {
        if (!TextUtils.isEmpty(memberId)) {
            if (ableTag) {
                if (p == 3) {
                    AudioUtils.getInstance().speakText("请确认支付金额");
                    initDialog("0");
                } else {
                    String money = tvMoney.getText().toString();
                    if (!money.equals("刷脸支付") && !money.equals("")) {
                        if (Double.parseDouble(money) > 0) {
                            if (p == 1) {
                                if (isEnterPressed) {
                                    PostFaceExpenseBody postFaceExpenseBody = new PostFaceExpenseBody(memberId, Double.parseDouble(money), p, Integer.parseInt(deviceId), 2);
                                    api.postFaceExpense(postFaceExpenseBody, token, Constants.MODIAN_TOKEN);
                                } else {
                                    AudioUtils.getInstance().speakText("请确认支付金额");
                                }
                            } else {
                                AudioUtils.getInstance().speakText("请确认支付金额");
                                initDialog(money);
                            }
                        } else {
                            ToastHelper.showToast("金额必须大于零才能支付");
                        }
                    } else {
                        ToastHelper.showToast("请输入金额");
                    }
                }
            } else {
                ToastHelper.showToast("请先打开消费开关");
            }
        } else {
            ToastHelper.showToast("人脸未录入");
        }
    }

    private ComonDialog comonDialog;

    private void initDialog(String money) {
        if (comonDialog != null) {
            return;
        }
        comonDialog = new ComonDialog(DsyActivity.this);
        if (p == 2) {
            comonDialog.setMessage("已识别到人脸，请您确认支付：自动消费" + money + "元。");
        } else {
            comonDialog.setMessage("已识别到人脸，请您确认支付：定值消费。");
        }
        comonDialog.setTitle("支付提示")
                .setSingle(false).setOnClickBottomListener(new ComonDialog.OnClickBottomListener() {
            @Override
            public void onPositiveClick() {
                comonDialog.dismiss();
                if (p == 2) {
                    PostFaceExpenseBody postFaceExpenseBody = new PostFaceExpenseBody(memberId, Double.parseDouble(money), p, Integer.parseInt(deviceId), 2);
                    api.postFaceExpense(postFaceExpenseBody, token, Constants.MODIAN_TOKEN);
                } else {
                    PostFaceExpenseBody postFaceExpenseBody = new PostFaceExpenseBody(memberId, 0, p, Integer.parseInt(deviceId), 2);
                    api.postFaceExpense(postFaceExpenseBody, token, Constants.MODIAN_TOKEN);
                }
                comonDialog = null;
            }

            @Override
            public void onNegtiveClick() {
                comonDialog.dismiss();
                comonDialog = null;
            }
        }).show();
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
        if (mRgbCameraView != null) {
            mRgbCameraView.onResume();
        }
        if (mNirCameraView != null) {
            if (mRgbCameraView.hasOpened()) {
                mNirCameraView.onResume();
            } else {
                mHandler.sendEmptyMessageDelayed(Constants.KEY_OPEN_NIR_CAMERA, Constants.OPEN_MIR_CAMERA_DELAY);
            }
        }
        leftTop = false;
        rightTop = false;
        rightBottom = false;
        leftBpttom = false;
    }

    /**
     * descirption: mNirTipsView恢复默认 ，两个Cameraview 暂停
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
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

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter = null;
        }
    }

    /**
     * descirption: 恢复初始，解绑广播,关闭串口
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
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
        mHandler.removeCallbacksAndMessages(null);
        if (dialog != null) {
            dialog.cancel();
        }
        ableTag = true;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.KEY_CLEAR:
                    tvUsername.setText("");
                    tvUsername.setHint("请先识别");
                    tvBalance.setText("");
                    tvBalance.setHint("请先识别");
                    tvPaycount.setText("");
                    tvPaycount.setHint("请先识别");
                    if (p == 1 || p == 2) {
                        tvMoney.setTextColor(getResources().getColor(R.color.color_grey));
                    }
                    if (p == 1) {
                        tvMoney.setText("");
                        tvMoney.setHint("刷脸支付");
                    }
                    break;
                case Constants.KEY_SET_TEXT:
                    String name = msg.getData().getString("name");
                    double balance = msg.getData().getDouble("balance");
                    int paycount = msg.getData().getInt("paycount");
                    tvUsername.setText(name.substring(0, 10));
                    tvBalance.setText(balance + "元");
                    tvPaycount.setText(paycount + "次");
                    break;
                //如果人脸识别已打开，红外生物识别获取焦点，如果没打开则延迟重来
                case Constants.KEY_OPEN_NIR_CAMERA:
                    if (mRgbCameraView != null) {
                        if (mRgbCameraView.hasOpened()) {
                            mNirCameraView.onResume();
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                }
                            }, 2000);
                        } else {
                            mCheckRgbCameraOpenCount++;
                            mHandler.sendEmptyMessageDelayed(Constants.KEY_OPEN_NIR_CAMERA, Constants.OPEN_MIR_CAMERA_DELAY + mCheckRgbCameraOpenCount * 1000);
                        }
                    }
                    break;
                //隐藏显示的文字
                case Constants.KEY_DETECT_HIDE:
                    mHandler.removeMessages(Constants.KEY_DETECT_HIDE);
                    Log.e(TAG, "hide detect result");
                    if (mDetectResultView.getVisibility() != View.GONE) {
                        mDetectResultView.setVisibility(View.GONE);
                    }
                    break;
                //靠近一点提醒，后隐藏
                case Constants.KEY_DETECT_FACE_SIZE_ERROR:
                    mHandler.removeMessages(Constants.KEY_DETECT_FACE_SIZE_ERROR);
                    Log.e(TAG, "face size incorrect");
                    if (!Constants.SIZE_INCORRECT_TIPS.equals(mDetectResultView.getText())) {
                        mDetectResultView.setText(Constants.SIZE_INCORRECT_TIPS);
                    }
                    if (mDetectResultView.getVisibility() != View.VISIBLE) {
                        mDetectResultView.setVisibility(View.VISIBLE);
                    }
                    mHandler.sendEmptyMessageDelayed(Constants.KEY_DETECT_HIDE, 2000);
                    break;
                //摆正提醒，后隐藏
                case Constants.KEY_DETECT_FACE_ANGLE_ERROR:
                    mHandler.removeMessages(Constants.KEY_DETECT_FACE_ANGLE_ERROR);
                    Log.e(TAG, "face angle incorrect");
                    if (!Constants.ANGLE_INCORRECT_TIPS.equals(mDetectResultView.getText())) {
                        mDetectResultView.setText(Constants.ANGLE_INCORRECT_TIPS);
                    }
                    if (mDetectResultView.getVisibility() != View.VISIBLE) {
                        mDetectResultView.setVisibility(View.VISIBLE);
                    }
                    mHandler.sendEmptyMessageDelayed(Constants.KEY_DETECT_HIDE, 2000);
                    break;
                //提醒保持静止提醒，后隐藏
                case Constants.KEY_DETECT_FACE_QUALITY_ERROR:
                    mHandler.removeMessages(Constants.KEY_DETECT_FACE_QUALITY_ERROR);
                    Log.e(TAG, "face quality incorrect");
                    if (!Constants.QUALITY_INCORRECT_TIPS.equals(mDetectResultView.getText())) {
                        mDetectResultView.setText(Constants.QUALITY_INCORRECT_TIPS);
                    }
                    if (mDetectResultView.getVisibility() != View.VISIBLE) {
                        mDetectResultView.setVisibility(View.VISIBLE);
                    }
                    mHandler.sendEmptyMessageDelayed(Constants.KEY_DETECT_HIDE, 2000);
                    break;
                //显示识别获取的名字后隐藏
                case Constants.KEY_DETECT_USER_NAME:
                    mHandler.removeMessages(Constants.KEY_DETECT_USER_NAME);
                    String username = msg.getData().getString(Constants.USER_NAME);
                    if (!username.equals(mDetectResultView.getText())) {
                        mDetectResultView.setText(username);
                    }
                    if (mDetectResultView.getVisibility() != View.VISIBLE) {
                        mDetectResultView.setVisibility(View.VISIBLE);
                    }
                    mHandler.sendEmptyMessageDelayed(Constants.KEY_DETECT_HIDE, 2000);
                    faceConsume();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @OnClick({R.id.main_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_menu:
                startActivity(MainActivity.getMainActivityIntent(DsyActivity.this));
                finish();
                break;
        }
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //判断是否有信息
            if (action != null && !action.equals("")) {
                boolean status = intent.getBooleanExtra(Constants.CHECK_STATUS, false);
                rgb_data = intent.getByteArrayExtra(Constants.RGB_DATA);
                // 彩色摄像头抓到的人脸
                if (rgb_data != null && rgb_data.length > 0) {
                    Bitmap rgbBitmap = BitmapFactory.decodeByteArray(rgb_data, 0, rgb_data.length);
                    mRgbFaceView.setImageBitmap(rgbBitmap);
                    if (mShowCallbackFace) {
                        mRgbFaceView.setVisibility(View.VISIBLE);
                    }
                }
                //红外摄像头抓到的人脸
                byte[] nir_data = intent.getByteArrayExtra(Constants.NIR_DATA);
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
                    reason = intent.getStringExtra(Constants.CHECK_FAIL_REASON);
                    Log.d(TAG, "receive:" + action + ",status:  " + status + ",reason  :" + reason);
                    //人脸质量不合格
                    if (action.equals(Constants.DETECT_RESULT_ACTION)) {
                        if (reason.contains(Constants.DETECT_FAIL_REASON_FACE_SIZE_INCORRECT)) {
                            mHandler.sendEmptyMessage(Constants.KEY_DETECT_FACE_SIZE_ERROR);
                        } else if (reason.contains(Constants.DETECT_FAIL_REASON_NOFACE)) {
                            mNirTipsView.setBackground(getResources().getDrawable(R.drawable.shap_nir_tip_default));
                        } else if (reason.contains(Constants.DETECT_FAIL_REASON_FACE_ANGLE_INCORRECT)) {
                            mHandler.sendEmptyMessage(Constants.KEY_DETECT_FACE_ANGLE_ERROR);
                        } else if (reason.contains(Constants.DETECT_FAIL_REASON_FACE_QUALITY_TOO_LOW)) {
                            mHandler.sendEmptyMessage(Constants.KEY_DETECT_FACE_QUALITY_ERROR);
                        }
                        //是否是活体
                    } else if (action.equals(Constants.NIR_RESULT_ACTION)) {
                        mNirTipsView.setBackground(getResources().getDrawable(R.drawable.shap_nir_tip_fail));
                    }
                } else {
                    //人脸质量合格
                    if (action.equals(Constants.DETECT_RESULT_ACTION)) {
                        int facecount = intent.getIntExtra(Constants.FACE_COUNT, 0);
                        String trackids = "";
                        long trackid = 01;
                        if (facecount > 0) {
                            for (int i = 0; i < facecount; i++) {
                                trackid = intent.getLongExtra(Constants.TRACK_ID + i, 0l);
                                trackids = trackids + "," + trackid;
                            }
                        }
                        Log.d("ascb", "DETECT_RESULT_ACTION: " + trackids);
                    } else if (action.equals(Constants.NIR_RESULT_ACTION)) {
                        long trackid = intent.getLongExtra(Constants.TRACK_ID, 0l);
                        mNirTipsView.setBackground(getResources().getDrawable(R.drawable.shap_nir_tip_succ));
                        Log.d("ascb", "NIR_RESULT_ACTION: " + trackid);
                    } else if (action.equals(Constants.OFFLINE_RECOGNIZE_ACTION) || action.equals(Constants.ONLINE_RECOGNIZE_ACTION)) {
                        long trackid = intent.getLongExtra(Constants.TRACK_ID, 0l);
                        Log.d("ascb", "OFFLINE_RECOGNIZE_ACTION: " + trackid);
                        username = intent.getStringExtra(Constants.USER_NAME);
                        memberId = intent.getStringExtra(Constants.PERSON_ID);
                        Log.d("ascb", "OFFLINE_RECOGNIZE_ACTION: " + memberId);
                        Message msg = new Message();
                        msg.what = Constants.KEY_DETECT_USER_NAME;
                        Bundle b = new Bundle();
                        b.putString(Constants.USER_NAME, username);
                        msg.setData(b);
                        mHandler.sendMessage(msg);
                    }
                }
            }
        }
    }

    public class LoopTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.6f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View view, float position) {
            if (position < -1) {
                position = -1;
            } else if (position > 1) {
                position = 1;
            }
            float tempScale = position < 0 ? 1 + position : 1 - position;
            float scaleValue = MIN_SCALE + tempScale * 0.2f;
            view.setScaleX(scaleValue);
            view.setScaleY(scaleValue);

            if (position < -1 || position > 1) {
                view.setAlpha(MIN_ALPHA);
            } else {
                //不透明->半透明
                if (position < 0) {//[0,-1]
                    view.setAlpha(MIN_ALPHA + (1 + position) * (1 - MIN_ALPHA));
                } else {//[1,0]                //半透明->不透明
                    view.setAlpha(MIN_ALPHA + (1 - position) * (1 - MIN_ALPHA));
                }
            }

        }
    }
}
