package com.moredian.entrance.guard.entity;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/20 11:12
 */
public class PostsetDevicePattern {

    /**
     * AutoAmount : string
     * Pattern : string
     */

    private String AutoAmount;
    private String Pattern;

    public PostsetDevicePattern(String autoAmount, String pattern) {
        AutoAmount = autoAmount;
        Pattern = pattern;
    }

    public String getAutoAmount() {
        return AutoAmount;
    }

    public void setAutoAmount(String AutoAmount) {
        this.AutoAmount = AutoAmount;
    }

    public String getPattern() {
        return Pattern;
    }

    public void setPattern(String Pattern) {
        this.Pattern = Pattern;
    }
}
