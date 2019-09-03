package com.moredian.entrance.guard.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/2 13:49
 */
public class GetDepositPage {


    /**
     * Content : {"Count":6,"Rows":[{"SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DetailType":1,"DeviceID":0,"Finance":0,"BeforeBalance":92650,"Amount":3,"Money":0,"Donate":0,"Cost":0,"UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","AfterBalance":92653,"TradeDateTime":"2019-09-02 10:48:29","Operator":"1001","Description":"【线上】充值现金3.0元,充值赠送0.0元,实际现金交易0元,余额92653.00元","Channel":null},{"SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DetailType":1,"DeviceID":0,"Finance":0,"BeforeBalance":92644,"Amount":3,"Money":0,"Donate":3,"Cost":0,"UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","AfterBalance":92650,"TradeDateTime":"2019-09-02 10:47:44","Operator":"1001","Description":"【线上】充值现金3.0元,充值赠送3.0元,实际现金交易0元,余额92650.00元","Channel":null},{"SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DetailType":1,"DeviceID":0,"Finance":0,"BeforeBalance":92643.68,"Amount":0.32,"Money":0,"Donate":0,"Cost":0,"UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","AfterBalance":92644,"TradeDateTime":"2019-09-02 10:47:21","Operator":"1001","Description":"【线上】充值现金0.32元,充值赠送0.0元,实际现金交易0元,余额92644.00元","Channel":null},{"SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DetailType":1,"DeviceID":0,"Finance":0,"BeforeBalance":92633.68,"Amount":10,"Money":0,"Donate":0,"Cost":0,"UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","AfterBalance":92643.68,"TradeDateTime":"2019-09-02 10:40:22","Operator":"1001","Description":"【线上】充值现金10元,充值赠送0元,实际现金交易0元,余额92643.68元","Channel":null},{"SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DetailType":1,"DeviceID":0,"Finance":0,"BeforeBalance":92623.68,"Amount":10,"Money":10,"Donate":0,"Cost":0,"UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","AfterBalance":92633.68,"TradeDateTime":"2019-09-02 10:20:38","Operator":"1001","Description":"【线上】充值现金10元,充值赠送0元,实际现金交易10元,余额92633.68元","Channel":null},{"SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DetailType":1,"DeviceID":0,"Finance":0,"BeforeBalance":46311.84,"Amount":46311.84,"Money":10,"Donate":0,"Cost":0,"UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","AfterBalance":92623.68,"TradeDateTime":"2019-09-02 09:15:00","Operator":"1001","Description":"【线上】充值现金46311.84元,充值赠送0元,实际现金交易10元,余额92623.68元","Channel":null}]}
     * Result : true
     * Message : Success!
     * StatusCode : 200
     */

    private ContentBean Content;
    private boolean Result;
    private String Message;
    private int StatusCode;

    public ContentBean getContent() {
        return Content;
    }

    public void setContent(ContentBean Content) {
        this.Content = Content;
    }

    public boolean isResult() {
        return Result;
    }

    public void setResult(boolean Result) {
        this.Result = Result;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public static class ContentBean {
        /**
         * Count : 6
         * Rows : [{"SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DetailType":1,"DeviceID":0,"Finance":0,"BeforeBalance":92650,"Amount":3,"Money":0,"Donate":0,"Cost":0,"UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","AfterBalance":92653,"TradeDateTime":"2019-09-02 10:48:29","Operator":"1001","Description":"【线上】充值现金3.0元,充值赠送0.0元,实际现金交易0元,余额92653.00元","Channel":null},{"SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DetailType":1,"DeviceID":0,"Finance":0,"BeforeBalance":92644,"Amount":3,"Money":0,"Donate":3,"Cost":0,"UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","AfterBalance":92650,"TradeDateTime":"2019-09-02 10:47:44","Operator":"1001","Description":"【线上】充值现金3.0元,充值赠送3.0元,实际现金交易0元,余额92650.00元","Channel":null},{"SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DetailType":1,"DeviceID":0,"Finance":0,"BeforeBalance":92643.68,"Amount":0.32,"Money":0,"Donate":0,"Cost":0,"UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","AfterBalance":92644,"TradeDateTime":"2019-09-02 10:47:21","Operator":"1001","Description":"【线上】充值现金0.32元,充值赠送0.0元,实际现金交易0元,余额92644.00元","Channel":null},{"SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DetailType":1,"DeviceID":0,"Finance":0,"BeforeBalance":92633.68,"Amount":10,"Money":0,"Donate":0,"Cost":0,"UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","AfterBalance":92643.68,"TradeDateTime":"2019-09-02 10:40:22","Operator":"1001","Description":"【线上】充值现金10元,充值赠送0元,实际现金交易0元,余额92643.68元","Channel":null},{"SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DetailType":1,"DeviceID":0,"Finance":0,"BeforeBalance":92623.68,"Amount":10,"Money":10,"Donate":0,"Cost":0,"UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","AfterBalance":92633.68,"TradeDateTime":"2019-09-02 10:20:38","Operator":"1001","Description":"【线上】充值现金10元,充值赠送0元,实际现金交易10元,余额92633.68元","Channel":null},{"SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DetailType":1,"DeviceID":0,"Finance":0,"BeforeBalance":46311.84,"Amount":46311.84,"Money":10,"Donate":0,"Cost":0,"UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","AfterBalance":92623.68,"TradeDateTime":"2019-09-02 09:15:00","Operator":"1001","Description":"【线上】充值现金46311.84元,充值赠送0元,实际现金交易10元,余额92623.68元","Channel":null}]
         */

        private int Count;
        private List<RowsBean> Rows;

        public int getCount() {
            return Count;
        }

        public void setCount(int Count) {
            this.Count = Count;
        }

        public List<RowsBean> getRows() {
            return Rows;
        }

        public void setRows(List<RowsBean> Rows) {
            this.Rows = Rows;
        }

        public static class RowsBean implements Parcelable {
            /**
             * SerialNo : scy11
             * Name : scy11
             * DepartmentID : 00000000-0000-0000-0000-000000000001
             * EmpID : null
             * IDCard : null
             * Phone : null
             * Number : 22
             * CardTypeID : 1
             * CardTypeName : 正常卡
             * DetailType : 1
             * DeviceID : 0
             * Finance : 0
             * BeforeBalance : 92650
             * Amount : 3
             * Money : 0
             * Donate : 0
             * Cost : 0
             * UserID : 6369e769-ef3b-47fe-b7ce-f7f8e931c29c
             * AfterBalance : 92653
             * TradeDateTime : 2019-09-02 10:48:29
             * Operator : 1001
             * Description : 【线上】充值现金3.0元,充值赠送0.0元,实际现金交易0元,余额92653.00元
             * Channel : null
             */



            private String SerialNo;
            private String Name;
            private String DepartmentID;
            private Object EmpID;
            private Object IDCard;
            private Object Phone;
            private int Number;
            private int CardTypeID;
            private String CardTypeName;
            private int DetailType;
            private int DeviceID;
            private int Finance;
            private double BeforeBalance;
            private double Amount;
            private double Money;
            private double Donate;
            private double Cost;
            private String UserID;
            private double AfterBalance;
            private String TradeDateTime;
            private String Operator;
            private String Description;
            private Object Channel;

            public String getSerialNo() {
                return SerialNo;
            }

            public void setSerialNo(String SerialNo) {
                this.SerialNo = SerialNo;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getDepartmentID() {
                return DepartmentID;
            }

            public void setDepartmentID(String DepartmentID) {
                this.DepartmentID = DepartmentID;
            }

            public Object getEmpID() {
                return EmpID;
            }

            public void setEmpID(Object EmpID) {
                this.EmpID = EmpID;
            }

            public Object getIDCard() {
                return IDCard;
            }

            public void setIDCard(Object IDCard) {
                this.IDCard = IDCard;
            }

            public Object getPhone() {
                return Phone;
            }

            public void setPhone(Object Phone) {
                this.Phone = Phone;
            }

            public int getNumber() {
                return Number;
            }

            public void setNumber(int Number) {
                this.Number = Number;
            }

            public int getCardTypeID() {
                return CardTypeID;
            }

            public void setCardTypeID(int CardTypeID) {
                this.CardTypeID = CardTypeID;
            }

            public String getCardTypeName() {
                return CardTypeName;
            }

            public void setCardTypeName(String CardTypeName) {
                this.CardTypeName = CardTypeName;
            }

            public int getDetailType() {
                return DetailType;
            }

            public void setDetailType(int DetailType) {
                this.DetailType = DetailType;
            }

            public int getDeviceID() {
                return DeviceID;
            }

            public void setDeviceID(int DeviceID) {
                this.DeviceID = DeviceID;
            }

            public int getFinance() {
                return Finance;
            }

            public void setFinance(int Finance) {
                this.Finance = Finance;
            }

            public double getBeforeBalance() {
                return BeforeBalance;
            }

            public void setBeforeBalance(double BeforeBalance) {
                this.BeforeBalance = BeforeBalance;
            }

            public double getAmount() {
                return Amount;
            }

            public void setAmount(double Amount) {
                this.Amount = Amount;
            }

            public double getMoney() {
                return Money;
            }

            public void setMoney(double Money) {
                this.Money = Money;
            }

            public double getDonate() {
                return Donate;
            }

            public void setDonate(double Donate) {
                this.Donate = Donate;
            }

            public double getCost() {
                return Cost;
            }

            public void setCost(double Cost) {
                this.Cost = Cost;
            }

            public String getUserID() {
                return UserID;
            }

            public void setUserID(String UserID) {
                this.UserID = UserID;
            }

            public double getAfterBalance() {
                return AfterBalance;
            }

            public void setAfterBalance(double AfterBalance) {
                this.AfterBalance = AfterBalance;
            }

            public String getTradeDateTime() {
                return TradeDateTime;
            }

            public void setTradeDateTime(String TradeDateTime) {
                this.TradeDateTime = TradeDateTime;
            }

            public String getOperator() {
                return Operator;
            }

            public void setOperator(String Operator) {
                this.Operator = Operator;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
            }

            public Object getChannel() {
                return Channel;
            }

            public void setChannel(Object Channel) {
                this.Channel = Channel;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.SerialNo);
                dest.writeString(this.Name);
                dest.writeString(this.DepartmentID);
                dest.writeInt(this.Number);
                dest.writeInt(this.CardTypeID);
                dest.writeString(this.CardTypeName);
                dest.writeInt(this.DetailType);
                dest.writeInt(this.DeviceID);
                dest.writeInt(this.Finance);
                dest.writeDouble(this.BeforeBalance);
                dest.writeDouble(this.Amount);
                dest.writeDouble(this.Money);
                dest.writeDouble(this.Donate);
                dest.writeDouble(this.Cost);
                dest.writeString(this.UserID);
                dest.writeDouble(this.AfterBalance);
                dest.writeString(this.TradeDateTime);
                dest.writeString(this.Operator);
                dest.writeString(this.Description);
            }

            public RowsBean() {
            }

            protected RowsBean(Parcel in) {
                this.SerialNo = in.readString();
                this.Name = in.readString();
                this.DepartmentID = in.readString();
                this.Number = in.readInt();
                this.CardTypeID = in.readInt();
                this.CardTypeName = in.readString();
                this.DetailType = in.readInt();
                this.DeviceID = in.readInt();
                this.Finance = in.readInt();
                this.BeforeBalance = in.readDouble();
                this.Amount = in.readDouble();
                this.Money = in.readDouble();
                this.Donate = in.readDouble();
                this.Cost = in.readDouble();
                this.UserID = in.readString();
                this.AfterBalance = in.readDouble();
                this.TradeDateTime = in.readString();
                this.Operator = in.readString();
                this.Description = in.readString();
            }

            public static final Parcelable.Creator<RowsBean> CREATOR = new Parcelable.Creator<RowsBean>() {
                @Override
                public RowsBean createFromParcel(Parcel source) {
                    return new RowsBean(source);
                }

                @Override
                public RowsBean[] newArray(int size) {
                    return new RowsBean[size];
                }
            };
        }
    }
}
