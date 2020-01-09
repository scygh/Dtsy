package com.moredian.entrance.guard.entity;

import java.util.List;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/4 09:46
 */
public class GetMealList {


    /**
     * Content : [{"Id":1,"Name":"早餐","BeginTime":"01:00","EndTime":"09:00","State":1},{"Id":2,"Name":"中餐","BeginTime":"09:01","EndTime":"15:59","State":1},{"Id":3,"Name":"晚餐","BeginTime":"16:00","EndTime":"21:59","State":1},{"Id":4,"Name":"夜宵","BeginTime":"22:00","EndTime":"23:59","State":1}]
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
         * Id : 1
         * Name : 早餐
         * BeginTime : 01:00
         * EndTime : 09:00
         * State : 1
         */

        private int Id;
        private String Name;
        private String BeginTime;
        private String EndTime;
        private int State;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getBeginTime() {
            return BeginTime;
        }

        public void setBeginTime(String BeginTime) {
            this.BeginTime = BeginTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }
    }
}
