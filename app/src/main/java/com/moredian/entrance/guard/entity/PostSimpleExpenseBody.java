package com.moredian.entrance.guard.entity;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/12 17:20
 */
public class PostSimpleExpenseBody {


    /**
     * Number : 0
     * Amount : 0
     * Pattern : 1
     * PayCount : 0
     * PayKey : string
     * DeviceID : 0
     * DeviceType : 2
     */

    private int Number;
    private double Amount;
    private int Pattern;
    private int PayCount;
    private String PayKey;
    private int DeviceID;
    private int DeviceType;

    public PostSimpleExpenseBody(int number, double amount, int pattern, int payCount, String payKey, int deviceID, int deviceType) {
        Number = number;
        Amount = amount;
        Pattern = pattern;
        PayCount = payCount;
        PayKey = payKey;
        DeviceID = deviceID;
        DeviceType = deviceType;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        this.Number = Number;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public int getPattern() {
        return Pattern;
    }

    public void setPattern(int Pattern) {
        this.Pattern = Pattern;
    }

    public int getPayCount() {
        return PayCount;
    }

    public void setPayCount(int PayCount) {
        this.PayCount = PayCount;
    }

    public String getPayKey() {
        return PayKey;
    }

    public void setPayKey(String PayKey) {
        this.PayKey = PayKey;
    }

    public int getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(int DeviceID) {
        this.DeviceID = DeviceID;
    }

    public int getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(int DeviceType) {
        this.DeviceType = DeviceType;
    }
}
