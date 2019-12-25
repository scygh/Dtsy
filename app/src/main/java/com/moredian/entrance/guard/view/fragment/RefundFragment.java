package com.moredian.entrance.guard.view.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.blankj.utilcode.util.SPUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.app.MainApplication;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.PostDepositBody;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.utils.ToastHelper;
import com.moredian.entrance.guard.view.activity.VoucherCenterActivity;

import java.text.DecimalFormat;

import android_serialport_api.ChangeTool;
import android_serialport_api.SerialPortUtils;

import static com.blankj.utilcode.util.Utils.runOnUiThread;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/12 10:10
 */
public class RefundFragment extends DialogFragment {

    TextInputEditText refundEt;
    Button refundBtn;

    public static RefundFragment newInstance(String userid) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE_USERID, userid);
        RefundFragment fragment = new RefundFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainApplication.getSerialPortUtils().setOnDataReceiveListener(new SerialPortUtils.OnDataReceiveListener() {
            @Override
            public void onDataReceive(byte[] buffer, int size) {
                String a = ChangeTool.ByteArrToHex(buffer, 0, size);
                if (a.length() == 24) {//接收到键盘输入金额
                    formatHex(buffer, size);
                }
            }
        });
    }

    private void formatHex(byte[] buffer, int size) {
        try {
            int money = ChangeTool.HexToInt(ChangeTool.ByteArrToHex(buffer, 0, size).substring(16, 22));
            float m = (float) money / 100;
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String formatmomey = decimalFormat.format(m);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    refundEt.setText(formatmomey);
                }
            });
            MainApplication.getSerialPortUtils().sendSerialPort(Constants.SETMONEYOK);
        } catch (Exception e) {
            e.printStackTrace();
            MainApplication.getSerialPortUtils().sendSerialPort(Constants.SETMONEYFAIL);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.refund_layout, null, false);
        Bundle bundle = getArguments();
        String userid = bundle.getString(Constants.BUNDLE_USERID);
        refundEt = view.findViewById(R.id.refund_et);
        refundBtn = view.findViewById(R.id.refund_btn);
        refundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Api api = new Api();
                String token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
                String money = refundEt.getText().toString();
                if (!TextUtils.isEmpty(money)) {
                    PostDepositBody body = new PostDepositBody(userid, Double.parseDouble(money));
                    api.postRefund(token,body);
                    RefundFragment.this.dismiss();
                } else {
                    ToastHelper.showToast("请输入退款金额");
                }
            }
        });
        return new AlertDialog.Builder(getActivity())
                .setTitle("退款")
                .setView(view)
                .create();
    }

    @Override
    public void dismiss() {
        try {
            VoucherCenterActivity fragmentActivity = (VoucherCenterActivity) getActivity();
            // 触发背景 activity 重新 onresume
            Intent intent = new Intent(getActivity(), fragmentActivity.getClass());
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        } catch (NullPointerException e) {
            Log.i("scy", "dialog dismiss, resume activity failed, null pointer exception occurs");
        }

        super.dismiss();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        MainApplication.getSerialPortUtils().setOnDataReceiveListenerNull();
    }
}
