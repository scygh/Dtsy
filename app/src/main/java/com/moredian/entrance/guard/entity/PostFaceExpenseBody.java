package com.moredian.entrance.guard.entity;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/13 15:29
 */
public class PostFaceExpenseBody {

    /**
     * MemberId : string
     * Amount : 0.1
     * Pattern : 1
     * DeviceID : 0
     * DeviceType : 2
     */

    private String MemberId;
    private double Amount;
    private int Pattern;
    private int DeviceID;
    private int DeviceType;

    public PostFaceExpenseBody(String memberId, double amount, int pattern, int deviceID, int deviceType) {
        MemberId = memberId;
        Amount = amount;
        Pattern = pattern;
        DeviceID = deviceID;
        DeviceType = deviceType;
    }

    public String getMemberId() {
        return MemberId;
    }

    public void setMemberId(String MemberId) {
        this.MemberId = MemberId;
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
