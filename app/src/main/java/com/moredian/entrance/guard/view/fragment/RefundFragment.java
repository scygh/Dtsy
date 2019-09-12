package com.moredian.entrance.guard.view.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.blankj.utilcode.util.SPUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.app.MainApplication;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.PostDepositBody;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.utils.KeyBoardUtil;
import com.moredian.entrance.guard.utils.ToastHelper;

import java.text.DecimalFormat;

import android_serialport_api.ChangeTool;
import android_serialport_api.SerialPortUtils;
import butterknife.BindView;

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
}
