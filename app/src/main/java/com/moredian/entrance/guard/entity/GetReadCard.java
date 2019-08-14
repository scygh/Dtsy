package com.moredian.entrance.guard.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/12 08:58
 */
public class GetReadCard {

    /**
     * Content : {"State":0,"Number":0,"Balance":0,"PayCount":0,"UserName":"string"}
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

    public static class ContentBean implements Parcelable {

        /**
         * State : 0
         * Number : 0
         * Balance : 0
         * PayCount : 0
         * UserName : string
         */

        private int State;
        private int Number;
        private double Balance;
        private int PayCount;
        private String UserName;

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public int getNumber() {
            return Number;
        }

        public void setNumber(int Number) {
            this.Number = Number;
        }

        public double getBalance() {
            return Balance;
        }

        public void setBalance(double Balance) {
            this.Balance = Balance;
        }

        public int getPayCount() {
            return PayCount;
        }

        public void setPayCount(int PayCount) {
            this.PayCount = PayCount;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.State);
            dest.writeInt(this.Number);
            dest.writeDouble(this.Balance);
            dest.writeInt(this.PayCount);
            dest.writeString(this.UserName);
        }

        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            this.State = in.readInt();
            this.Number = in.readInt();
            this.Balance = in.readDouble();
            this.PayCount = in.readInt();
            this.UserName = in.readString();
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
