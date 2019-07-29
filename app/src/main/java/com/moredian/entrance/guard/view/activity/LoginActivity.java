package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.moredian.entrance.guard.Constants;
import com.moredian.entrance.guard.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

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

    public static Intent getLoginActivityIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
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
                SPUtils.getInstance().put(Constants.ISLOGIN, true);
                startActivity(MainActivity.getMainActivityIntent(LoginActivity.this));
                finish();
                break;
        }
    }
}
