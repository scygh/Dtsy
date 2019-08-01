package com.moredian.entrance.guard.face;

import java.lang.reflect.Method;

public class DeviceType {
    static String getprop(String key, String defaultValue) {

        String value = defaultValue;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class );
            value = (String)(get.invoke(c, key, "unknown" ));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return value;
        }
    }


    public static boolean isD2(){
        return getprop("ro.product.model","unkonw").contentEquals("D2");
    }
    public static boolean isG2(){
        String model = getprop("ro.product.model","unkonw");
        return model.contentEquals("G2")||model.contentEquals("D2_Mini");
    }

    public static boolean isMg1(){
        String model = getprop("ro.product.model","unkonw");
        return model.contentEquals("MG1");
    }
}
