package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.app.MainApplication;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetChannel;
import com.moredian.entrance.guard.entity.GetReadCard;
import com.moredian.entrance.guard.entity.GetUser;
import com.moredian.entrance.guard.entity.PostDepositBody;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.utils.ToastHelper;
import com.moredian.entrance.guard.view.adapter.SpinnerAdapter;
import com.moredian.entrance.guard.view.fragment.RefundFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android_serialport_api.ChangeTool;
import android_serialport_api.SerialPortUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VoucherCenterActivity extends BaseActivity implements View.OnFocusChangeListener {

    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.avc_name)
    TextView avcName;
    @BindView(R.id.avc_balance)
    TextView avcBalance;
    @BindView(R.id.fpa_cash_et)
    TextInputEditText fpaCashEt;
    @BindView(R.id.fpa_donate_et)
    TextInputEditText fpaDonateEt;
    @BindView(R.id.deposit)
    Button deposit;
    String userId;
    @BindView(R.id.spinner_channel)
    Spinner spinnerChannel;
    @BindView(R.id.refund)
    ImageView refund;
    private int number;

    public static Intent getVoucherCenterActivityIntent(Context context) {
        Intent intent = new Intent(context, VoucherCenterActivity.class);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.activity_voucher_center;
    }

    @Override
    public void initView() {
        pageName.setText("充值");
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    @Override
    public void initData() {
        api.getChannel(token);
        api.setGetResponseListener(new Api.GetResponseListener<Object>() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof GetReadCard) {
                    Log.d("scy", "GetReadCard: ");
                    String name = ((GetReadCard) o).getContent().getUserName();
                    double balance = ((GetReadCard) o).getContent().getBalance();
                    int paycount = ((GetReadCard) o).getContent().getPayCount();
                    int status = ((GetReadCard) o).getContent().getState();
                    String namehex = getNameHex(name);
                    String balancehex = ChangeTool.numToHex3((int) (balance * 100));
                    String paycounthex = ChangeTool.numToHex2(paycount);
                    String statushex = ChangeTool.numToHex1(status);
                    String sum = "020101000f" + namehex + balancehex + paycounthex + statushex;
                    MainApplication.getSerialPortUtils().sendSerialPort("A1B103020101000f" + namehex + balancehex + paycounthex + statushex + ChangeTool.makeChecksum(sum));
                    showCardData(name, balance);
                    api.getUser(token, number);
                } else if (o instanceof GetUser) {
                    userId = ((GetUser) o).getContent().getUserID();
                } else if (o instanceof GetChannel) {
                    List<String> channel = new ArrayList<>();
                    channel.add(((GetChannel) o).getContent().get_$0());
                    channel.add(((GetChannel) o).getContent().get_$101());
                    channel.add(((GetChannel) o).getContent().get_$102());
                    channel.add(((GetChannel) o).getContent().get_$103());
                    channel.add(((GetChannel) o).getContent().get_$104());
                    String[] array = channel.toArray(new String[channel.size()]);
                    spinnerChannel.setAdapter(new SpinnerAdapter(VoucherCenterActivity.this, array));
                }
            }

            @Override
            public void onFail(String err) {
                ToastHelper.showToast("读卡失败");
            }
        });
        MainApplication.getSerialPortUtils().setOnDataReceiveListener(new SerialPortUtils.OnDataReceiveListener() {
            @Override
            public void onDataReceive(byte[] buffer, int size) {
                Log.d("scy", "onDataReceive: ");
                String a = ChangeTool.ByteArrToHex(buffer, 0, size);
                if (a.length() == 32) {
                    formatReadCard(a, Constants.KIND_FIND);
                } else if (a.length() == 24) {//接收到键盘输入金额
                    formatHex(buffer, size);
                }
            }
        });
        fpaCashEt.setOnFocusChangeListener(this);
        fpaDonateEt.setOnFocusChangeListener(this);
    }

    int flag = -1;

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.fpa_cash_et:
                if (b) {
                    flag = 1;
                }
                break;
            case R.id.fpa_donate_et:
                if (b) {
                    flag = 2;
                }
                break;
        }
    }

    /**
     * descirption: 把接收到刷卡的16进制数转化去消费查询
     */
    private void formatReadCard(String a, int kind) {
        int companyCode = ChangeTool.HexToInt(a.substring(16, 20));//单位代码
        number = ChangeTool.HexToInt(a.substring(20, 26));//卡内码
        if (kind == Constants.KIND_FIND) {
            getReadCard(companyCode, 1, number);
        }
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
                    if (flag == 1) {
                        fpaCashEt.setText(formatmomey);
                    } else if (flag == 2) {
                        fpaDonateEt.setText(formatmomey);
                    }
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
        api.getReadCard(companyCode, diviceID, number, token, Constants.MODIAN_TOKEN);
    }

    private void showCardData(String name, double balance) {
        avcName.setText(name);
        avcBalance.setText(balance + "");
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

    @OnClick({R.id.Manualconsumption_back, R.id.deposit, R.id.refund})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.refund:
                if (!TextUtils.isEmpty(userId)) {
                    RefundFragment fragment = RefundFragment.newInstance(userId);
                    fragment.show(getSupportFragmentManager(), "refund");
                } else {
                    ToastHelper.showToast("请先刷卡");
                }

                break;
            case R.id.deposit:
                String deposit = fpaCashEt.getText().toString();
                String donate = fpaDonateEt.getText().toString();
                String channel = (String) spinnerChannel.getSelectedItem();
                fpaCashEt.setText("");
                fpaDonateEt.setText("");
                spinnerChannel.setSelection(0);
                int c = 0;
                if (channel.equals("线上")) {
                    c = 0;
                } else if (channel.equals("支付宝转账")) {
                    c = 101;
                } else if (channel.equals("微信转账")) {
                    c = 102;
                } else if (channel.equals("银行卡转账")) {
                    c = 103;
                } else if (channel.equals("其他转账")) {
                    c = 104;
                }
                PostDepositBody body = null;
                if (TextUtils.isEmpty(deposit)) {
                    ToastHelper.showToast("请输入充值金额");
                } else {
                    if (!TextUtils.isEmpty(userId)) {
                        if (!TextUtils.isEmpty(donate)) {
                            body = new PostDepositBody(userId, Double.parseDouble(deposit), Double.parseDouble(donate), c);
                        } else {
                            body = new PostDepositBody(userId, Double.parseDouble(deposit), 0.0, c);
                        }
                        api.postDeposit(token, body);
                    } else {
                        ToastHelper.showToast("请先刷卡");
                    }
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("scy", "onResume: ");
        api.setGetResponseListener(new Api.GetResponseListener<Object>() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof GetReadCard) {
                    Log.d("scy", "GetReadCard: ");
                    String name = ((GetReadCard) o).getContent().getUserName();
                    double balance = ((GetReadCard) o).getContent().getBalance();
                    int paycount = ((GetReadCard) o).getContent().getPayCount();
                    int status = ((GetReadCard) o).getContent().getState();
                    String namehex = getNameHex(name);
                    String balancehex = ChangeTool.numToHex3((int) (balance * 100));
                    String paycounthex = ChangeTool.numToHex2(paycount);
                    String statushex = ChangeTool.numToHex1(status);
                    String sum = "020101000f" + namehex + balancehex + paycounthex + statushex;
                    MainApplication.getSerialPortUtils().sendSerialPort("A1B103020101000f" + namehex + balancehex + paycounthex + statushex + ChangeTool.makeChecksum(sum));
                    showCardData(name, balance);
                    api.getUser(token, number);
                } else if (o instanceof GetUser) {
                    userId = ((GetUser) o).getContent().getUserID();
                } else if (o instanceof GetChannel) {
                    List<String> channel = new ArrayList<>();
                    channel.add(((GetChannel) o).getContent().get_$0());
                    channel.add(((GetChannel) o).getContent().get_$101());
                    channel.add(((GetChannel) o).getContent().get_$102());
                    channel.add(((GetChannel) o).getContent().get_$103());
                    channel.add(((GetChannel) o).getContent().get_$104());
                    String[] array = channel.toArray(new String[channel.size()]);
                    spinnerChannel.setAdapter(new SpinnerAdapter(VoucherCenterActivity.this, array));
                }
            }

            @Override
            public void onFail(String err) {
                ToastHelper.showToast("读卡失败");
            }
        });
        MainApplication.getSerialPortUtils().setOnDataReceiveListener(new SerialPortUtils.OnDataReceiveListener() {
            @Override
            public void onDataReceive(byte[] buffer, int size) {
                Log.d("scy", "onDataReceive: ");
                String a = ChangeTool.ByteArrToHex(buffer, 0, size);
                if (a.length() == 32) {
                    formatReadCard(a, Constants.KIND_FIND);
                } else if (a.length() == 24) {//接收到键盘输入金额
                    formatHex(buffer, size);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainApplication.getSerialPortUtils().setOnDataReceiveListenerNull();
    }
}
