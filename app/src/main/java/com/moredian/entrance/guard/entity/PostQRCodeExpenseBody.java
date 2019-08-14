package com.moredian.entrance.guard.entity;

import java.util.List;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/13 15:29
 */
public class PostQRCodeExpenseBody {

    /**
     * QRType : 0
     * QRContent : string
     * ListGoods : [{"ID":"00000000-0000-0000-0000-000000000000","EID":"00000000-0000-0000-0000-000000000000","GoodsNo":0,"OrderNo":0,"GoodsName":"string","Price":0,"Amount":0,"Count":0,"CreateTime":"2019-08-13T06:08:23.295Z"}]
     * Number : 0
     * Amount : 0
     * Pattern : 1
     * PayCount : 0
     * PayKey : string
     * DeviceID : 0
     * DeviceType : 2
     */

    private int QRType;
    private String QRContent;
    private int Number;
    private double Amount;
    private int Pattern;
    private int PayCount;
    private String PayKey;
    private int DeviceID;
    private int DeviceType;
    private List<ListGoodsBean> ListGoods;

    public PostQRCodeExpenseBody(String QRContent, double amount, int pattern,  int deviceID, int deviceType) {
        this.QRContent = QRContent;
        Amount = amount;
        Pattern = pattern;
        DeviceID = deviceID;
        DeviceType = deviceType;
    }

    public int getQRType() {
        return QRType;
    }

    public void setQRType(int QRType) {
        this.QRType = QRType;
    }

    public String getQRContent() {
        return QRContent;
    }

    public void setQRContent(String QRContent) {
        this.QRContent = QRContent;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        this.Number = Number;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public int getPattern() {
        return Pattern;
    }

    public void setPattern(int Pattern) {
        this.Pattern = Pattern;
    }

    public int getPayCount() {
        return PayCount;
    }

    public void setPayCount(int PayCount) {
        this.PayCount = PayCount;
    }

    public String getPayKey() {
        return PayKey;
    }

    public void setPayKey(String PayKey) {
        this.PayKey = PayKey;
    }

    public int getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(int DeviceID) {
        this.DeviceID = DeviceID;
    }

    public int getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(int DeviceType) {
        this.DeviceType = DeviceType;
    }

    public List<ListGoodsBean> getListGoods() {
        return ListGoods;
    }

    public void setListGoods(List<ListGoodsBean> ListGoods) {
        this.ListGoods = ListGoods;
    }

    public static class ListGoodsBean {
        /**
         * ID : 00000000-0000-0000-0000-000000000000
         * EID : 00000000-0000-0000-0000-000000000000
         * GoodsNo : 0
         * OrderNo : 0
         * GoodsName : string
         * Price : 0
         * Amount : 0
         * Count : 0
         * CreateTime : 2019-08-13T06:08:23.295Z
         */

        private String ID;
        private String EID;
        private int GoodsNo;
        private int OrderNo;
        private String GoodsName;
        private double Price;
        private double Amount;
        private int Count;
        private String CreateTime;

        public ListGoodsBean(String ID, String EID, int goodsNo, int orderNo, String goodsName, double price, double amount, int count, String createTime) {
            this.ID = ID;
            this.EID = EID;
            GoodsNo = goodsNo;
            OrderNo = orderNo;
            GoodsName = goodsName;
            Price = price;
            Amount = amount;
            Count = count;
            CreateTime = createTime;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getEID() {
            return EID;
        }

        public void setEID(String EID) {
            this.EID = EID;
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
