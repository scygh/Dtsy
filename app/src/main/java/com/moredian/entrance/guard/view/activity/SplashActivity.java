package com.moredian.entrance.guard.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.blankj.utilcode.util.SPUtils;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.entity.GetDevicePattern;
import com.moredian.entrance.guard.http.Api;

public class SplashActivity extends BaseActivity {

    @Override
    public int layoutView() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        api.getDevicePattern(Integer.parseInt(deviceId), token);
        api.setGetResponseListener(new Api.GetResponseListener() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof GetDevicePattern) {
                    int devicePattern = ((GetDevicePattern) o).getContent().getPattern();
                    SPUtils.getInstance().put(Constants.DEVICE_PATTERN,devicePattern);
                }
            }

            @Override
            public void onFail(String err) {

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SPUtils.getInstance().getBoolean(Constants.ISLOGIN)) {
                    startActivity(DsyActivity.getDsyActivityIntent(SplashActivity.this));
                } else {
                    startActivity(LoginActivity.getLoginActivityIntent(SplashActivity.this));
                }
                finish();
            }
        },2500);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
