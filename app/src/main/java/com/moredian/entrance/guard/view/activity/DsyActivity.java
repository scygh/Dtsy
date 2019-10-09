package com.moredian.entrance.guard.view.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.app.MainApplication;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.DefiniteExpense;
import com.moredian.entrance.guard.entity.FaceExpense;
import com.moredian.entrance.guard.entity.GetMealList;
import com.moredian.entrance.guard.entity.GetReadCard;
import com.moredian.entrance.guard.entity.PostDefiniteExpenseBody;
import com.moredian.entrance.guard.entity.PostFaceExpenseBody;
import com.moredian.entrance.guard.entity.PostQRCodeExpenseBody;
import com.moredian.entrance.guard.entity.PostSimpleExpenseBody;
import com.moredian.entrance.guard.entity.QRCodeExpense;
import com.moredian.entrance.guard.entity.SimpleExpense;
import com.moredian.entrance.guard.face.CameraUtil;
import com.moredian.entrance.guard.face.CameraView;
import com.moredian.entrance.guard.face.drawface.DrawerSurfaceView;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.utils.SerialPortApi;
import com.moredian.entrance.guard.utils.StatusBarUtil;
import com.moredian.entrance.guard.utils.ToastHelper;
import com.moredian.entrance.guard.view.adapter.MealPagerAdapter;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android_serialport_api.ChangeTool;
import android_serialport_api.SerialPortUtils;
import butterknife.BindView;


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
    TextView tvMoney;
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
    @BindView(R.id.bmb)
    BoomMenuButton bmb;
    private Mytask mytask;
    private byte[] rgb_data;
    private String memberId;
    private static boolean mShowCallbackFace = false;
    private static int mCheckRgbCameraOpenCount = 0;
    private MyReceiver mReceiver = new MyReceiver();
    private int publiccount;
    private boolean allowdConsume = false;
    private int cardnumber;
    private String name;
    private int status;
    private String username;
    private double balance;
    private int paycount;
    private List<GetMealList.ContentBean> datas = new ArrayList<>();
    private List<String[]> times = new ArrayList<>();
    MealPagerAdapter adapter;
    private static int index = 0;
    private static int imageResourceIndex = 0;
    private static String[] text = new String[]{"退出", "菜单"};
    private static int[] imageResources = new int[]{R.mipmap.face_tuichu, R.mipmap.face_menu,};
    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }
    static String getext() {
        if (index >= text.length) index = 0;
        return text[index++];
    }



    public static Intent getDsyActivityIntent(Context context) {
        Intent intent = new Intent(context, DsyActivity.class);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.activity_dsy;
    }

    @Override
    public void initView() {
        StatusBarUtil.fitStatusBar(this);
        if (!TextUtils.isEmpty(pattern)) {
            tvPattern.setText(pattern);
        }
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            if (index == 0) {
                                MainApplication.getSerialPortUtils().closeSerialPort();
                                finish();
                            } else if (index == 1) {
                                startActivity(MainActivity.getMainActivityIntent(DsyActivity.this));
                                finish();
                            }
                        }
                    })
                    .normalImageRes(getImageResource())
                    .normalText(getext());
            bmb.addBuilder(builder);
        }
        if (p == 3) {
            rlData.setVisibility(View.GONE);
            mealViewpager.setVisibility(View.VISIBLE);
            api.getMealList(Integer.parseInt(deviceId), token);
        }
    }

    @Override
    public void initData() {
        /*mytask = new Mytask();
        mytask.execute();*/
        initReceiver();
        initPort();
        initRequest();
        initCamera();
    }

    private class Mytask extends AsyncTask<Void,Integer,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            initCamera();
            return null;
        }
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
     * descirption: 监听串口接收
     */
    private void initPort() {
        MainApplication.getSerialPortUtils().setOnDataReceiveListener(new SerialPortUtils.OnDataReceiveListener() {
            @Override
            public void onDataReceive(byte[] buffer, int size) {
                String hexStr = ChangeTool.ByteArrToHex(buffer, 0, size);
                String money = tvMoney.getText().toString();
                if (hexStr.length() == 24) {//接收到键盘输入金额
                    formatMoneyHex(buffer, size);
                } else if (hexStr.length() == 32) {//接收到刷卡的信息
                    if (p != 3) {
                        if (money.equals("0.00")) {
                            formatReadCard(hexStr, Constants.KIND_FIND);
                        } else {
                            //刷卡消费
                            formatReadCard(hexStr, Constants.KIND_CONSUME);
                        }
                    } else {
                        defineConsume(hexStr);
                    }
                } else if (hexStr.length() == 42) {//接收到扫码的信息
                    if (p != 3) {
                        if (money.equals("0.00")) {
                            ToastHelper.showToast("键盘还未输入金额");
                        } else {
                            //扫码消费 011103040101000C00000028731343776464974922
                            QrCodeConsume(hexStr, Constants.KIND_CONSUME_TDC);
                        }
                    } else {
                        ToastHelper.showToast("定值消费模式不支持二维码消费");
                    }
                }
            }
        });
    }

    /**
     * descirption: 初始化请求返回
     */
    private void initRequest() {
        api.setGetResponseListener(new Api.GetResponseListener() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof GetReadCard) {//如果是刷卡
                    name = ((GetReadCard) o).getContent().getUserName();
                    status = ((GetReadCard) o).getContent().getState();
                    balance = ((GetReadCard) o).getContent().getBalance();
                    paycount = ((GetReadCard) o).getContent().getPayCount();
                    if (!allowdConsume) {//如果不允许消费，查询，数据下行
                        updateText();
                        String namehex = SerialPortApi.getNameHex(name);
                        String balancehex = ChangeTool.numToHex3((int) (balance * 100));
                        String paycounthex = ChangeTool.numToHex2(paycount);
                        String statushex = ChangeTool.numToHex1(status);
                        String sum = "020101000f" + namehex + balancehex + paycounthex + statushex;
                        MainApplication.getSerialPortUtils().sendSerialPort("A1B103020101000f" + namehex + balancehex + paycounthex + statushex + ChangeTool.makeChecksum(sum));
                    } else {//如果允许消费，消费，数据下行
                        Log.d(TAG, "onRespnse: allowed");
                        publiccount = ((GetReadCard) o).getContent().getPayCount();
                        publiccount++;
                        //查询到消费次数之后执行消费
                        if (p == 3) {
                            postDefineExpense(cardnumber, publiccount, name, status);
                        } else {
                            postSimpleExpense(cardnumber, publiccount);
                        }
                    }
                } else if (o instanceof SimpleExpense) {//刷卡简单消费
                    SerialPortApi.consumeSenddown(((SimpleExpense) o), status, name);
                    if (p == 1) {//如果是手动消费
                        clearData();
                        //跳转到支付成功界面
                        startActivity(ConsumeResultActivity.getConsumeSuccessActivityIntent(DsyActivity.this, ((SimpleExpense) o).getContent()));
                    } else if (p == 2) {//如果是自动消费
                        updateText();
                        ToastHelper.showToast("刷卡消费成功啦！");
                    }
                } else if (o instanceof DefiniteExpense) {//定值消费
                    SerialPortApi.consumeSenddown(((DefiniteExpense) o), status, name);
                    startActivity(ConsumeResultActivity.getDefineConsumeSuccessActivityIntent(DsyActivity.this, ((DefiniteExpense) o).getContent().getExpenseDetail()));
                } else if (o instanceof QRCodeExpense) {//二维码消费
                    if (p == 1) {
                        clearData();
                        //跳转到支付成功界面
                        startActivity(ConsumeResultActivity.getQRConsumeSuccessActivityIntent(DsyActivity.this, ((QRCodeExpense) o).getContent()));
                    } else if (p == 2) {
                        autoUpdateData(tvUsername, "", tvBalance, tvPaycount);
                        ToastHelper.showToast("二维码消费成功啦");
                    }
                } else if (o instanceof FaceExpense) {//人脸消费
                    SerialPortApi.consumeSenddown(((FaceExpense) o), 0, username);
                    if (p == 1 || p == 3) {
                        clearData();
                        startActivity(ConsumeResultActivity.getFaceConsumeSuccessActivityIntent(DsyActivity.this, ((FaceExpense) o).getContent()));
                    } else if (p == 2) {
                        autoUpdateData(tvUsername, "", tvBalance, tvPaycount);
                        ToastHelper.showToast("人脸消费成功啦！");
                    }
                } else if (o instanceof GetMealList) {
                    datas.clear();
                    datas.addAll(((GetMealList) o).getContent());
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    } else {
                        adapter = new MealPagerAdapter(datas, DsyActivity.this);
                        mealViewpager.setAdapter(adapter);
                        mealViewpager.setPageTransformer(false, new LoopTransformer());
                        mealViewpager.setOffscreenPageLimit(datas.size());
                    }
                    setDefiCurrentItem((GetMealList) o);
                }
            }

            @Override
            public void onFail(String err) {
                if (err.equals(Constants.CONSUME_ERROR)) {
                    clearData();
                    startActivity(ConsumeResultActivity.getConsumeFailActivityIntent(DsyActivity.this));
                }
            }
        });
    }

    /**
     * descirption: 跳转到当前Pager
     */
    private void setDefiCurrentItem(GetMealList o) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentDate = format.format(date).substring(0, 11);
        String currentTime = format.format(date);
        for (int i = 0; i < datas.size(); i++) {
            String begintime = currentDate + o.getContent().get(i).getMeal().getBeginTime();
            String endtime = currentDate + o.getContent().get(i).getMeal().getEndTime();
            String[] timearr = new String[]{begintime, endtime};
            times.add(timearr);
        }
        try {
            for (int j = 0; j < times.size(); j++) {
                if (format.parse(times.get(j)[0]).getTime() > format.parse(times.get(j)[1]).getTime()) {
                    mealViewpager.setCurrentItem(times.size() - 1);
                    break;
                }
                if (format.parse(times.get(j)[0]).getTime() <= format.parse(currentTime).getTime() && format.parse(times.get(j)[1]).getTime() >= format.parse(currentTime).getTime()) {
                    mealViewpager.setCurrentItem(j);
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * descirption: 清空数据
     */
    private void clearData() {
        autoUpdateData(tvMoney, "0.00", tvUsername, tvBalance);
        tvPaycount.setText("");
    }

    /**
     * descirption: 清除人员信息
     */
    private void autoUpdateData(TextView tvUsername, String s, TextView tvBalance, TextView tvPaycount) {
        tvUsername.setText(s);
        tvBalance.setText("");
        tvPaycount.setText("");
    }

    /**
     * descirption: 更新数据
     */
    private void updateText() {
        Message message = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putDouble("balance", balance);
        bundle.putInt("paycount", paycount);
        message.setData(bundle);
        message.what = Constants.KEY_SET_TEXT;
        mHandler.sendMessage(message);
    }

    /**
     * descirption: 将接受到的金额字节数组转化为实际金额并展示，并下发接受结果
     */
    private void formatMoneyHex(byte[] buffer, int size) {
        try {
            int money = ChangeTool.HexToInt(ChangeTool.ByteArrToHex(buffer, 0, size).substring(16, 22));
            float m = (float) money / 100;
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String formatmomey = decimalFormat.format(m);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvMoney.setText(formatmomey);
                }
            });
            MainApplication.getSerialPortUtils().sendSerialPort(Constants.SETMONEYOK);
        } catch (Exception e) {
            e.printStackTrace();
            MainApplication.getSerialPortUtils().sendSerialPort(Constants.SETMONEYFAIL);
        }
    }

    /**
     * descirption: 把接收到刷卡的16进制数转化去消费、查询
     */
    private void formatReadCard(String a, int kind) {
        int companyCode = ChangeTool.HexToInt(a.substring(16, 20));//单位代码
        cardnumber = ChangeTool.HexToInt(a.substring(20, 26));//卡内码
        if (kind == Constants.KIND_FIND) {
            getReadCard(companyCode, Integer.parseInt(deviceId), cardnumber);
        } else if (kind == Constants.KIND_CONSUME) {
            getReadCardPaycount(companyCode, Integer.parseInt(deviceId), cardnumber);
        }
    }

    /**
     * descirption: 去定值消费
     */
    private void defineConsume(String a) {
        int companyCode = ChangeTool.HexToInt(a.substring(16, 20));//单位代码
        cardnumber = ChangeTool.HexToInt(a.substring(20, 26));//卡内码
        getReadCardPaycount(companyCode, Integer.parseInt(deviceId), cardnumber);
    }

    /**
     * descirption: 查询卡信息，数据下行
     */
    public void getReadCard(Integer companyCode, Integer diviceID, Integer number) {
        allowdConsume = false;
        api.getReadCard(companyCode, diviceID, number, token, Constants.MODIAN_TOKEN);
    }

    /**
     * descirption: 查询卡支付次数取消费
     */
    public void getReadCardPaycount(Integer companyCode, Integer diviceID, Integer number) {
        allowdConsume = true;
        api.getReadCard(companyCode, diviceID, number, token, Constants.MODIAN_TOKEN);
    }

    /**
     * descirption: 刷卡消费
     */
    public void postSimpleExpense(int number, int count) {
        String amount = tvMoney.getText().toString();
        PostSimpleExpenseBody body = new PostSimpleExpenseBody(number, Double.parseDouble(amount), p, count, "scy", Integer.parseInt(deviceId), 2);
        api.postSimpleExpense(body, token);
    }

    /**
     * descirption: 定值消费
     */
    public void postDefineExpense(int number, int count, String name, int status) {
        PostDefiniteExpenseBody body = new PostDefiniteExpenseBody(number, 0, count, "scy", Integer.parseInt(deviceId));
        api.postDefiniteExpense(body, token);
    }

    /**
     * descirption: 付款码消费
     */
    private void QrCodeConsume(String a, int kind) {
        String qrcode = a.substring(22, 40);
        if (kind == Constants.KIND_CONSUME_TDC) {
            String amount = tvMoney.getText().toString();
            PostQRCodeExpenseBody body = new PostQRCodeExpenseBody(qrcode, Double.parseDouble(amount), p, Integer.parseInt(deviceId), 2);
            api.postQRCodeExpense(body, token, Constants.MODIAN_TOKEN);
        }
    }

    /**
     * descirption: 人脸消费
     */
    private void faceConsume() {
        if (!TextUtils.isEmpty(memberId)) {
            if (p == 3) {
                PostFaceExpenseBody postFaceExpenseBody = new PostFaceExpenseBody(memberId, 0, p, Integer.parseInt(deviceId), 2);
                api.postFaceExpense(postFaceExpenseBody, token, Constants.MODIAN_TOKEN);
            } else {
                String money = tvMoney.getText().toString();
                if (!money.equals("0.00")) {
                    PostFaceExpenseBody postFaceExpenseBody = new PostFaceExpenseBody(memberId, Double.parseDouble(money), p, Integer.parseInt(deviceId), 2);
                    api.postFaceExpense(postFaceExpenseBody, token, Constants.MODIAN_TOKEN);
                } else {
                    ToastHelper.showToast("请输入金额");
                }
            }
        } else {
            ToastUtils.showShort("人脸未录入，不能使用人脸支付");
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
        allowdConsume = false;
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

    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.KEY_SET_TEXT:
                    String name = msg.getData().getString("name");
                    double balance = msg.getData().getDouble("balance");
                    int paycount = msg.getData().getInt("paycount");
                    tvUsername.setText(name);
                    tvBalance.setText(balance + "元");
                    tvPaycount.setText(paycount + "次");
                    break;
                //如果人脸识别已打开，红外生物识别获取焦点，如果没打开则延迟重来
                case Constants.KEY_OPEN_NIR_CAMERA:
                    if (mRgbCameraView != null) {
                        if (mRgbCameraView.hasOpened()) {
                            mNirCameraView.onResume();
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
                        long trackid = 0l;
                        if (facecount > 0) {
                            for (int i = 0; i < facecount; i++) {
                                trackid = intent.getLongExtra(Constants.TRACK_ID + i, 0l);
                                trackids = trackids + "," + trackid;
                            }
                        }
                    } else if (action.equals(Constants.NIR_RESULT_ACTION)) {
                        long trackid = intent.getLongExtra(Constants.TRACK_ID, 0l);
                        mNirTipsView.setBackground(getResources().getDrawable(R.drawable.shap_nir_tip_succ));
                        Log.d(TAG, "NIR_RESULT_ACTION: " + System.currentTimeMillis());
                    } else if (action.equals(Constants.OFFLINE_RECOGNIZE_ACTION) || action.equals(Constants.ONLINE_RECOGNIZE_ACTION)) {
                        Log.d(TAG, "OFFLINE_RECOGNIZE_ACTION: " + System.currentTimeMillis());
                        long trackid = intent.getLongExtra(Constants.TRACK_ID, 0l);
                        username = intent.getStringExtra(Constants.USER_NAME);
                        memberId = intent.getStringExtra(Constants.PERSON_ID);
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
        private static final float MIN_SCALE = 0.8f;

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
        }
    }
}
