package com.moredian.entrance.guard.entity;

import java.util.List;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/3 10:03
 */
public class GetChannel {


    /**
     * Content : [{"Value":0,"Text":"线上"},{"Value":101,"Text":"支付宝转账"},{"Value":102,"Text":"微信转账"},{"Value":103,"Text":"银行卡转账"},{"Value":104,"Text":"其它转账"}]
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
         * Value : 0
         * Text : 线上
         */

        private int Value;
        private String Text;

        public int getValue() {
            return Value;
        }

        public void setValue(int Value) {
            this.Value = Value;
        }

        public String getText() {
            return Text;
        }

        public void setText(String Text) {
            this.Text = Text;
        }
    }
}
