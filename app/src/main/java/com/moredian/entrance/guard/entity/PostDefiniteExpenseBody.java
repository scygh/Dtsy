package com.moredian.entrance.guard.entity;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/15 16:11
 */
public class PostDefiniteExpenseBody {

    /**
     * Number : 0
     * Amount : 0
     * PayCount : 0
     * PayKey : string
     * DeviceID : 0
     */

    private int Number;
    private double Amount;
    private int PayCount;
    private String PayKey;
    private int DeviceID;

    public PostDefiniteExpenseBody(int number, double amount, int payCount, String payKey, int deviceID) {
        Number = number;
        Amount = amount;
        PayCount = payCount;
        PayKey = payKey;
        DeviceID = deviceID;
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
}
