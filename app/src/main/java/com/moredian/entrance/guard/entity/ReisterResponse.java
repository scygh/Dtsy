package com.moredian.entrance.guard.entity;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/27 12:07
 */
public class ReisterResponse {

    /**
     * Content : 50
     * Result : true
     * Message : Success!
     * StatusCode : 200
     */

    private int Content;
    private boolean Result;
    private String Message;
    private int StatusCode;

    public int getContent() {
        return Content;
    }

    public void setContent(int Content) {
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
}
