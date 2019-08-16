package com.moredian.entrance.guard.entity;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/15 16:11
 */
public class PostDefiniteExpenseBody {

    /**
     * Amount : 0
     * Pattern : 1
     * PayCount : 0
     * DeviceID : 0
     * DeviceType : 2
     */

    private double Amount;
    private int Pattern;
    private int PayCount;
    private int DeviceID;
    private int DeviceType;

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
