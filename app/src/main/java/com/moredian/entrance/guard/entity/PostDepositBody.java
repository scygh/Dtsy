package com.moredian.entrance.guard.entity;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/2 10:19
 */
public class PostDepositBody {

    /**
     * UserID : string
     * Amount : 0
     * Donate : 0
     */

    private String UserID;
    private double Amount;
    private double Donate;
    private int Channel;
    private double Money;

    public PostDepositBody(String userID, double amount, double donate, int channel, double money) {
        UserID = userID;
        Amount = amount;
        Donate = donate;
        Channel = channel;
        Money = money;
    }

    public PostDepositBody(String userID, double amount) {
        UserID = userID;
        Amount = amount;
    }

    public double getMoney() {
        return Money;
    }

    public void setMoney(double money) {
        Money = money;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public double getDonate() {
        return Donate;
    }

    public void setDonate(double Donate) {
        this.Donate = Donate;
    }

    public int getChannel() {
        return Channel;
    }

    public void setChannel(int channel) {
        Channel = channel;
    }
}
