package com.moredian.entrance.guard.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/26 09:26
 */
public class PostRegister implements Parcelable {

    /**
     * address :
     * age : 0
     * authType : 0
     * cardState : 1
     * cardType : 1
     * cash : 2220
     * cost : 0
     * deadline : 2029-06-30 00:00:00
     * departmentID : 00000000-0000-0000-0000-000000000001
     * departmentName : 默认部门
     * discount : false
     * discountRate : 0
     * donate : 0
     * empID : 工号
     * foregift : 0
     * iDCard :
     * integral : 0
     * isGotCard : 0 、
     * name : 222
     * number : 30
     * payCount : 0
     * payKey :
     * phone :
     * serialNo : 2222
     * sex : 0
     * subsidy : 0
     * subsidyDatediff : 0
     * subsidyLevel : 0
     * times : 0
     * userCreateTime : 2019-08-22 05:42:10
     * userState : 0
     */

    private String address;
    private int age;
    private int authType;
    private int cardState;
    private int cardType;
    private double cash;
    private double cost;
    private String deadline;
    private String departmentID;
    private String departmentName;
    private boolean discount;
    private int discountRate;
    private double donate;
    private String empID;
    private int foregift;
    private String iDCard;
    private int integral;
    private int isGotCard;
    private String name;
    private int number;
    private int payCount;
    private String payKey;
    private String phone;
    private String serialNo;
    private int sex;
    private double subsidy;
    private int subsidyDatediff;
    private int subsidyLevel;
    private int times;
    private String userCreateTime;
    private int userState;



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAuthType() {
        return authType;
    }

    public void setAuthType(int authType) {
        this.authType = authType;
    }

    public int getCardState() {
        return cardState;
    }

    public void setCardState(int cardState) {
        this.cardState = cardState;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public double getDonate() {
        return donate;
    }

    public void setDonate(double donate) {
        this.donate = donate;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public int getForegift() {
        return foregift;
    }

    public void setForegift(int foregift) {
        this.foregift = foregift;
    }

    public String getIDCard() {
        return iDCard;
    }

    public void setIDCard(String iDCard) {
        this.iDCard = iDCard;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getIsGotCard() {
        return isGotCard;
    }

    public void setIsGotCard(int isGotCard) {
        this.isGotCard = isGotCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPayCount() {
        return payCount;
    }

    public void setPayCount(int payCount) {
        this.payCount = payCount;
    }

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public double getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(double subsidy) {
        this.subsidy = subsidy;
    }

    public int getSubsidyDatediff() {
        return subsidyDatediff;
    }

    public void setSubsidyDatediff(int subsidyDatediff) {
        this.subsidyDatediff = subsidyDatediff;
    }

    public int getSubsidyLevel() {
        return subsidyLevel;
    }

    public void setSubsidyLevel(int subsidyLevel) {
        this.subsidyLevel = subsidyLevel;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(String userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeInt(this.age);
        dest.writeInt(this.authType);
        dest.writeInt(this.cardState);
        dest.writeInt(this.cardType);
        dest.writeDouble(this.cash);
        dest.writeDouble(this.cost);
        dest.writeString(this.deadline);
        dest.writeString(this.departmentID);
        dest.writeString(this.departmentName);
        dest.writeByte(this.discount ? (byte) 1 : (byte) 0);
        dest.writeInt(this.discountRate);
        dest.writeDouble(this.donate);
        dest.writeString(this.empID);
        dest.writeInt(this.foregift);
        dest.writeString(this.iDCard);
        dest.writeInt(this.integral);
        dest.writeInt(this.isGotCard);
        dest.writeString(this.name);
        dest.writeInt(this.number);
        dest.writeInt(this.payCount);
        dest.writeString(this.payKey);
        dest.writeString(this.phone);
        dest.writeString(this.serialNo);
        dest.writeInt(this.sex);
        dest.writeDouble(this.subsidy);
        dest.writeInt(this.subsidyDatediff);
        dest.writeInt(this.subsidyLevel);
        dest.writeInt(this.times);
        dest.writeString(this.userCreateTime);
        dest.writeInt(this.userState);
    }

    public PostRegister() {
    }

    protected PostRegister(Parcel in) {
        this.address = in.readString();
        this.age = in.readInt();
        this.authType = in.readInt();
        this.cardState = in.readInt();
        this.cardType = in.readInt();
        this.cash = in.readDouble();
        this.cost = in.readDouble();
        this.deadline = in.readString();
        this.departmentID = in.readString();
        this.departmentName = in.readString();
        this.discount = in.readByte() != 0;
        this.discountRate = in.readInt();
        this.donate = in.readDouble();
        this.empID = in.readString();
        this.foregift = in.readInt();
        this.iDCard = in.readString();
        this.integral = in.readInt();
        this.isGotCard = in.readInt();
        this.name = in.readString();
        this.number = in.readInt();
        this.payCount = in.readInt();
        this.payKey = in.readString();
        this.phone = in.readString();
        this.serialNo = in.readString();
        this.sex = in.readInt();
        this.subsidy = in.readDouble();
        this.subsidyDatediff = in.readInt();
        this.subsidyLevel = in.readInt();
        this.times = in.readInt();
        this.userCreateTime = in.readString();
        this.userState = in.readInt();
    }

    public static final Parcelable.Creator<PostRegister> CREATOR = new Parcelable.Creator<PostRegister>() {
        @Override
        public PostRegister createFromParcel(Parcel source) {
            return new PostRegister(source);
        }

        @Override
        public PostRegister[] newArray(int size) {
            return new PostRegister[size];
        }
    };
}
