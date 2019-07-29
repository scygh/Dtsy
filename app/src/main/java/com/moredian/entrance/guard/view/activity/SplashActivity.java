package com.moredian.entrance.guard.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.moredian.entrance.guard.Constants;
import com.moredian.entrance.guard.R;

public class SplashActivity extends AppCompatActivity {
    SplashHandler handler = null;
    SplashTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        handler = new SplashHandler();
        task = new SplashTask();
        handler.postDelayed(task, 2000);
    }

    private class SplashTask implements Runnable {
        @Override
        public void run() {
            if (SPUtils.getInstance().getBoolean(Constants.ISLOGIN)) {
                SplashActivity.this.startActivity(MainActivity.getMainActivityIntent(SplashActivity.this));
            } else {
                SplashActivity.this.startActivity(LoginActivity.getLoginActivityIntent(SplashActivity.this));
            }
            finish();
        }
    }

    private static class SplashHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
