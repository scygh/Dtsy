package com.moredian.entrance.guard.entity;

import java.util.List;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/26 14:12
 */
public class GetCardTypeList {


    /**
     * Result : true
     * Message : string
     * StatusCode : 200
     * Content : [{"ID":0,"Name":"string","State":0,"Foregift":0,"DefaultCost":0,"ExpiryDate":0,"IsDiscount":true,"DiscountRate":0,"Description":"string"}]
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
         * ID : 0
         * Name : string
         * State : 0
         * Foregift : 0
         * DefaultCost : 0
         * ExpiryDate : 0
         * IsDiscount : true
         * DiscountRate : 0
         * Description : string
         */

        private int ID;
        private String Name;
        private int State;
        private int Foregift;
        private int DefaultCost;
        private int ExpiryDate;
        private boolean IsDiscount;
        private int DiscountRate;
        private String Description;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public int getForegift() {
            return Foregift;
        }

        public void setForegift(int Foregift) {
            this.Foregift = Foregift;
        }

        public int getDefaultCost() {
            return DefaultCost;
        }

        public void setDefaultCost(int DefaultCost) {
            this.DefaultCost = DefaultCost;
        }

        public int getExpiryDate() {
            return ExpiryDate;
        }

        public void setExpiryDate(int ExpiryDate) {
            this.ExpiryDate = ExpiryDate;
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

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }
    }
}
