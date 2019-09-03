package com.moredian.entrance.guard.view.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.blankj.utilcode.util.SPUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.PostDepositBody;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.utils.ToastHelper;

import butterknife.BindView;

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
