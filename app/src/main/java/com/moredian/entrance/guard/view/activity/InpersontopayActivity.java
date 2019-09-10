package com.moredian.entrance.guard.view.activity;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.SPUtils;
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
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.utils.ToastHelper;
import com.moredian.entrance.guard.view.adapter.MealPagerAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android_serialport_api.ChangeTool;
import android_serialport_api.SerialPortUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InpersontopayActivity extends BaseActivity {

    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.meal_viewpager)
    ViewPager mealViewpager;
    @BindView(R.id.inper_face_pay)
    ImageView inperFacePay;
    private int x;
    private int y;
    private List<GetMealList.ContentBean> datas = new ArrayList<>();
    MealPagerAdapter adapter;
    private int publiccount;
    private List<String[]> times = new ArrayList<>();
    int shouldConsume;
    private double money;
    SimpleDateFormat format;

    public static Intent getInpersontopayActivityIntent(Context context, LinearLayout mainLl3) {
        Intent intent = new Intent(context, InpersontopayActivity.class);
        int[] vLocation = new int[2];
        mainLl3.getLocationOnScreen(vLocation);
        int centerX = vLocation[0] + mainLl3.getMeasuredWidth() / 2;
        int centerY = vLocation[1] + mainLl3.getMeasuredHeight() / 2;
        intent.putExtra("x", centerX);
        intent.putExtra("y", centerY);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.activity_inpersontopay;
    }

    @Override
    public void initView() {
        pageName.setText("时段定额");
        x = getIntent().getIntExtra("x", 0);
        y = getIntent().getIntExtra("y", 0);
        root.post(new Runnable() {
            @Override
            public void run() {
                createCircleReveal(x, y).start();
            }
        });
    }

    @Override
    public void initData() {
        Date date = new Date(System.currentTimeMillis());
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentDate = format.format(date).substring(0,11);
        String currentTime = format.format(date);
        api.getMealList(Constants.DEVICE_ID, token);
        api.setGetResponseListener(new Api.GetResponseListener() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof GetMealList) {
                    datas.clear();
                    datas.addAll(((GetMealList) o).getContent());
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    } else {
                        adapter = new MealPagerAdapter(datas, InpersontopayActivity.this);
                        mealViewpager.setAdapter(adapter);
                        mealViewpager.setPageTransformer(false, new LoopTransformer());
                        mealViewpager.setOffscreenPageLimit(datas.size());
                    }
                    for (int i = 0; i < datas.size(); i++) {
                        String begintime = currentDate + ((GetMealList) o).getContent().get(i).getMeal().getBeginTime();
                        String endtime = currentDate + ((GetMealList) o).getContent().get(i).getMeal().getEndTime();
                        String[] timearr = new String[]{begintime, endtime};
                        times.add(timearr);
                    }
                    try {
                        for (int j = 0; j < times.size(); j++) {
                            if (format.parse(times.get(j)[0]).getTime() > format.parse(times.get(j)[1]).getTime()) {
                                mealViewpager.setCurrentItem(times.size()-1);
                                shouldConsume = times.size()-1;
                                break;
                            }
                            if (format.parse(times.get(j)[0]).getTime() <= format.parse(currentTime).getTime() && format.parse(times.get(j)[1]).getTime() >= format.parse(currentTime).getTime()) {
                                mealViewpager.setCurrentItem(j);
                                shouldConsume = j;
                                break;
                            } else {
                                shouldConsume = mealViewpager.getCurrentItem();
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFail(String err) {

            }
        });
        MainApplication.getSerialPortUtils().setOnDataReceiveListener(new SerialPortUtils.OnDataReceiveListener() {
            @Override
            public void onDataReceive(byte[] buffer, int size) {
                String a = ChangeTool.ByteArrToHex(buffer, 0, size);
                money = datas.get(shouldConsume).getDeviceMeal().getMealAmount();
                if (a.length() == 32) {//接收到刷卡的信息
                    formatReadCard(a, money);
                } else if (a.length() == 42) {//接收到扫码的信息
                    //扫码消费 011103040101000C00000028731343776464974922
                    QrCodeConsume(a, money);
                }
            }
        });
    }

    /**
     * descirption: 把接收到刷卡的16进制数转化去消费查询
     */
    private void formatReadCard(String a, double money) {
        int companyCode = ChangeTool.HexToInt(a.substring(16, 20));//单位代码
        int number = ChangeTool.HexToInt(a.substring(20, 26));//卡内码
        getReadCardPaycount(companyCode, Constants.DEVICE_ID, number, money);

    }

    /**
     * descirption: 查询卡支付次数，不展示dialog
     */
    public void getReadCardPaycount(Integer companyCode, Integer diviceID, Integer number, double money) {
        api.getReadCard(companyCode, diviceID, number, token, Constants.MODIAN_TOKEN);
        api.setGetResponseListener(new Api.GetResponseListener<GetReadCard>() {
            @Override
            public void onRespnse(GetReadCard getReadCard) {
                publiccount = getReadCard.getContent().getPayCount();
                publiccount++;
                String name = getReadCard.getContent().getUserName();
                int status = getReadCard.getContent().getState();
                //查询到消费次数之后执行消费
                postDefineExpense(number, publiccount, name, status, money);
            }

            @Override
            public void onFail(String err) {

            }
        });
    }

    /**
     * descirption: 定值消费
     */
    public void postDefineExpense(int number, int count, String name, int status, double money) {
        PostDefiniteExpenseBody body = new PostDefiniteExpenseBody(number, 0, count, "scy", Constants.DEVICE_ID);
        api.postDefiniteExpense(body, token);
        api.setGetResponseListener(new Api.GetResponseListener<DefiniteExpense>() {
            @Override
            public void onRespnse(DefiniteExpense definiteExpense) {
                consumeSenddown(definiteExpense, status, name);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //跳转到支付成功界面
                        startActivity(ConsumeResultActivity.getDefineConsumeSuccessActivityIntent(InpersontopayActivity.this, definiteExpense.getContent().getExpenseDetail()));
                    }
                });
            }

            @Override
            public void onFail(String err) {
                ToastUtils.showShort("支付失败");
                startActivity(ConsumeResultActivity.getConsumeFailActivityIntent(InpersontopayActivity.this));
            }
        });
    }

    /**
     * descirption: 付款码消费
     */
    private void QrCodeConsume(String a, double money) {
        String qrcode = a.substring(22, 40);
        PostQRCodeExpenseBody body = new PostQRCodeExpenseBody(qrcode, money, 3, Constants.DEVICE_ID, 2);
        api.postQRCodeExpense(body, token, Constants.MODIAN_TOKEN);
        api.setGetResponseListener(new Api.GetResponseListener<QRCodeExpense>() {
            @Override
            public void onRespnse(QRCodeExpense qrCodeExpense) {
                if (qrCodeExpense.getContent().getQRType() == 3) {
                    //如果是会员码的话返回用户信息
                    // TODO: 2019/8/15
                }
                startActivity(ConsumeResultActivity.getQRConsumeSuccessActivityIntent(InpersontopayActivity.this, qrCodeExpense.getContent()));
            }

            @Override
            public void onFail(String err) {
                ToastUtils.showShort("支付失败");
                startActivity(ConsumeResultActivity.getConsumeFailActivityIntent(InpersontopayActivity.this));
            }
        });
    }

    /**
     * descirption: 消费成功，拼接字符，数据下行
     */
    private void consumeSenddown(DefiniteExpense simpleExpense, int status, String name) {
        double amount = simpleExpense.getContent().getExpenseDetail().getAmount();
        double oamount = simpleExpense.getContent().getExpenseDetail().getOriginalAmount();
        double balance = simpleExpense.getContent().getExpenseDetail().getBalance();
        int paycount = simpleExpense.getContent().getExpenseDetail().getPayCount();
        int discountrate = simpleExpense.getContent().getExpenseDetail().getDiscountRate();
        String consumestatus = ChangeTool.numToHex1(simpleExpense.getContent().getTradingState());
        String discountratehex = ChangeTool.numToHex1(discountrate);
        String namehex = getNameHex(name);
        String balancehex = ChangeTool.numToHex3((int) (balance * 100));
        String amounthex = ChangeTool.numToHex3((int) (amount * 100));
        String oamounthex = ChangeTool.numToHex3((int) (oamount * 100));
        String paycounthex = ChangeTool.numToHex2(paycount);
        String statushex = ChangeTool.numToHex1(status);
        String sum = "0301010017" + namehex + balancehex + oamounthex + amounthex + discountratehex + paycounthex + statushex + consumestatus;
        MainApplication.getSerialPortUtils().sendSerialPort("A1B1030301010017" + namehex + balancehex + oamounthex + amounthex + discountratehex + paycounthex + statushex + consumestatus + ChangeTool.makeChecksum(sum));
    }

    /**
     * descirption: 拼接数据name
     */
    private String getNameHex(String name) {
        String namehex = ChangeTool.toChineseHex(name);
        StringBuilder stringBuilder = new StringBuilder();
        if (namehex.length() < 18) {
            for (int i = 0; i < (18 - namehex.length()); i++) {
                stringBuilder.append("0");
            }
        }
        namehex = namehex + stringBuilder.toString();
        return namehex;
    }


    @TargetApi(21)
    private Animator createCircleReveal(int x, int y) {
        float radius = (float) Math.hypot(root.getHeight(), root.getWidth());
        Animator animator = ViewAnimationUtils.createCircularReveal(root, x, y, 0, radius);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        return animator;
    }

    @TargetApi(21)
    private Animator closeCircleReveal(int x, int y) {
        float radius = (float) Math.hypot(root.getHeight(), root.getWidth());
        Animator animator = ViewAnimationUtils.createCircularReveal(root, x, y, radius, 0);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        return animator;
    }

    @OnClick({R.id.Manualconsumption_back, R.id.inper_face_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.inper_face_pay:
                startActivityForResult(FaceInputConsumeActivity.getFaceInputActivityIntent(InpersontopayActivity.this), Constants.FACE_INPUT_REQUESTCODE);
                break;
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.FACE_INPUT_REQUESTCODE && resultCode == Constants.FACE_INPUT_RESULTCODE) {
            String memberId = data.getStringExtra(Constants.INTENT_FACEINPUT_MEMBERID);
            if (!TextUtils.isEmpty(memberId)) {
                PostFaceExpenseBody postFaceExpenseBody = new PostFaceExpenseBody(memberId, 0, 3, Constants.DEVICE_ID, 2);
                api.postFaceExpense(postFaceExpenseBody, token, Constants.MODIAN_TOKEN);
                api.setGetResponseListener(new Api.GetResponseListener<FaceExpense>() {
                    @Override
                    public void onRespnse(FaceExpense faceExpense) {
                        startActivity(ConsumeResultActivity.getFaceConsumeSuccessActivityIntent(InpersontopayActivity.this, faceExpense.getContent()));
                    }

                    @Override
                    public void onFail(String err) {
                        ToastUtils.showShort("支付失败");
                        startActivity(ConsumeResultActivity.getConsumeFailActivityIntent(InpersontopayActivity.this));
                    }
                });
            } else {
                ToastUtils.showShort("人脸未录入，不能使用人脸支付");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        times.clear();
    }
}
