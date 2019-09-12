package com.moredian.entrance.guard.entity;

import java.util.List;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/11 16:12
 */
public class GetDeviceNumList {


    /**
     * Content : [{"ID":1,"PlaceID":"00000000-0000-0000-0000-000000000001","Pattern":6,"AutoAmount":0,"KeyEnabled":null,"MealEnabled":true,"DepositEnabled":true,"RefundEnabled":true,"CorrectionEnabled":true,"AllowType":"1,2,3,4","AllowMeal":"","LinkIpAddress":"172.18.14.163","LinkPort":"1400","GoodsRange":"1,65535","State":1,"DiscountEnabled":true,"FirmwareVersion":"SCM_EM_1100-190418","DeviceVersion":9},{"ID":2,"PlaceID":"00000000-0000-0000-0000-000000000001","Pattern":1,"AutoAmount":3,"KeyEnabled":false,"MealEnabled":true,"DepositEnabled":true,"RefundEnabled":true,"CorrectionEnabled":true,"AllowType":"1,2,3,4,5","AllowMeal":"","LinkIpAddress":"172.16.204.235","LinkPort":"1400","GoodsRange":"1,65535","State":1,"DiscountEnabled":true,"FirmwareVersion":"SCM_EM_1100-190418","DeviceVersion":3},{"ID":999,"PlaceID":"00000000-0000-0000-0000-000000000001","Pattern":1,"AutoAmount":10,"KeyEnabled":null,"MealEnabled":true,"DepositEnabled":false,"RefundEnabled":false,"CorrectionEnabled":true,"AllowType":"1,2,3,4,5,6","AllowMeal":"","LinkIpAddress":"","LinkPort":"","GoodsRange":"1,65535","State":1,"DiscountEnabled":true,"FirmwareVersion":"","DeviceVersion":8},{"ID":10000,"PlaceID":"00000000-0000-0000-0000-000000000001","Pattern":1,"AutoAmount":0,"KeyEnabled":false,"MealEnabled":true,"DepositEnabled":false,"RefundEnabled":false,"CorrectionEnabled":true,"AllowType":"1,2,3,4","AllowMeal":"","LinkIpAddress":"172.16.204.235","LinkPort":"1400","GoodsRange":"1,65535","State":1,"DiscountEnabled":true,"FirmwareVersion":"SCM_EM_1000-190418","DeviceVersion":9}]
     * Result : true
     * Message : Success!
     * StatusCode : 200
     */

    private boolean Result;
    private String Message;
    private int StatusCode;
    private List<ContentBean> Content;

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

    public List<ContentBean> getContent() {
        return Content;
    }

    public void setContent(List<ContentBean> Content) {
        this.Content = Content;
    }

    public static class ContentBean {
        /**
         * ID : 1
         * PlaceID : 00000000-0000-0000-0000-000000000001
         * Pattern : 6
         * AutoAmount : 0
         * KeyEnabled : null
         * MealEnabled : true
         * DepositEnabled : true
         * RefundEnabled : true
         * CorrectionEnabled : true
         * AllowType : 1,2,3,4
         * AllowMeal :
         * LinkIpAddress : 172.18.14.163
         * LinkPort : 1400
         * GoodsRange : 1,65535
         * State : 1
         * DiscountEnabled : true
         * FirmwareVersion : SCM_EM_1100-190418
         * DeviceVersion : 9
         */

        private int ID;
        private String PlaceID;
        private int Pattern;
        private double AutoAmount;
        private Object KeyEnabled;
        private boolean MealEnabled;
        private boolean DepositEnabled;
        private boolean RefundEnabled;
        private boolean CorrectionEnabled;
        private String AllowType;
        private String AllowMeal;
        private String LinkIpAddress;
        private String LinkPort;
        private String GoodsRange;
        private int State;
        private boolean DiscountEnabled;
        private String FirmwareVersion;
        private int DeviceVersion;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getPlaceID() {
            return PlaceID;
        }

        public void setPlaceID(String PlaceID) {
            this.PlaceID = PlaceID;
        }

        public int getPattern() {
            return Pattern;
        }

        public void setPattern(int Pattern) {
            this.Pattern = Pattern;
        }

        public double getAutoAmount() {
            return AutoAmount;
        }

        public void setAutoAmount(double AutoAmount) {
            this.AutoAmount = AutoAmount;
        }

        public Object getKeyEnabled() {
            return KeyEnabled;
        }

        public void setKeyEnabled(Object KeyEnabled) {
            this.KeyEnabled = KeyEnabled;
        }

        public boolean isMealEnabled() {
            return MealEnabled;
        }

        public void setMealEnabled(boolean MealEnabled) {
            this.MealEnabled = MealEnabled;
        }

        public boolean isDepositEnabled() {
            return DepositEnabled;
        }

        public void setDepositEnabled(boolean DepositEnabled) {
            this.DepositEnabled = DepositEnabled;
        }

        public boolean isRefundEnabled() {
            return RefundEnabled;
        }

        public void setRefundEnabled(boolean RefundEnabled) {
            this.RefundEnabled = RefundEnabled;
        }

        public boolean isCorrectionEnabled() {
            return CorrectionEnabled;
        }

        public void setCorrectionEnabled(boolean CorrectionEnabled) {
            this.CorrectionEnabled = CorrectionEnabled;
        }

        public String getAllowType() {
            return AllowType;
        }

        public void setAllowType(String AllowType) {
            this.AllowType = AllowType;
        }

        public String getAllowMeal() {
            return AllowMeal;
        }

        public void setAllowMeal(String AllowMeal) {
            this.AllowMeal = AllowMeal;
        }

        public String getLinkIpAddress() {
            return LinkIpAddress;
        }

        public void setLinkIpAddress(String LinkIpAddress) {
            this.LinkIpAddress = LinkIpAddress;
        }

        public String getLinkPort() {
            return LinkPort;
        }

        public void setLinkPort(String LinkPort) {
            this.LinkPort = LinkPort;
        }

        public String getGoodsRange() {
            return GoodsRange;
        }

        public void setGoodsRange(String GoodsRange) {
            this.GoodsRange = GoodsRange;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public boolean isDiscountEnabled() {
            return DiscountEnabled;
        }

        public void setDiscountEnabled(boolean DiscountEnabled) {
            this.DiscountEnabled = DiscountEnabled;
        }

        public String getFirmwareVersion() {
            return FirmwareVersion;
        }

        public void setFirmwareVersion(String FirmwareVersion) {
            this.FirmwareVersion = FirmwareVersion;
        }

        public int getDeviceVersion() {
            return DeviceVersion;
        }

        public void setDeviceVersion(int DeviceVersion) {
            this.DeviceVersion = DeviceVersion;
        }
    }
}
