package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.entity.GetToken;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.http.ApiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.username_tv)
    TextInputEditText usernameTv;
    @BindView(R.id.passsword_tv)
    TextInputEditText passswordTv;
    @BindView(R.id.password_cansee_iv)
    ImageView passwordCanseeIv;
    @BindView(R.id.remenberpassword_rb)
    RadioButton remenberpasswordRb;
    @BindView(R.id.login_btn)
    Button loginBtn;
    private Api api;

    public static Intent getLoginActivityIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        api = new Api();
        if (usernameTv != null && passswordTv != null) {
            String existsUsername = SPUtils.getInstance().getString(Constants.USRTNAME);
            String existsPassword = SPUtils.getInstance().getString(Constants.PASSWORD);
            if (existsUsername != null && existsPassword != null) {
                usernameTv.setText(existsUsername);
                passswordTv.setText(existsPassword);
            }
        }
    }

    @OnClick({R.id.remenberpassword_rb, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.remenberpassword_rb:
                SPUtils.getInstance().put(Constants.USRTNAME, usernameTv.getText().toString().trim());
                SPUtils.getInstance().put(Constants.PASSWORD, passswordTv.getText().toString().trim());
                break;
            case R.id.login_btn:
                String name =  usernameTv.getText().toString().trim();
                String password = passswordTv.getText().toString().trim();
                api.getToken(name,password,LoginActivity.this);
                break;
        }
    }
}
