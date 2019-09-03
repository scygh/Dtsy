package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android_serialport_api.ChangeTool;
import android_serialport_api.SerialPortUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VoucherCenterActivity extends BaseActivity {

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
    }

    @Override
    public void initData() {
        api.getChannel(token);
        api.setGetResponseListener(new Api.GetResponseListener<Object>() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof GetReadCard) {
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
                String a = ChangeTool.ByteArrToHex(buffer, 0, size);
                if (a.length() == 32) {
                    formatReadCard(a, Constants.KIND_FIND);
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
        }
    }

    /**
     * descirption: 查询卡信息
     */
    public void getReadCard(Integer companyCode, Integer diviceID, Integer number) {
        api.getReadCard(companyCode, diviceID, number, token, Constants.MODIAN_TOKEN);
        api.getUser(token, number);
    }

    private void showCardData(String name, double balance) {
        avcName.setText(name);
        avcBalance.setText(balance + "");
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

    @OnClick({R.id.Manualconsumption_back, R.id.deposit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.deposit:
                String deposit = fpaCashEt.getText().toString();
                String donate = fpaDonateEt.getText().toString();
                String channel = (String) spinnerChannel.getSelectedItem();
                PostDepositBody body = null;
                if (TextUtils.isEmpty(deposit)) {
                    ToastHelper.showToast("请输入充值金额");
                } else {
                    if (!TextUtils.isEmpty(userId)) {
                        if (!TextUtils.isEmpty(donate)) {
                            body = new PostDepositBody(userId, Double.parseDouble(deposit), Double.parseDouble(donate), channel);
                        } else {
                            body = new PostDepositBody(userId, Double.parseDouble(deposit), 0.0, channel);
                        }
                        api.postDeposit(token, body);
                    } else {
                        ToastHelper.showToast("请先刷卡");
                    }
                }
                break;
        }
    }

}
