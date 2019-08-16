package com.moredian.entrance.guard.entity;

import java.util.List;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/15 15:59
 */
public class DefiniteExpense {

    /**
     * Content : {"ExpenseDetail":{"Id":"00000000-0000-0000-0000-000000000000","UserId":"00000000-0000-0000-0000-000000000000","Number":0,"DeviceType":1,"DeviceId":0,"Pattern":1,"DetailType":0,"PayCount":0,"Finance":0,"OriginalAmount":0,"Amount":0,"Balance":0,"IsDiscount":true,"DiscountRate":0,"TradeDateTime":"2019-08-15T06:52:11.344Z","CreateTime":"2019-08-15T06:52:11.344Z","Description":"string","OfflinePayCount":0},"ListEMGoodsDetail":[{"Id":"00000000-0000-0000-0000-000000000000","Eid":"00000000-0000-0000-0000-000000000000","GoodsNo":0,"OrderNo":0,"GoodsName":"string","Price":0,"Amount":0,"Count":0,"CreateTime":"2019-08-15T06:52:11.344Z"}],"ThirdPartyExpense":{"Id":"00000000-0000-0000-0000-000000000000","DeviceType":1,"DeviceId":0,"Pattern":1,"DetailType":0,"OriginalAmount":0,"Amount":0,"IsDiscount":true,"DiscountRate":0,"TradeDateTime":"2019-08-15T06:52:11.344Z","CreateTime":"2019-08-15T06:52:11.344Z","Description":"string","ThirdPartyUserId":"string","ThirdPartySourceId":"string","OurSourceId":"string","PayWay":0,"Channel":0,"State":0},"TradingState":0}
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
         * ExpenseDetail : {"Id":"00000000-0000-0000-0000-000000000000","UserId":"00000000-0000-0000-0000-000000000000","Number":0,"DeviceType":1,"DeviceId":0,"Pattern":1,"DetailType":0,"PayCount":0,"Finance":0,"OriginalAmount":0,"Amount":0,"Balance":0,"IsDiscount":true,"DiscountRate":0,"TradeDateTime":"2019-08-15T06:52:11.344Z","CreateTime":"2019-08-15T06:52:11.344Z","Description":"string","OfflinePayCount":0}
         * ListEMGoodsDetail : [{"Id":"00000000-0000-0000-0000-000000000000","Eid":"00000000-0000-0000-0000-000000000000","GoodsNo":0,"OrderNo":0,"GoodsName":"string","Price":0,"Amount":0,"Count":0,"CreateTime":"2019-08-15T06:52:11.344Z"}]
         * ThirdPartyExpense : {"Id":"00000000-0000-0000-0000-000000000000","DeviceType":1,"DeviceId":0,"Pattern":1,"DetailType":0,"OriginalAmount":0,"Amount":0,"IsDiscount":true,"DiscountRate":0,"TradeDateTime":"2019-08-15T06:52:11.344Z","CreateTime":"2019-08-15T06:52:11.344Z","Description":"string","ThirdPartyUserId":"string","ThirdPartySourceId":"string","OurSourceId":"string","PayWay":0,"Channel":0,"State":0}
         * TradingState : 0
         */

        private ExpenseDetailBean ExpenseDetail;
        private ThirdPartyExpenseBean ThirdPartyExpense;
        private int TradingState;
        private List<ListEMGoodsDetailBean> ListEMGoodsDetail;

        public ExpenseDetailBean getExpenseDetail() {
            return ExpenseDetail;
        }

        public void setExpenseDetail(ExpenseDetailBean ExpenseDetail) {
            this.ExpenseDetail = ExpenseDetail;
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

        public List<ListEMGoodsDetailBean> getListEMGoodsDetail() {
            return ListEMGoodsDetail;
        }

        public void setListEMGoodsDetail(List<ListEMGoodsDetailBean> ListEMGoodsDetail) {
            this.ListEMGoodsDetail = ListEMGoodsDetail;
        }

        public static class ExpenseDetailBean {
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
             * TradeDateTime : 2019-08-15T06:52:11.344Z
             * CreateTime : 2019-08-15T06:52:11.344Z
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
        }

        public static class ThirdPartyExpenseBean {
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
             * TradeDateTime : 2019-08-15T06:52:11.344Z
             * CreateTime : 2019-08-15T06:52:11.344Z
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
        }

        public static class ListEMGoodsDetailBean {
            /**
             * Id : 00000000-0000-0000-0000-000000000000
             * Eid : 00000000-0000-0000-0000-000000000000
             * GoodsNo : 0
             * OrderNo : 0
             * GoodsName : string
             * Price : 0
             * Amount : 0
             * Count : 0
             * CreateTime : 2019-08-15T06:52:11.344Z
             */

            private String Id;
            private String Eid;
            private int GoodsNo;
            private int OrderNo;
            private String GoodsName;
            private double Price;
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

            public double getPrice() {
                return Price;
            }

            public void setPrice(double Price) {
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
        }
    }
}
