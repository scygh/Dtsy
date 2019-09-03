package com.moredian.entrance.guard.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/2 14:01
 */
public class GetExpensePage {

    /**
     * Content : {"Count":6,"Rows":[{"ID":"34e0996c-900e-435b-b205-7bcb08e28332","UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","DepartmentName":"默认部门","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DeviceType":2,"DeviceTypeName":"消费机","DeviceID":1,"DetailType":0,"PayCount":162,"Pattern":2,"PatternName":"自动扣费","Finance":0,"OriginalAmount":20,"Amount":20,"Balance":8808.58,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-22 15:42:15","Description":"扣款合计20.00元;账户实际扣款20.00元;账户余额合计8808.58元.","OfflinePayCount":0},{"ID":"b3085554-4fb6-4f01-bdea-428efe2708fe","UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","DepartmentName":"默认部门","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DeviceType":2,"DeviceTypeName":"消费机","DeviceID":1,"DetailType":0,"PayCount":161,"Pattern":2,"PatternName":"自动扣费","Finance":0,"OriginalAmount":20,"Amount":20,"Balance":8828.58,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-22 15:42:13","Description":"扣款合计20.00元;账户实际扣款20.00元;账户余额合计8828.58元.","OfflinePayCount":0},{"ID":"9df829c1-ced4-48c0-82e9-ea9ce5b2ef6f","UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","DepartmentName":"默认部门","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DeviceType":2,"DeviceTypeName":"消费机","DeviceID":1,"DetailType":0,"PayCount":160,"Pattern":1,"PatternName":"手动消费","Finance":0,"OriginalAmount":20,"Amount":20,"Balance":8848.58,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-22 15:41:51","Description":"扣款合计20.00元;账户实际扣款20.00元;账户余额合计8848.58元.","OfflinePayCount":0},{"ID":"7a869271-5625-4efa-949d-0784acc57317","UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","DepartmentName":"默认部门","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DeviceType":2,"DeviceTypeName":"消费机","DeviceID":10000,"DetailType":0,"PayCount":159,"Pattern":1,"PatternName":"手动消费","Finance":0,"OriginalAmount":1800,"Amount":1800,"Balance":8846.58,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-21 15:02:59","Description":"扣款合计1800.00元;账户实际扣款1800.00元;账户余额合计8846.58元.","OfflinePayCount":0},{"ID":"f6344fad-f849-4f10-86e2-4ab6ae1c6d10","UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","DepartmentName":"默认部门","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DeviceType":2,"DeviceTypeName":"消费机","DeviceID":10000,"DetailType":0,"PayCount":158,"Pattern":2,"PatternName":"自动扣费","Finance":0,"OriginalAmount":2000,"Amount":2000,"Balance":10646.58,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-21 15:02:44","Description":"扣款合计2000.00元;账户实际扣款2000.00元;账户余额合计10646.58元.","OfflinePayCount":0},{"ID":"f3dbd037-f490-420c-85b3-1070acfaa7a6","UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","DepartmentName":"默认部门","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DeviceType":2,"DeviceTypeName":"消费机","DeviceID":1,"DetailType":0,"PayCount":157,"Pattern":2,"PatternName":"自动扣费","Finance":0,"OriginalAmount":2000,"Amount":2000,"Balance":12606.58,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-21 12:32:18","Description":"扣款合计2000.00元;账户实际扣款2000.00元;账户余额合计12606.58元.","OfflinePayCount":0}]}
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
         * Rows : [{"ID":"34e0996c-900e-435b-b205-7bcb08e28332","UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","DepartmentName":"默认部门","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DeviceType":2,"DeviceTypeName":"消费机","DeviceID":1,"DetailType":0,"PayCount":162,"Pattern":2,"PatternName":"自动扣费","Finance":0,"OriginalAmount":20,"Amount":20,"Balance":8808.58,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-22 15:42:15","Description":"扣款合计20.00元;账户实际扣款20.00元;账户余额合计8808.58元.","OfflinePayCount":0},{"ID":"b3085554-4fb6-4f01-bdea-428efe2708fe","UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","DepartmentName":"默认部门","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DeviceType":2,"DeviceTypeName":"消费机","DeviceID":1,"DetailType":0,"PayCount":161,"Pattern":2,"PatternName":"自动扣费","Finance":0,"OriginalAmount":20,"Amount":20,"Balance":8828.58,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-22 15:42:13","Description":"扣款合计20.00元;账户实际扣款20.00元;账户余额合计8828.58元.","OfflinePayCount":0},{"ID":"9df829c1-ced4-48c0-82e9-ea9ce5b2ef6f","UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","DepartmentName":"默认部门","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DeviceType":2,"DeviceTypeName":"消费机","DeviceID":1,"DetailType":0,"PayCount":160,"Pattern":1,"PatternName":"手动消费","Finance":0,"OriginalAmount":20,"Amount":20,"Balance":8848.58,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-22 15:41:51","Description":"扣款合计20.00元;账户实际扣款20.00元;账户余额合计8848.58元.","OfflinePayCount":0},{"ID":"7a869271-5625-4efa-949d-0784acc57317","UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","DepartmentName":"默认部门","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DeviceType":2,"DeviceTypeName":"消费机","DeviceID":10000,"DetailType":0,"PayCount":159,"Pattern":1,"PatternName":"手动消费","Finance":0,"OriginalAmount":1800,"Amount":1800,"Balance":8846.58,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-21 15:02:59","Description":"扣款合计1800.00元;账户实际扣款1800.00元;账户余额合计8846.58元.","OfflinePayCount":0},{"ID":"f6344fad-f849-4f10-86e2-4ab6ae1c6d10","UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","DepartmentName":"默认部门","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DeviceType":2,"DeviceTypeName":"消费机","DeviceID":10000,"DetailType":0,"PayCount":158,"Pattern":2,"PatternName":"自动扣费","Finance":0,"OriginalAmount":2000,"Amount":2000,"Balance":10646.58,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-21 15:02:44","Description":"扣款合计2000.00元;账户实际扣款2000.00元;账户余额合计10646.58元.","OfflinePayCount":0},{"ID":"f3dbd037-f490-420c-85b3-1070acfaa7a6","UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","SerialNo":"scy11","Name":"scy11","DepartmentID":"00000000-0000-0000-0000-000000000001","DepartmentName":"默认部门","EmpID":null,"IDCard":null,"Phone":null,"Number":22,"CardTypeID":1,"CardTypeName":"正常卡","DeviceType":2,"DeviceTypeName":"消费机","DeviceID":1,"DetailType":0,"PayCount":157,"Pattern":2,"PatternName":"自动扣费","Finance":0,"OriginalAmount":2000,"Amount":2000,"Balance":12606.58,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-21 12:32:18","Description":"扣款合计2000.00元;账户实际扣款2000.00元;账户余额合计12606.58元.","OfflinePayCount":0}]
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
             * ID : 34e0996c-900e-435b-b205-7bcb08e28332
             * UserID : 6369e769-ef3b-47fe-b7ce-f7f8e931c29c
             * SerialNo : scy11
             * Name : scy11
             * DepartmentID : 00000000-0000-0000-0000-000000000001
             * DepartmentName : 默认部门
             * EmpID : null
             * IDCard : null
             * Phone : null
             * Number : 22
             * CardTypeID : 1
             * CardTypeName : 正常卡
             * DeviceType : 2
             * DeviceTypeName : 消费机
             * DeviceID : 1
             * DetailType : 0
             * PayCount : 162
             * Pattern : 2
             * PatternName : 自动扣费
             * Finance : 0
             * OriginalAmount : 20
             * Amount : 20
             * Balance : 8808.58
             * IsDiscount : false
             * DiscountRate : 100
             * TradeDateTime : 2019-08-22 15:42:15
             * Description : 扣款合计20.00元;账户实际扣款20.00元;账户余额合计8808.58元.
             * OfflinePayCount : 0
             */



            private String ID;
            private String UserID;
            private String SerialNo;
            private String Name;
            private String DepartmentID;
            private String DepartmentName;
            private Object EmpID;
            private Object IDCard;
            private Object Phone;
            private int Number;
            private int CardTypeID;
            private String CardTypeName;
            private int DeviceType;
            private String DeviceTypeName;
            private int DeviceID;
            private int DetailType;
            private int PayCount;
            private int Pattern;
            private String PatternName;
            private int Finance;
            private double OriginalAmount;
            private double Amount;
            private double Balance;
            private boolean IsDiscount;
            private int DiscountRate;
            private String TradeDateTime;
            private String Description;
            private int OfflinePayCount;

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

            public String getDepartmentName() {
                return DepartmentName;
            }

            public void setDepartmentName(String DepartmentName) {
                this.DepartmentName = DepartmentName;
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

            public int getDeviceType() {
                return DeviceType;
            }

            public void setDeviceType(int DeviceType) {
                this.DeviceType = DeviceType;
            }

            public String getDeviceTypeName() {
                return DeviceTypeName;
            }

            public void setDeviceTypeName(String DeviceTypeName) {
                this.DeviceTypeName = DeviceTypeName;
            }

            public int getDeviceID() {
                return DeviceID;
            }

            public void setDeviceID(int DeviceID) {
                this.DeviceID = DeviceID;
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

            public int getPattern() {
                return Pattern;
            }

            public void setPattern(int Pattern) {
                this.Pattern = Pattern;
            }

            public String getPatternName() {
                return PatternName;
            }

            public void setPatternName(String PatternName) {
                this.PatternName = PatternName;
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

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
            }

            public int getOfflinePayCount() {
                return OfflinePayCount;
            }

            public void setOfflinePayCount(int OfflinePayCount) {
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
                dest.writeString(this.SerialNo);
                dest.writeString(this.Name);
                dest.writeString(this.DepartmentID);
                dest.writeString(this.DepartmentName);
                dest.writeInt(this.Number);
                dest.writeInt(this.CardTypeID);
                dest.writeString(this.CardTypeName);
                dest.writeInt(this.DeviceType);
                dest.writeString(this.DeviceTypeName);
                dest.writeInt(this.DeviceID);
                dest.writeInt(this.DetailType);
                dest.writeInt(this.PayCount);
                dest.writeInt(this.Pattern);
                dest.writeString(this.PatternName);
                dest.writeInt(this.Finance);
                dest.writeDouble(this.OriginalAmount);
                dest.writeDouble(this.Amount);
                dest.writeDouble(this.Balance);
                dest.writeByte(this.IsDiscount ? (byte) 1 : (byte) 0);
                dest.writeInt(this.DiscountRate);
                dest.writeString(this.TradeDateTime);
                dest.writeString(this.Description);
                dest.writeInt(this.OfflinePayCount);
            }

            public RowsBean() {
            }

            protected RowsBean(Parcel in) {
                this.ID = in.readString();
                this.UserID = in.readString();
                this.SerialNo = in.readString();
                this.Name = in.readString();
                this.DepartmentID = in.readString();
                this.DepartmentName = in.readString();
                this.Number = in.readInt();
                this.CardTypeID = in.readInt();
                this.CardTypeName = in.readString();
                this.DeviceType = in.readInt();
                this.DeviceTypeName = in.readString();
                this.DeviceID = in.readInt();
                this.DetailType = in.readInt();
                this.PayCount = in.readInt();
                this.Pattern = in.readInt();
                this.PatternName = in.readString();
                this.Finance = in.readInt();
                this.OriginalAmount = in.readDouble();
                this.Amount = in.readDouble();
                this.Balance = in.readDouble();
                this.IsDiscount = in.readByte() != 0;
                this.DiscountRate = in.readInt();
                this.TradeDateTime = in.readString();
                this.Description = in.readString();
                this.OfflinePayCount = in.readInt();
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
