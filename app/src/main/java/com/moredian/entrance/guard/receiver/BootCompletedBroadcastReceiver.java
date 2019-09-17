package com.moredian.entrance.guard.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.moredian.entrance.guard.view.activity.SplashActivity;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/17 16:35
 */
public class BootCompletedBroadcastReceiver extends BroadcastReceiver {

    private final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_BOOT.equals(intent.getAction())) {
            Intent i = new Intent(context,SplashActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
