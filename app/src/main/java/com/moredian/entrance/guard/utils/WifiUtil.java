package com.moredian.entrance.guard.utils;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/30 10:35
 */
public class WifiUtil {
    private static WifiManager wifiManager;

    /**
     * descirption: 获取当前连接wifi名字 内存溢出？
     */
    public static String getCurrentWifiSSID(Context context) {
        context = context.getApplicationContext();
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        return info != null ? info.getSSID() : "null";
    }

    /**
     * descirption: 扫描附近WIFI返回结果 注意权限，延时，定位
     */
    public static List<ScanResult> getScanResult(WifiManager mWifiManager) {
        mWifiManager.startScan();
        List<ScanResult> results = mWifiManager.getScanResults();
        if (results.size() > 0) {
            //先排序
            results = sortList(results);
            //删除空值
            Iterator<ScanResult> iterator = results.iterator();
            if (iterator.hasNext()) {
                ScanResult scanResult = iterator.next();
                String s = "\"" + scanResult.SSID + "\"";
                if (s.equals("")) {
                    iterator.remove();
                }
            }
            //去重
            removeDuplicate(results);
            //再取前10
            results = results.subList(0, 10);
        }
        return results;
    }

    /**
     * descirption: 按照信号强度排序
     */
    public static List<ScanResult> sortList(List<ScanResult> list) {

        Collections.sort(list, new Comparator<ScanResult>() {
            @Override
            public int compare(ScanResult o1, ScanResult o2) {
                return o2.level - o1.level;
            }
        });
        return list;
    }

    /**
     * descirption: 去掉重复Scanresult
     */
    private static void removeDuplicate(List<ScanResult> list) {
        LinkedHashSet<ScanResult> set = new LinkedHashSet<ScanResult>(list.size());
        set.addAll(list);
        list.clear();
        list.addAll(set);
    }

}
