package com.moredian.entrance.guard.entity;

import com.google.gson.annotations.SerializedName;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/3 10:03
 */
public class GetChannel {


    /**
     * Content : {"0":"线上","101":"支付宝转账","102":"微信转账","103":"银行卡转账","104":"其它转账"}
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
         * 0 : 线上
         * 101 : 支付宝转账
         * 102 : 微信转账
         * 103 : 银行卡转账
         * 104 : 其它转账
         */

        @SerializedName("0")
        private String _$0;
        @SerializedName("101")
        private String _$101;
        @SerializedName("102")
        private String _$102;
        @SerializedName("103")
        private String _$103;
        @SerializedName("104")
        private String _$104;

        public String get_$0() {
            return _$0;
        }

        public void set_$0(String _$0) {
            this._$0 = _$0;
        }

        public String get_$101() {
            return _$101;
        }

        public void set_$101(String _$101) {
            this._$101 = _$101;
        }

        public String get_$102() {
            return _$102;
        }

        public void set_$102(String _$102) {
            this._$102 = _$102;
        }

        public String get_$103() {
            return _$103;
        }

        public void set_$103(String _$103) {
            this._$103 = _$103;
        }

        public String get_$104() {
            return _$104;
        }

        public void set_$104(String _$104) {
            this._$104 = _$104;
        }
    }
}
