package com.moredian.entrance.guard.entity;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/31 11:08
 */
public class GetToken {


    /**
     * Content : {"AccessToken":"00000000-0000-0000-0000-000000000000","CompanyCode":0,"UserID":"00000000-0000-0000-0000-000000000000","Account":"string","ExpirationTime":"2019-07-31T01:51:59.791Z"}
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
         * AccessToken : 00000000-0000-0000-0000-000000000000
         * CompanyCode : 0
         * UserID : 00000000-0000-0000-0000-000000000000
         * Account : string
         * ExpirationTime : 2019-07-31T01:51:59.791Z
         */

        private String AccessToken;
        private int CompanyCode;
        private String UserID;
        private String Account;
        private String ExpirationTime;

        public String getAccessToken() {
            return AccessToken;
        }

        public void setAccessToken(String AccessToken) {
            this.AccessToken = AccessToken;
        }

        public int getCompanyCode() {
            return CompanyCode;
        }

        public void setCompanyCode(int CompanyCode) {
            this.CompanyCode = CompanyCode;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getAccount() {
            return Account;
        }

        public void setAccount(String Account) {
            this.Account = Account;
        }

        public String getExpirationTime() {
            return ExpirationTime;
        }

        public void setExpirationTime(String ExpirationTime) {
            this.ExpirationTime = ExpirationTime;
        }
    }

}
