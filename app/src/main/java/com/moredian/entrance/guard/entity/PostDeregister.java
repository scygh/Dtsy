package com.moredian.entrance.guard.entity;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/28 10:31
 */
public class PostDeregister {

    /**
     * UserID : string
     * Money : 0
     * Cost : 0
     */

    private String UserID;
    private double Money;
    private double Cost;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public double getMoney() {
        return Money;
    }

    public void setMoney(double Money) {
        this.Money = Money;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double Cost) {
        this.Cost = Cost;
    }
}
