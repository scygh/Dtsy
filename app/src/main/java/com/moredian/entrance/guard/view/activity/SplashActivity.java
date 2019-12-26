package com.moredian.entrance.guard.view.activity;

import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.view.WindowManager;

import com.blankj.utilcode.util.SPUtils;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.entity.GetDevicePattern;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.utils.ToastHelper;

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
        //如果登录过了才会在这个界面去请求消费模式
        if (!TextUtils.isEmpty(token)) {
            api.getDevicePattern(Integer.parseInt(deviceId), token);
        }
        api.setGetResponseListener(new Api.GetResponseListener() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof GetDevicePattern) {
                    int devicePattern = ((GetDevicePattern) o).getContent().getPattern();
                    String pattern = "";
                    if (devicePattern == 1) {
                        pattern = "手动消费";
                    } else if (devicePattern == 2) {
                        pattern = "自动消费";
                        SPUtils.getInstance().put(Constants.AUTO_AMOUNT, ((GetDevicePattern) o).getContent().getAutoAmount(),true);
                    } else if (devicePattern == 3) {
                        pattern = "定值消费";
                    }
                    SPUtils.getInstance().put(Constants.DEVICE_PATTERN, pattern);
                }
            }

            @Override
            public void onFail(String err) {
                ToastHelper.showToast(err);
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
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
