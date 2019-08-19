package com.moredian.entrance.guard.view.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.moredian.entrance.guard.http.Api;

import butterknife.ButterKnife;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/19 14:08
 */
public abstract class BaseActivity extends AppCompatActivity {

    public Api api;
    public abstract int layoutView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutView());
        ButterKnife.bind(this);
        api = new Api();
        setLightMode();
        initView();
        initData();
    }

    public abstract void initView();

    public abstract void initData();

    /**
     * Android 6.0 以上设置状态栏颜色
     */
    private void setLightMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 设置状态栏底色白色
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(Color.WHITE);
            // 设置状态栏字体黑色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
