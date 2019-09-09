package com.moredian.entrance.guard.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/15 15:59
 */
public class DefiniteExpense {

    /**
     * Content : {"ExpenseDetail":{"Id":"00000000-0000-0000-0000-000000000000","UserId":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","Number":22,"DeviceType":2,"DeviceId":10000,"Pattern":3,"DetailType":0,"PayCount":256,"Finance":0,"OriginalAmount":10,"Amount":10,"Balance":92341.86,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"0001-01-01 00:00:00","CreateTime":"0001-01-01 00:00:00","Description":"扣款合计10.00元;账户合计扣款10.00元;账户余额合计92341.86元.","OfflinePayCount":null},"ListEMGoodsDetail":null,"ThirdPartyExpense":null,"TradingState":0}
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
         * ExpenseDetail : {"Id":"00000000-0000-0000-0000-000000000000","UserId":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","Number":22,"DeviceType":2,"DeviceId":10000,"Pattern":3,"DetailType":0,"PayCount":256,"Finance":0,"OriginalAmount":10,"Amount":10,"Balance":92341.86,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"0001-01-01 00:00:00","CreateTime":"0001-01-01 00:00:00","Description":"扣款合计10.00元;账户合计扣款10.00元;账户余额合计92341.86元.","OfflinePayCount":null}
         * ListEMGoodsDetail : null
         * ThirdPartyExpense : null
         * TradingState : 0
         */

        private ExpenseDetailBean ExpenseDetail;
        private Object ListEMGoodsDetail;
        private Object ThirdPartyExpense;
        private int TradingState;

        public ExpenseDetailBean getExpenseDetail() {
            return ExpenseDetail;
        }

        public void setExpenseDetail(ExpenseDetailBean ExpenseDetail) {
            this.ExpenseDetail = ExpenseDetail;
        }

        public Object getListEMGoodsDetail() {
            return ListEMGoodsDetail;
        }

        public void setListEMGoodsDetail(Object ListEMGoodsDetail) {
            this.ListEMGoodsDetail = ListEMGoodsDetail;
        }

        public Object getThirdPartyExpense() {
            return ThirdPartyExpense;
        }

        public void setThirdPartyExpense(Object ThirdPartyExpense) {
            this.ThirdPartyExpense = ThirdPartyExpense;
        }

        public int getTradingState() {
            return TradingState;
        }

        public void setTradingState(int TradingState) {
            this.TradingState = TradingState;
        }

        public static class ExpenseDetailBean implements Parcelable {
            /**
             * Id : 00000000-0000-0000-0000-000000000000
             * UserId : 6369e769-ef3b-47fe-b7ce-f7f8e931c29c
             * Number : 22
             * DeviceType : 2
             * DeviceId : 10000
             * Pattern : 3
             * DetailType : 0
             * PayCount : 256
             * Finance : 0
             * OriginalAmount : 10
             * Amount : 10
             * Balance : 92341.86
             * IsDiscount : false
             * DiscountRate : 100
             * TradeDateTime : 0001-01-01 00:00:00
             * CreateTime : 0001-01-01 00:00:00
             * Description : 扣款合计10.00元;账户合计扣款10.00元;账户余额合计92341.86元.
             * OfflinePayCount : null
             */


            private String Id;
            private String UserId;
            private int Number;
            private int DeviceType;
            private int DeviceId;
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

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getUserId() {
                return UserId;
            }

            public void setUserId(String UserId) {
                this.UserId = UserId;
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

            public int getDeviceId() {
                return DeviceId;
            }

            public void setDeviceId(int DeviceId) {
                this.DeviceId = DeviceId;
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
                dest.writeString(this.Id);
                dest.writeString(this.UserId);
                dest.writeInt(this.Number);
                dest.writeInt(this.DeviceType);
                dest.writeInt(this.DeviceId);
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

            public ExpenseDetailBean() {
            }

            protected ExpenseDetailBean(Parcel in) {
                this.Id = in.readString();
                this.UserId = in.readString();
                this.Number = in.readInt();
                this.DeviceType = in.readInt();
                this.DeviceId = in.readInt();
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

            public static final Parcelable.Creator<ExpenseDetailBean> CREATOR = new Parcelable.Creator<ExpenseDetailBean>() {
                @Override
                public ExpenseDetailBean createFromParcel(Parcel source) {
                    return new ExpenseDetailBean(source);
                }

                @Override
                public ExpenseDetailBean[] newArray(int size) {
                    return new ExpenseDetailBean[size];
                }
            };
        }
    }
}
