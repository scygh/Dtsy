package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.app.MainApplication;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetChannel;
import com.moredian.entrance.guard.entity.GetReadCard;
import com.moredian.entrance.guard.entity.GetUser;
import com.moredian.entrance.guard.entity.GetUserByUserID;
import com.moredian.entrance.guard.entity.PostDepositBody;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.utils.ToastHelper;
import com.moredian.entrance.guard.view.adapter.SpinnerAdapter;
import com.moredian.entrance.guard.view.fragment.RefundFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
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
    @BindView(R.id.refund)
    ImageView refund;

    public static Intent getVoucherCenterActivityIntent(Context context, String userId) {
        Intent intent = new Intent(context, VoucherCenterActivity.class);
        intent.putExtra("userid", userId);
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
        userId = getIntent().getStringExtra("userid");
        api.getUserByuserID(userId, token);
        api.setGetResponseListener(new Api.GetResponseListener<Object>() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof GetChannel) {
                    List<String> channel = new ArrayList<>();
                    for (int i = 0; i < ((GetChannel) o).getContent().size(); i++) {
                        channel.add(((GetChannel) o).getContent().get(i).getText());
                    }
                    spinnerChannel.setAdapter(new SpinnerAdapter(VoucherCenterActivity.this, channel.toArray(new String[channel.size()])));
                } else if (o instanceof GetUserByUserID) {
                    showData(((GetUserByUserID) o).getContent().getName(), ((GetUserByUserID) o).getContent().getCash());
                }
            }

            @Override
            public void onFail(String err) {
                ToastHelper.showToast("数据加载失败");
            }
        });
    }

    private void showData(String name, double balance) {
        avcName.setText(name);
        avcBalance.setText(balance + "");
    }

    @OnClick({R.id.Manualconsumption_back, R.id.deposit, R.id.refund})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.refund:
                break;
            case R.id.deposit:
                String deposit = fpaCashEt.getText().toString();
                String donate = fpaDonateEt.getText().toString();
                String channel = (String) spinnerChannel.getSelectedItem();
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
                        ToastHelper.showToast("userId为空");
                    }
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
