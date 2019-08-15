package com.moredian.entrance.guard.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/13 15:26
 */
public class QRCodeExpense {

    /**
     * Content : {"QRType":1,"ExpenseDetail":null,"ListEMGoodsDetail":null,"ThirdPartyExpense":{"Id":"d8f13d5e-fc20-4b10-98c2-cb8e43a91fcc","DeviceType":null,"DeviceId":1,"Pattern":2,"DetailType":0,"OriginalAmount":0.01,"Amount":0.01,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-15 08:40:27","CreateTime":"2019-08-15 08:40:27","Description":"【微信刷卡支付】消费0.01元","ThirdPartyUserId":"oyeSOv6Cio8vvbNGs9H5nRA7A6QY","ThirdPartySourceId":"4200000401201908158253605273","OurSourceId":"1001_20190815084025346_1","PayWay":1,"Channel":2,"State":1},"TradingState":0}
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
         * QRType : 1
         * ExpenseDetail : null
         * ListEMGoodsDetail : null
         * ThirdPartyExpense : {"Id":"d8f13d5e-fc20-4b10-98c2-cb8e43a91fcc","DeviceType":null,"DeviceId":1,"Pattern":2,"DetailType":0,"OriginalAmount":0.01,"Amount":0.01,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-15 08:40:27","CreateTime":"2019-08-15 08:40:27","Description":"【微信刷卡支付】消费0.01元","ThirdPartyUserId":"oyeSOv6Cio8vvbNGs9H5nRA7A6QY","ThirdPartySourceId":"4200000401201908158253605273","OurSourceId":"1001_20190815084025346_1","PayWay":1,"Channel":2,"State":1}
         * TradingState : 0
         */



        private int QRType;
        private ExpenseDetailBean ExpenseDetail;
        private List<ListEMGoodsDetailBean> ListEMGoodsDetail;
        private ThirdPartyExpenseBean ThirdPartyExpense;
        private int TradingState;

        public int getQRType() {
            return QRType;
        }

        public void setQRType(int QRType) {
            this.QRType = QRType;
        }

        public ExpenseDetailBean getExpenseDetail() {
            return ExpenseDetail;
        }

        public void setExpenseDetail(ExpenseDetailBean expenseDetail) {
            ExpenseDetail = expenseDetail;
        }

        public List<ListEMGoodsDetailBean> getListEMGoodsDetail() {
            return ListEMGoodsDetail;
        }

        public void setListEMGoodsDetail(List<ListEMGoodsDetailBean> listEMGoodsDetail) {
            ListEMGoodsDetail = listEMGoodsDetail;
        }

        public ThirdPartyExpenseBean getThirdPartyExpense() {
            return ThirdPartyExpense;
        }

        public void setThirdPartyExpense(ThirdPartyExpenseBean ThirdPartyExpense) {
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
             * UserId : 00000000-0000-0000-0000-000000000000
             * Number : 0
             * DeviceType : 1
             * DeviceId : 0
             * Pattern : 1
             * DetailType : 0
             * PayCount : 0
             * Finance : 0
             * OriginalAmount : 0
             * Amount : 0
             * Balance : 0
             * IsDiscount : true
             * DiscountRate : 0
             * TradeDateTime : 2019-08-14T07:49:41.205Z
             * CreateTime : 2019-08-14T07:49:41.205Z
             * Description : string
             * OfflinePayCount : 0
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
            private int OfflinePayCount;

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
                dest.writeInt(this.OfflinePayCount);
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
                this.OfflinePayCount = in.readInt();
            }

            public static final Creator<SimpleExpense.ContentBean.ExpenseDetailBean> CREATOR = new Creator<SimpleExpense.ContentBean.ExpenseDetailBean>() {
                @Override
                public SimpleExpense.ContentBean.ExpenseDetailBean createFromParcel(Parcel source) {
                    return new SimpleExpense.ContentBean.ExpenseDetailBean(source);
                }

                @Override
                public SimpleExpense.ContentBean.ExpenseDetailBean[] newArray(int size) {
                    return new SimpleExpense.ContentBean.ExpenseDetailBean[size];
                }
            };
        }

        public static class ListEMGoodsDetailBean implements Parcelable {

            /**
             * Id : 00000000-0000-0000-0000-000000000000
             * Eid : 00000000-0000-0000-0000-000000000000
             * GoodsNo : 0
             * OrderNo : 0
             * GoodsName : string
             * Price : 0
             * Amount : 0
             * Count : 0
             * CreateTime : 2019-08-14T07:49:41.205Z
             */

            private String Id;
            private String Eid;
            private int GoodsNo;
            private int OrderNo;
            private String GoodsName;
            private int Price;
            private double Amount;
            private int Count;
            private String CreateTime;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getEid() {
                return Eid;
            }

            public void setEid(String Eid) {
                this.Eid = Eid;
            }

            public int getGoodsNo() {
                return GoodsNo;
            }

            public void setGoodsNo(int GoodsNo) {
                this.GoodsNo = GoodsNo;
            }

            public int getOrderNo() {
                return OrderNo;
            }

            public void setOrderNo(int OrderNo) {
                this.OrderNo = OrderNo;
            }

            public String getGoodsName() {
                return GoodsName;
            }

            public void setGoodsName(String GoodsName) {
                this.GoodsName = GoodsName;
            }

            public int getPrice() {
                return Price;
            }

            public void setPrice(int Price) {
                this.Price = Price;
            }

            public double getAmount() {
                return Amount;
            }

            public void setAmount(double Amount) {
                this.Amount = Amount;
            }

            public int getCount() {
                return Count;
            }

            public void setCount(int Count) {
                this.Count = Count;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.Id);
                dest.writeString(this.Eid);
                dest.writeInt(this.GoodsNo);
                dest.writeInt(this.OrderNo);
                dest.writeString(this.GoodsName);
                dest.writeInt(this.Price);
                dest.writeDouble(this.Amount);
                dest.writeInt(this.Count);
                dest.writeString(this.CreateTime);
            }

            public ListEMGoodsDetailBean() {
            }

            protected ListEMGoodsDetailBean(Parcel in) {
                this.Id = in.readString();
                this.Eid = in.readString();
                this.GoodsNo = in.readInt();
                this.OrderNo = in.readInt();
                this.GoodsName = in.readString();
                this.Price = in.readInt();
                this.Amount = in.readDouble();
                this.Count = in.readInt();
                this.CreateTime = in.readString();
            }

            public static final Creator<SimpleExpense.ContentBean.ListEMGoodsDetailBean> CREATOR = new Creator<SimpleExpense.ContentBean.ListEMGoodsDetailBean>() {
                @Override
                public SimpleExpense.ContentBean.ListEMGoodsDetailBean createFromParcel(Parcel source) {
                    return new SimpleExpense.ContentBean.ListEMGoodsDetailBean(source);
                }

                @Override
                public SimpleExpense.ContentBean.ListEMGoodsDetailBean[] newArray(int size) {
                    return new SimpleExpense.ContentBean.ListEMGoodsDetailBean[size];
                }
            };
        }

        public static class ThirdPartyExpenseBean implements Parcelable {
            /**
             * Id : d8f13d5e-fc20-4b10-98c2-cb8e43a91fcc
             * DeviceType : null
             * DeviceId : 1
             * Pattern : 2
             * DetailType : 0
             * OriginalAmount : 0.01
             * Amount : 0.01
             * IsDiscount : false
             * DiscountRate : 100
             * TradeDateTime : 2019-08-15 08:40:27
             * CreateTime : 2019-08-15 08:40:27
             * Description : 【微信刷卡支付】消费0.01元
             * ThirdPartyUserId : oyeSOv6Cio8vvbNGs9H5nRA7A6QY
             * ThirdPartySourceId : 4200000401201908158253605273
             * OurSourceId : 1001_20190815084025346_1
             * PayWay : 1
             * Channel : 2
             * State : 1
             */

            private String Id;
            private Object DeviceType;
            private int DeviceId;
            private int Pattern;
            private int DetailType;
            private double OriginalAmount;
            private double Amount;
            private boolean IsDiscount;
            private int DiscountRate;
            private String TradeDateTime;
            private String CreateTime;
            private String Description;
            private String ThirdPartyUserId;
            private String ThirdPartySourceId;
            private String OurSourceId;
            private int PayWay;
            private int Channel;
            private int State;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public Object getDeviceType() {
                return DeviceType;
            }

            public void setDeviceType(Object DeviceType) {
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

            public String getThirdPartyUserId() {
                return ThirdPartyUserId;
            }

            public void setThirdPartyUserId(String ThirdPartyUserId) {
                this.ThirdPartyUserId = ThirdPartyUserId;
            }

            public String getThirdPartySourceId() {
                return ThirdPartySourceId;
            }

            public void setThirdPartySourceId(String ThirdPartySourceId) {
                this.ThirdPartySourceId = ThirdPartySourceId;
            }

            public String getOurSourceId() {
                return OurSourceId;
            }

            public void setOurSourceId(String OurSourceId) {
                this.OurSourceId = OurSourceId;
            }

            public int getPayWay() {
                return PayWay;
            }

            public void setPayWay(int PayWay) {
                this.PayWay = PayWay;
            }

            public int getChannel() {
                return Channel;
            }

            public void setChannel(int Channel) {
                this.Channel = Channel;
            }

            public int getState() {
                return State;
            }

            public void setState(int State) {
                this.State = State;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.Id);
                dest.writeInt(this.DeviceId);
                dest.writeInt(this.Pattern);
                dest.writeInt(this.DetailType);
                dest.writeDouble(this.OriginalAmount);
                dest.writeDouble(this.Amount);
                dest.writeByte(this.IsDiscount ? (byte) 1 : (byte) 0);
                dest.writeInt(this.DiscountRate);
                dest.writeString(this.TradeDateTime);
                dest.writeString(this.CreateTime);
                dest.writeString(this.Description);
                dest.writeString(this.ThirdPartyUserId);
                dest.writeString(this.ThirdPartySourceId);
                dest.writeString(this.OurSourceId);
                dest.writeInt(this.PayWay);
                dest.writeInt(this.Channel);
                dest.writeInt(this.State);
            }

            public ThirdPartyExpenseBean() {
            }

            protected ThirdPartyExpenseBean(Parcel in) {
                this.Id = in.readString();
                this.DeviceId = in.readInt();
                this.Pattern = in.readInt();
                this.DetailType = in.readInt();
                this.OriginalAmount = in.readDouble();
                this.Amount = in.readDouble();
                this.IsDiscount = in.readByte() != 0;
                this.DiscountRate = in.readInt();
                this.TradeDateTime = in.readString();
                this.CreateTime = in.readString();
                this.Description = in.readString();
                this.ThirdPartyUserId = in.readString();
                this.ThirdPartySourceId = in.readString();
                this.OurSourceId = in.readString();
                this.PayWay = in.readInt();
                this.Channel = in.readInt();
                this.State = in.readInt();
            }

            public static final Parcelable.Creator<ThirdPartyExpenseBean> CREATOR = new Parcelable.Creator<ThirdPartyExpenseBean>() {
                @Override
                public ThirdPartyExpenseBean createFromParcel(Parcel source) {
                    return new ThirdPartyExpenseBean(source);
                }

                @Override
                public ThirdPartyExpenseBean[] newArray(int size) {
                    return new ThirdPartyExpenseBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.QRType);
            dest.writeParcelable(this.ExpenseDetail, flags);
            dest.writeParcelable(this.ThirdPartyExpense, flags);
            dest.writeInt(this.TradingState);
        }

        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            this.QRType = in.readInt();
            this.ExpenseDetail = in.readParcelable(ExpenseDetailBean.class.getClassLoader());
            this.ThirdPartyExpense = in.readParcelable(ThirdPartyExpenseBean.class.getClassLoader());
            this.TradingState = in.readInt();
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
