package com.moredian.entrance.guard.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/12 17:16
 */
public class SimpleExpense {

    /**
     * Content : {"ID":"b80be3c7-e714-47b8-8ac7-f9dc5cd6bc58","UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","Number":22,"DeviceType":2,"DeviceID":1,"Pattern":1,"DetailType":0,"PayCount":10,"Finance":0,"OriginalAmount":0.99,"Amount":0.99,"Balance":19996,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-13 14:48:50","CreateTime":"2019-08-13 14:48:50","Description":"【手动消费】扣款合计0.99元;账户实际扣款0.99元;账户余额合计19996.00元.","OfflinePayCount":null}
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

    public static class ContentBean implements Parcelable {
        /**
         * ID : b80be3c7-e714-47b8-8ac7-f9dc5cd6bc58
         * UserID : 6369e769-ef3b-47fe-b7ce-f7f8e931c29c
         * Number : 22
         * DeviceType : 2
         * DeviceID : 1
         * Pattern : 1
         * DetailType : 0
         * PayCount : 10
         * Finance : 0
         * OriginalAmount : 0.99
         * Amount : 0.99
         * Balance : 19996
         * IsDiscount : false
         * DiscountRate : 100
         * TradeDateTime : 2019-08-13 14:48:50
         * CreateTime : 2019-08-13 14:48:50
         * Description : 【手动消费】扣款合计0.99元;账户实际扣款0.99元;账户余额合计19996.00元.
         * OfflinePayCount : null
         */


        private String ID;
        private String UserID;
        private int Number;
        private int DeviceType;
        private int DeviceID;
        private int Pattern;
        private int DetailType;
        private int PayCount;
        private int Finance;
        private double OriginalAmount;
        private double Amount;
        private double Balance;
        private boolean IsDiscount;
        private int DiscountRate;
        private String TradeDateTime;
        private String CreateTime;
        private String Description;
        private Object OfflinePayCount;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public int getNumber() {
            return Number;
        }

        public void setNumber(int Number) {
            this.Number = Number;
        }

        public int getDeviceType() {
            return DeviceType;
        }

        public void setDeviceType(int DeviceType) {
            this.DeviceType = DeviceType;
        }

        public int getDeviceID() {
            return DeviceID;
        }

        public void setDeviceID(int DeviceID) {
            this.DeviceID = DeviceID;
        }

        public int getPattern() {
            return Pattern;
        }

        public void setPattern(int Pattern) {
            this.Pattern = Pattern;
        }

        public int getDetailType() {
            return DetailType;
        }

        public void setDetailType(int DetailType) {
            this.DetailType = DetailType;
        }

        public int getPayCount() {
            return PayCount;
        }

        public void setPayCount(int PayCount) {
            this.PayCount = PayCount;
        }

        public int getFinance() {
            return Finance;
        }

        public void setFinance(int Finance) {
            this.Finance = Finance;
        }

        public double getOriginalAmount() {
            return OriginalAmount;
        }

        public void setOriginalAmount(double OriginalAmount) {
            this.OriginalAmount = OriginalAmount;
        }

        public double getAmount() {
            return Amount;
        }

        public void setAmount(double Amount) {
            this.Amount = Amount;
        }

        public double getBalance() {
            return Balance;
        }

        public void setBalance(double Balance) {
            this.Balance = Balance;
        }

        public boolean isIsDiscount() {
            return IsDiscount;
        }

        public void setIsDiscount(boolean IsDiscount) {
            this.IsDiscount = IsDiscount;
        }

        public int getDiscountRate() {
            return DiscountRate;
        }

        public void setDiscountRate(int DiscountRate) {
            this.DiscountRate = DiscountRate;
        }

        public String getTradeDateTime() {
            return TradeDateTime;
        }

        public void setTradeDateTime(String TradeDateTime) {
            this.TradeDateTime = TradeDateTime;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public Object getOfflinePayCount() {
            return OfflinePayCount;
        }

        public void setOfflinePayCount(Object OfflinePayCount) {
            this.OfflinePayCount = OfflinePayCount;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.ID);
            dest.writeString(this.UserID);
            dest.writeInt(this.Number);
            dest.writeInt(this.DeviceType);
            dest.writeInt(this.DeviceID);
            dest.writeInt(this.Pattern);
            dest.writeInt(this.DetailType);
            dest.writeInt(this.PayCount);
            dest.writeInt(this.Finance);
            dest.writeDouble(this.OriginalAmount);
            dest.writeDouble(this.Amount);
            dest.writeDouble(this.Balance);
            dest.writeByte(this.IsDiscount ? (byte) 1 : (byte) 0);
            dest.writeInt(this.DiscountRate);
            dest.writeString(this.TradeDateTime);
            dest.writeString(this.CreateTime);
            dest.writeString(this.Description);
        }

        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            this.ID = in.readString();
            this.UserID = in.readString();
            this.Number = in.readInt();
            this.DeviceType = in.readInt();
            this.DeviceID = in.readInt();
            this.Pattern = in.readInt();
            this.DetailType = in.readInt();
            this.PayCount = in.readInt();
            this.Finance = in.readInt();
            this.OriginalAmount = in.readDouble();
            this.Amount = in.readDouble();
            this.Balance = in.readDouble();
            this.IsDiscount = in.readByte() != 0;
            this.DiscountRate = in.readInt();
            this.TradeDateTime = in.readString();
            this.CreateTime = in.readString();
            this.Description = in.readString();
        }

        public static final Parcelable.Creator<ContentBean> CREATOR = new Parcelable.Creator<ContentBean>() {
            @Override
            public ContentBean createFromParcel(Parcel source) {
                return new ContentBean(source);
            }

            @Override
            public ContentBean[] newArray(int size) {
                return new ContentBean[size];
            }
        };
    }
}
