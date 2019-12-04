package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.moredian.entrance.guard.app.MainApplication;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.entity.GetDevicePattern;
import com.moredian.entrance.guard.entity.GetToken;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.http.ApiUtils;
import com.moredian.entrance.guard.utils.ToastHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.username_tv)
    TextInputEditText usernameTv;
    @BindView(R.id.passsword_tv)
    TextInputEditText passswordTv;
    @BindView(R.id.remenberpassword_rb)
    RadioButton remenberpasswordRb;
    @BindView(R.id.login_btn)
    Button loginBtn;

    public static Intent getLoginActivityIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        String existsUsername = SPUtils.getInstance().getString(Constants.USRTNAME);
        String existsPassword = SPUtils.getInstance().getString(Constants.PASSWORD);
        if (existsUsername != null && existsPassword != null) {
            usernameTv.setText(existsUsername);
            passswordTv.setText(existsPassword);
        }
        api.setGetResponseListener(new Api.GetResponseListener() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof GetDevicePattern) {
                    int devicePattern = ((GetDevicePattern) o).getContent().getPattern();
                    SPUtils.getInstance().put(Constants.DEVICE_PATTERN, devicePattern);
                    startActivity(DsyActivity.getDsyActivityIntent(LoginActivity.this));
                    finish();
                }
            }

            @Override
            public void onFail(String err) {

            }
        });
        api.setOnCreate(new Api.OnCreate() {
            @Override
            public void created() {
                //第一次打开未登录，没有查询结果，所以重复查一次。
                token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
                api.getDevicePattern(Integer.parseInt(deviceId), token);
            }
        });
    }

    @OnClick({R.id.remenberpassword_rb, R.id.login_btn, R.id.iv_come_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.remenberpassword_rb:
                SPUtils.getInstance().put(Constants.USRTNAME, usernameTv.getText().toString().trim());
                SPUtils.getInstance().put(Constants.PASSWORD, passswordTv.getText().toString().trim());
                break;
            case R.id.login_btn:
                String name = usernameTv.getText().toString().trim();
                String password = passswordTv.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    api.getToken(name, password, LoginActivity.this);
                } else {
                    ToastHelper.showToast("用户名或者密码不能为空！");
                }
                break;
            case R.id.iv_come_back:
                finish();
                MainApplication.getSerialPortUtils().closeSerialPort();
                break;
        }
    }
}
