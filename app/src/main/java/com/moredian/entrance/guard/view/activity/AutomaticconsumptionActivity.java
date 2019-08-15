package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.app.MainApplication;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.FaceExpense;
import com.moredian.entrance.guard.entity.GetReadCard;
import com.moredian.entrance.guard.entity.PostFaceExpenseBody;
import com.moredian.entrance.guard.entity.PostQRCodeExpenseBody;
import com.moredian.entrance.guard.entity.PostSimpleExpenseBody;
import com.moredian.entrance.guard.entity.QRCodeExpense;
import com.moredian.entrance.guard.entity.SimpleExpense;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.view.fragment.ShowCardMessageFragment;

import java.nio.charset.Charset;
import java.text.DecimalFormat;

import android_serialport_api.ChangeTool;
import android_serialport_api.SerialPortUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AutomaticconsumptionActivity extends AppCompatActivity {
    private static final String TAG = "AutoActivity";
    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.automaticcnsumption_keyboard_enter_money)
    EditText automaticcnsumptionKeyboardEnterMoney;
    @BindView(R.id.persondetail_sure)
    Button persondetailSure;
    @BindView(R.id.persondetail_cancle)
    Button persondetailCancle;
    private Api api;
    private int publiccount;


    public static Intent getAutomaticconsumptionActivityIntent(Context context) {
        Intent intent = new Intent(context, AutomaticconsumptionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automaticconsumption);
        ButterKnife.bind(this);
        pageName.setText("自动扣款");
        api = new Api();
        MainApplication.getSerialPortUtils().setOnDataReceiveListener(new SerialPortUtils.OnDataReceiveListener() {
            @Override
            public void onDataReceive(byte[] buffer, int size) {
                String a = ChangeTool.ByteArrToHex(buffer, 0, size);
                String money = automaticcnsumptionKeyboardEnterMoney.getText().toString().trim();
                if (a.length() == 24) {//接收到键盘输入金额
                    formatHex(buffer, size);
                } else if (a.length() == 32) {//接收到刷卡的信息
                    if (money.equals("请输入自动扣款金额") || money.equals("0.00") || money.equals("")) {
                        formatReadCard(a, Constants.KIND_FIND);
                    } else {
                        formatReadCard(a, Constants.KIND_CONSUME);
                    }
                } else if (a.length() == 42) {//接收到扫码的信息
                    //扫码消费 011103040101000C00000028731343776464974922
                    QrCodeConsume(a, Constants.KIND_CONSUME_TDC);
                }
            }
        });
    }

    /**
     * descirption: 把接收到刷卡的16进制数转化去消费查询
     */
    private void formatReadCard(String a, int kind) {
        int companyCode = ChangeTool.HexToInt(a.substring(16, 20));//单位代码
        int number = ChangeTool.HexToInt(a.substring(20, 26));//卡内码
        if (kind == Constants.KIND_FIND) {
            getReadCard(companyCode, 1, number);
        } else if (kind == Constants.KIND_CONSUME) {
            getReadCardPaycount(companyCode, 1, number);
        }
    }

    /**
     * descirption: 查询卡信息
     */
    public void getReadCard(Integer companyCode, Integer diviceID, Integer number) {
        String token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
        if (token != null) {
            api.getReadCard(companyCode, diviceID, number, token, "123");
            Log.d(TAG, "getReadCard: findover");
        }
        api.setGetResponseListener(new Api.GetResponseListener<GetReadCard>() {
            @Override
            public void onRespnse(GetReadCard getReadCard) {
                String name = getReadCard.getContent().getUserName();
                double balance = getReadCard.getContent().getBalance();
                int paycount = getReadCard.getContent().getPayCount();
                int status = getReadCard.getContent().getState();
                Log.d(TAG, "onRespnse: " + name);
                String namehex = getNameHex(name);
                Log.d(TAG, "onRespnse: " + namehex);
                Log.d(TAG, "onRespnse: " + balance);
                String balancehex = ChangeTool.numToHex3((int) (balance * 100));
                Log.d(TAG, "onRespnse: " + balancehex);
                Log.d(TAG, "onRespnse: " + paycount);
                String paycounthex = ChangeTool.numToHex2(paycount);
                Log.d(TAG, "onRespnse: " + paycounthex);
                Log.d(TAG, "onRespnse: " + status);
                String statushex = ChangeTool.numToHex1(status);
                Log.d(TAG, "onRespnse: " + statushex);
                showDialog(getReadCard);
                String sum = "020101000f" + namehex + balancehex + paycounthex + statushex;
                Log.d(TAG, "onRespnse: " + "A1B103020101000f" + namehex + balancehex + paycounthex + statushex + ChangeTool.makeChecksum(sum));
                MainApplication.getSerialPortUtils().sendSerialPort("A1B103020101000f" + namehex + balancehex + paycounthex + statushex + ChangeTool.makeChecksum(sum));
            }

            @Override
            public void onFail(String err) {

            }
        });
    }

    /**
     * descirption: 拼接数据name
     */
    private String getNameHex(String name) {
        String namehex = ChangeTool.string2Unicode(name);
        StringBuilder stringBuilder = new StringBuilder();
        if (namehex.length() < 18) {
            for (int i = 0; i < (18 - namehex.length()); i++) {
                stringBuilder.append("0");
            }
        }
        namehex = namehex + stringBuilder.toString();
        return namehex;
    }

    /**
     * descirption: 展示dialog
     */
    public void showDialog(GetReadCard getReadCard) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ShowCardMessageFragment fragment = ShowCardMessageFragment.newINstance(getReadCard.getContent());
        fragment.show(fragmentManager, Constants.SHOWCARDMESSAGE);
    }

    /**
     * descirption: 外接键盘输入显示
     */
    private void formatHex(byte[] buffer, int size) {
        try {
            int money = ChangeTool.HexToInt(ChangeTool.ByteArrToHex(buffer, 0, size).substring(16, 22));
            float m = (float) money / 100;
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String formatmomey = decimalFormat.format(m);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    automaticcnsumptionKeyboardEnterMoney.setText(formatmomey);
                }
            });
            MainApplication.getSerialPortUtils().sendSerialPort(Constants.SETMONEYOK);
        } catch (Exception e) {
            e.printStackTrace();
            MainApplication.getSerialPortUtils().sendSerialPort(Constants.SETMONEYFAIL);
        }
    }

    /**
     * descirption: 查询卡支付次数，不展示dialog
     */
    public void getReadCardPaycount(Integer companyCode, Integer diviceID, Integer number) {
        String token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
        if (token != null) {
            api.getReadCard(companyCode, diviceID, number, token, "123");
        }
        api.setGetResponseListener(new Api.GetResponseListener<GetReadCard>() {
            @Override
            public void onRespnse(GetReadCard getReadCard) {
                publiccount = getReadCard.getContent().getPayCount();
                publiccount++;
                String name = getReadCard.getContent().getUserName();
                int status = getReadCard.getContent().getState();
                //查询到消费次数之后执行消费
                postSimpleExpense(number, publiccount, name, status);
            }

            @Override
            public void onFail(String err) {

            }
        });
    }

    /**
     * descirption: 刷卡消费
     */
    public void postSimpleExpense(int number, int count, String name, int status) {
        String token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
        String amount = automaticcnsumptionKeyboardEnterMoney.getText().toString();
        PostSimpleExpenseBody body = new PostSimpleExpenseBody(number, Double.parseDouble(amount), 2, count, "scy", 1, 2);
        if (token != null) {
            api.postSimpleExpense(body, token);
            api.setGetResponseListener(new Api.GetResponseListener<SimpleExpense>() {
                @Override
                public void onRespnse(SimpleExpense simpleExpense) {
                    consumeSenddown(simpleExpense, status, name);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //跳转到支付成功界面
                            startActivity(ConsumeResultActivity.getConsumeSuccessActivityIntent(AutomaticconsumptionActivity.this, simpleExpense.getContent()));
                        }
                    });
                }

                @Override
                public void onFail(String err) {
                    ToastUtils.showShort("支付失败");
                    startActivity(ConsumeResultActivity.getConsumeFailActivityIntent(AutomaticconsumptionActivity.this));
                }
            });
        }
    }

    /**
     * descirption: 付款码消费
     */
    private void QrCodeConsume(String a, int kind) {
        String qrcode = a.substring(22, 40);
        if (kind == Constants.KIND_CONSUME_TDC) {
            String token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
            String amount = automaticcnsumptionKeyboardEnterMoney.getText().toString();
            if (token != null) {
                PostQRCodeExpenseBody body = new PostQRCodeExpenseBody(qrcode, Double.parseDouble(amount), 2, 1, 2);
                api.postQRCodeExpense(body, token, "123");
            }
            api.setGetResponseListener(new Api.GetResponseListener<QRCodeExpense>() {
                @Override
                public void onRespnse(QRCodeExpense qrCodeExpense) {
                    if (qrCodeExpense.getContent().getQRType() == 3) {
                        //如果是会员码的话返回用户信息
                        // TODO: 2019/8/15
                    }
                    startActivity(ConsumeResultActivity.getQRConsumeSuccessActivityIntent(AutomaticconsumptionActivity.this, qrCodeExpense.getContent()));
                }

                @Override
                public void onFail(String err) {
                    ToastUtils.showShort("支付失败");
                    startActivity(ConsumeResultActivity.getConsumeFailActivityIntent(AutomaticconsumptionActivity.this));
                }
            });
        }
    }

    /**
     * descirption: 消费成功，拼接字符，数据下行
     */
    private void consumeSenddown(SimpleExpense simpleExpense, int status, String name) {
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
        //MainApplication.getSerialPortUtils().sendSerialPort("A1B10303010100177363793131000000001e05280003840003846400380000c2");
        Log.d(TAG, "consumeSenddown: " + "A1B1030301010017" + namehex + balancehex + oamounthex + amounthex + discountratehex + paycounthex + statushex + consumestatus + ChangeTool.makeChecksum(sum));
    }

    /**
     * descirption: 点击事件
     */
    @OnClick({R.id.Manualconsumption_back, R.id.persondetail_sure, R.id.persondetail_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.persondetail_sure:
                // TODO: 2019/8/9  拉起人脸支付
                String money = automaticcnsumptionKeyboardEnterMoney.getText().toString().trim();
                if (money.equals("请输入自动扣款金额") || money.equals("0.00") || money.equals("")) {
                    closeKeyboard();
                    ToastUtils.showShort("还未设置金额");
                } else {
                    closeKeyboard();
                    startActivityForResult(FaceInputActivity.getFaceInputActivityIntent(AutomaticconsumptionActivity.this), Constants.FACE_INPUT_REQUESTCODE);
                }
                break;
            case R.id.persondetail_cancle:
                automaticcnsumptionKeyboardEnterMoney.setText("");
                closeKeyboard();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.FACE_INPUT_REQUESTCODE && resultCode == Constants.FACE_INPUT_RESULTCODE) {
            String memberId = data.getStringExtra(Constants.INTENT_FACEINPUT_MEMBERID);
            if (!TextUtils.isEmpty(memberId)) {
                String token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
                PostFaceExpenseBody postFaceExpenseBody = new PostFaceExpenseBody(memberId,Double.parseDouble(automaticcnsumptionKeyboardEnterMoney.getText().toString().trim()),2,1,2);
                api.postFaceExpense(postFaceExpenseBody,token,"123");
                api.setGetResponseListener(new Api.GetResponseListener<FaceExpense>() {
                    @Override
                    public void onRespnse(FaceExpense faceExpense) {
                        startActivity(ConsumeResultActivity.getFaceConsumeSuccessActivityIntent(AutomaticconsumptionActivity.this, faceExpense.getContent()));
                    }

                    @Override
                    public void onFail(String err) {
                        ToastUtils.showShort("支付失败");
                        startActivity(ConsumeResultActivity.getConsumeFailActivityIntent(AutomaticconsumptionActivity.this));
                    }
                });
            }
        }
    }

    /**
     * descirption: 关闭软键盘
     */
    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

}
