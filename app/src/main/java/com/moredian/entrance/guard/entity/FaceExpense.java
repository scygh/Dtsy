package com.moredian.entrance.guard.entity;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/14 10:05
 */
public class FaceExpense {


    /**
     * Content : {"Id":"00000000-0000-0000-0000-000000000000","DeviceType":1,"DeviceId":0,"Pattern":1,"DetailType":0,"OriginalAmount":0,"Amount":0,"IsDiscount":true,"DiscountRate":0,"TradeDateTime":"2019-08-14T00:39:39.858Z","CreateTime":"2019-08-14T00:39:39.858Z","Description":"string","ThirdPartyUserId":"string","ThirdPartySourceId":"string","OurSourceId":"string","PayWay":0,"Channel":0,"State":0}
     * Result : true
     * Message : string
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
         * Id : 00000000-0000-0000-0000-000000000000
         * DeviceType : 1
         * DeviceId : 0
         * Pattern : 1
         * DetailType : 0
         * OriginalAmount : 0
         * Amount : 0
         * IsDiscount : true
         * DiscountRate : 0
         * TradeDateTime : 2019-08-14T00:39:39.858Z
         * CreateTime : 2019-08-14T00:39:39.858Z
         * Description : string
         * ThirdPartyUserId : string
         * ThirdPartySourceId : string
         * OurSourceId : string
         * PayWay : 0
         * Channel : 0
         * State : 0
         */

        private String Id;
        private int DeviceType;
        private int DeviceId;
        private int Pattern;
        private int DetailType;
        private int OriginalAmount;
        private int Amount;
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

        public int getOriginalAmount() {
            return OriginalAmount;
        }

        public void setOriginalAmount(int OriginalAmount) {
            this.OriginalAmount = OriginalAmount;
        }

        public int getAmount() {
            return Amount;
        }

        public void setAmount(int Amount) {
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
    }
}
