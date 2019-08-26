package com.moredian.entrance.guard.entity;

import java.util.List;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/26 10:21
 */
public class GetDepartmentList {


    /**
     * Result : true
     * Message : string
     * StatusCode : 200
     * Content : [{"ID":"string","ParentID":"string","Name":"string","Description":"string","State":0}]
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
         * ID : string
         * ParentID : string
         * Name : string
         * Description : string
         * State : 0
         */

        private String ID;
        private String ParentID;
        private String Name;
        private String Description;
        private int State;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getParentID() {
            return ParentID;
        }

        public void setParentID(String ParentID) {
            this.ParentID = ParentID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }
    }
}
