package com.moredian.entrance.guard.utils;

import android.content.Context;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.app.MainApplication;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/22 10:47
 */
public class ToastHelper {


    public static Toast mToast = null;

    /**
     * 弹出Toast
     *
     * @param text 提示的文本
     */
    public static void showToast(String text) {
        try {
            View view = LayoutInflater.from(MainApplication.getContext()).inflate(R.layout.toast_helper_layout, null, false);
            TextView textView = view.findViewById(R.id.tv_issue);
            textView.setText(text);
            if (mToast == null) {
                mToast = new Toast(MainApplication.getContext());
            } else {
                textView.setText(text);
            }
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setView(view);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.show();
        } catch (Exception e) {
            Looper.prepare();
            Toast.makeText(MainApplication.getContext(), text, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    /**
     * 关闭Toast
     */
    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
