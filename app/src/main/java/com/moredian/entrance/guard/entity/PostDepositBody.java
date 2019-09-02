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

    public PostDepositBody(String userID, double amount, double donate) {
        UserID = userID;
        Amount = amount;
        Donate = donate;
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
}
