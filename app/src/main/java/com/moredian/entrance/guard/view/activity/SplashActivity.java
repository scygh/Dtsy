package com.moredian.entrance.guard.view.activity;

import android.os.Build;
import android.os.Handler;
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
    }

    @Override
    public void initData() {
        api.getDevicePattern(Integer.parseInt(deviceId), token);
        api.setGetResponseListener(new Api.GetResponseListener() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof GetDevicePattern) {
                    int devicePattern = ((GetDevicePattern) o).getContent().getPattern();
                    SPUtils.getInstance().put(Constants.DEVICE_PATTERN, devicePattern);
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
        }, 2500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
