package com.moredian.entrance.guard.app;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.utils.AudioUtils;

import android_serialport_api.SerialPortUtils;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/25 14:53
 */
public class MainApplication extends Application {

    private static Context context;
    private static SerialPortUtils serialPortUtils;

    public static Context getContext() {
        return context;
    }

    public static SerialPortUtils getSerialPortUtils() {
        return serialPortUtils;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        serialPortUtils = new SerialPortUtils();
        String port = SPUtils.getInstance().getString(Constants.MACHINE_PORT, Constants.SERIALPORT);
        String baudrate = SPUtils.getInstance().getString(Constants.MACHINE_BAUDRTE, Constants.BAUDRATE);
        serialPortUtils.openSerialPort(port, Integer.parseInt(baudrate));
        SpeechUtility.createUtility(context, SpeechConstant.APPID + "=12345678");

    }
}
