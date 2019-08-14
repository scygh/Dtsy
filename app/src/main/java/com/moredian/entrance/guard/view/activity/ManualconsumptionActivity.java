package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.app.MainApplication;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetReadCard;
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

public class ManualconsumptionActivity extends AppCompatActivity {

    private static final String TAG = "MActivity";
    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.Manualconsumption_name)
    TextView ManualconsumptionName;
    @BindView(R.id.Manualconsumption_balance)
    TextView ManualconsumptionBalance;
    @BindView(R.id.Manualconsumption_keyboard_enter_money)
    TextView ManualconsumptionKeyboardEnterMoney;
    @BindView(R.id.Manualconsumption_usesdozensmallnotes)
    Button ManualconsumptionUsesdozensmallnotes;
    @BindView(R.id.page_name)
    TextView pageName;
    private Api api;
    private int publiccount;

    public static Intent getManualconsumptionActivityIntent(Context context) {
        Intent intent = new Intent(context, ManualconsumptionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manualconsumption);
        ButterKnife.bind(this);
        pageName.setText("手动扣款");
        api = new Api();
        MainApplication.getSerialPortUtils().setOnDataReceiveListener(new SerialPortUtils.OnDataReceiveListener() {
            @Override
            public void onDataReceive(byte[] buffer, int size) {
                String a = ChangeTool.ByteArrToHex(buffer, 0, size);
                Log.d(TAG, "formatHex: " + a);
                Log.d(TAG, "formatHex: " + a.length());
                String money = ManualconsumptionKeyboardEnterMoney.getText().toString().trim();
                if (a.length() == 24) {//接收到键盘输入金额
                    formatHex(buffer, size);
                } else if (a.length() == 32) {//接收到刷卡的信息
                    if (money.equals("键盘输入金额") || money.equals("0.00")) {
                        formatReadCard(a, Constants.KIND_FIND);
                    } else {
                        //刷卡消费
                        formatReadCard(a, Constants.KIND_CONSUME);
                    }
                } else if (a.length() == 42) {//接收到扫码的信息
                    if (money.equals("键盘输入金额") || money.equals("0.00")) {
                        ToastUtils.showShort("键盘还未输入金额，请服务人员确认金额");
                    } else {
                        //扫码消费 011103040101000C00000028731343776464974922
                        QrCodeConsume(a, Constants.KIND_CONSUME_TDC);
                    }
                }
            }
        });
    }

    /**
     * descirption: 出示付款码操作
     */
    private void QrCodeConsume(String a, int kind) {
        String qrcode = a.substring(22, 40);
        Log.d(TAG, "QrCodeConsume: " + qrcode);
        if (kind == Constants.KIND_CONSUME_TDC) {
            String token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
            String amount = ManualconsumptionKeyboardEnterMoney.getText().toString();
            if (token != null) {
                PostQRCodeExpenseBody body = new PostQRCodeExpenseBody(qrcode, Double.parseDouble(amount), 1, 1, 2);
                api.postQRCodeExpense(body, token, "123");
            }
            api.setGetResponseListener(new Api.GetResponseListener<QRCodeExpense>() {
                @Override
                public void onRespnse(QRCodeExpense qrCodeExpense) {
                    ManualconsumptionKeyboardEnterMoney.setText("0.00");
                    //跳转到支付成功界面
                    startActivity(ConsumeResultActivity.getQRConsumeSuccessActivityIntent(ManualconsumptionActivity.this, qrCodeExpense.getContent().getDetails(), "二维码支付"));
                }

                @Override
                public void onFail(String err) {
                    ToastUtils.showShort("支付失败");
                    startActivity(ConsumeResultActivity.getConsumeFailActivityIntent(ManualconsumptionActivity.this));
                }
            });
        }
    }

    /**
     * descirption: 键盘输入和刷卡操作
     */
    private void formatReadCard(String a, int kind) {
        int companyCode = ChangeTool.HexToInt(a.substring(16, 20));//单位代码
        int number = ChangeTool.HexToInt(a.substring(20, 26));//卡内码
        /*publiccount = ChangeTool.HexToInt(a.substring(26, 30));//消费次数
        publiccount += 1;*/
        if (kind == Constants.KIND_FIND) {
            Log.d(TAG, "formatReadCard: " + companyCode + " " + number + " " + publiccount);
            getReadCard(companyCode, 1, number);
        } else if (kind == Constants.KIND_CONSUME) {
            Log.d(TAG, "formatReadCard: " + companyCode + " " + number + " " + publiccount);
            getReadCardPaycount(companyCode, 1, number);
        }
    }

    /**
     * descirption: 格式化接收到的字节数组
     */
    private void formatHex(byte[] buffer, int size) {
        try {
            int money = ChangeTool.HexToInt(ChangeTool.ByteArrToHex(buffer, 0, size).substring(16, 22));
            Log.d(TAG, "onDataReceive: " + money);
            float m = (float) money / 100;
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String formatmomey = decimalFormat.format(m);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ManualconsumptionKeyboardEnterMoney.setText(formatmomey);
                }
            });
            MainApplication.getSerialPortUtils().sendSerialPort(Constants.SETMONEYOK);
        } catch (Exception e) {
            e.printStackTrace();
            MainApplication.getSerialPortUtils().sendSerialPort(Constants.SETMONEYFAIL);
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
                //展示卡信息
                Log.d(TAG, "onRespnse: getReadcard");
                String name = ChangeTool.ByteArrToHex(getReadCard.getContent().getUserName().getBytes(Charset.forName("GBK"))).replace(" ", "");
                String balance = ChangeTool.ByteArrToHex((getReadCard.getContent().getBalance() + "").getBytes()).replace(" ", "");
                String paycount = ChangeTool.ByteArrToHex((getReadCard.getContent().getPayCount() + "").getBytes()).replace(" ", "");
                String status = ChangeTool.ByteArrToHex((getReadCard.getContent().getState() + "").getBytes()).replace(" ", "");
                int q = 18 - name.length();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < q; i++) {
                    stringBuilder.append("0");
                }
                String namehex = stringBuilder.toString() + name;
                stringBuilder.delete(0, stringBuilder.length());
                q = 6 - balance.length();
                for (int i = 0; i < q; i++) {
                    stringBuilder.append("0");
                }
                String balancehex = stringBuilder.toString() + balance;
                stringBuilder.delete(0, stringBuilder.length());
                q = 4 - paycount.length();
                for (int i = 0; i < q; i++) {
                    stringBuilder.append("0");
                }
                String paycounthex = stringBuilder.toString() + paycount;
                Log.d(TAG, "onRespnse: " + namehex);
                Log.d(TAG, "onRespnse: " + balancehex);
                Log.d(TAG, "onRespnse: " + paycounthex);
                Log.d(TAG, "onRespnse: " + status);
                showDialog(getReadCard);
                int nameint = 0;
                for (int i = 0; i < name.length(); i += 2) {
                    nameint += ChangeTool.HexToInt(name.substring(i, i + 2));
                }
                int balanceint = 0;
                for (int i = 0; i < balance.length(); i += 2) {
                    balanceint += ChangeTool.HexToInt(balance.substring(i, i + 2));
                }
                int paycountint = 0;
                for (int i = 0; i < paycount.length(); i += 2) {
                    paycountint += ChangeTool.HexToInt(paycount.substring(i, i + 2));
                }
                int sum = 19 + nameint + balanceint + paycountint + ChangeTool.HexToInt(status);
                Log.d(TAG, "onRespnse: " + sum);
                String a = ChangeTool.ByteArrToHex((sum + "").getBytes()).replace(" ", "");
                MainApplication.getSerialPortUtils().sendSerialPort("A1B103020101000f" + namehex + balancehex + paycounthex + status + a.substring(a.length() - 2));
                //MainApplication.getSerialPortUtils().sendSerialPort("A1B103020101000f" + namehex +balancehex+paycounthex+status+92);
                Log.d(TAG, "onRespnse: " + "A1B103020101000f" + namehex + balancehex + paycounthex + status + ChangeTool.ByteArrToHex((sum + "").getBytes()));

            }

            @Override
            public void onFail(String err) {

            }
        });
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
                //查询到消费次数之后执行消费
                postSimpleExpense(number, publiccount);
            }

            @Override
            public void onFail(String err) {

            }
        });
    }

    /**
     * descirption: 显示卡信息
     */
    public void showDialog(GetReadCard getReadCard) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ShowCardMessageFragment fragment = ShowCardMessageFragment.newINstance(getReadCard.getContent());
        fragment.show(fragmentManager, Constants.SHOWCARDMESSAGE);
    }

    /**
     * descirption: 刷卡消费
     */
    public void postSimpleExpense(int number, int count) {
        String token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
        String amount = ManualconsumptionKeyboardEnterMoney.getText().toString();
        PostSimpleExpenseBody body = new PostSimpleExpenseBody(number, Double.parseDouble(amount), 1, count, "scy", 1, 2);
        if (token != null) {
            api.postSimpleExpense(body, token, "123");
            api.setGetResponseListener(new Api.GetResponseListener<SimpleExpense>() {
                @Override
                public void onRespnse(SimpleExpense simpleExpense) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ManualconsumptionKeyboardEnterMoney.setText("0.00");
                            //跳转到支付成功界面
                            startActivity(ConsumeResultActivity.getConsumeSuccessActivityIntent(ManualconsumptionActivity.this, simpleExpense.getContent(), "刷卡支付"));
                        }
                    });
                }

                @Override
                public void onFail(String err) {
                    ToastUtils.showShort("支付失败");
                    startActivity(ConsumeResultActivity.getConsumeFailActivityIntent(ManualconsumptionActivity.this));
                }
            });
        }
    }

    @OnClick({R.id.Manualconsumption_back, R.id.Manualconsumption_usesdozensmallnotes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.Manualconsumption_usesdozensmallnotes:
                break;
        }
    }
}
