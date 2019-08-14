package com.moredian.entrance.guard.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/13 15:26
 */
public class QRCodeExpense {

    /**
     * Content : {"Details":{"Id":"1d3f2037-c694-4443-aabb-30913f044a7f","DeviceType":null,"DeviceId":1,"Pattern":1,"DetailType":0,"OriginalAmount":0.01,"Amount":0.01,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-13 16:37:23","CreateTime":"2019-08-13 16:37:23","Description":"【支付宝刷卡支付】消费0.01元","ThirdPartyUserId":"2088022580368112","ThirdPartySourceId":"2019081322001468110562177595","OurSourceId":"1001_20190813163721026_1","PayWay":1,"Channel":1,"State":1},"QRType":0,"ListGoodsDetails":null}
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
         * Details : {"Id":"1d3f2037-c694-4443-aabb-30913f044a7f","DeviceType":null,"DeviceId":1,"Pattern":1,"DetailType":0,"OriginalAmount":0.01,"Amount":0.01,"IsDiscount":false,"DiscountRate":100,"TradeDateTime":"2019-08-13 16:37:23","CreateTime":"2019-08-13 16:37:23","Description":"【支付宝刷卡支付】消费0.01元","ThirdPartyUserId":"2088022580368112","ThirdPartySourceId":"2019081322001468110562177595","OurSourceId":"1001_20190813163721026_1","PayWay":1,"Channel":1,"State":1}
         * QRType : 0
         * ListGoodsDetails : null
         */

        private DetailsBean Details;
        private int QRType;
        private Object ListGoodsDetails;

        public DetailsBean getDetails() {
            return Details;
        }

        public void setDetails(DetailsBean Details) {
            this.Details = Details;
        }

        public int getQRType() {
            return QRType;
        }

        public void setQRType(int QRType) {
            this.QRType = QRType;
        }

        public Object getListGoodsDetails() {
            return ListGoodsDetails;
        }

        public void setListGoodsDetails(Object ListGoodsDetails) {
            this.ListGoodsDetails = ListGoodsDetails;
        }

        public static class DetailsBean implements Parcelable {
            /**
             * Id : 1d3f2037-c694-4443-aabb-30913f044a7f
             * DeviceType : null
             * DeviceId : 1
             * Pattern : 1
             * DetailType : 0
             * OriginalAmount : 0.01
             * Amount : 0.01
             * IsDiscount : false
             * DiscountRate : 100
             * TradeDateTime : 2019-08-13 16:37:23
             * CreateTime : 2019-08-13 16:37:23
             * Description : 【支付宝刷卡支付】消费0.01元
             * ThirdPartyUserId : 2088022580368112
             * ThirdPartySourceId : 2019081322001468110562177595
             * OurSourceId : 1001_20190813163721026_1
             * PayWay : 1
             * Channel : 1
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

            public DetailsBean() {
            }

            protected DetailsBean(Parcel in) {
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

            public static final Parcelable.Creator<DetailsBean> CREATOR = new Parcelable.Creator<DetailsBean>() {
                @Override
                public DetailsBean createFromParcel(Parcel source) {
                    return new DetailsBean(source);
                }

                @Override
                public DetailsBean[] newArray(int size) {
                    return new DetailsBean[size];
                }
            };
        }
    }
}
