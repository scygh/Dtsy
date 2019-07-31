package com.moredian.entrance.guard.entity;

import java.util.List;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/31 13:35
 */
public class GetListByPage {

    /**
     * Content : {"Count":10,"Rows":[{"Id":"7e62e249-9ff8-4d26-8379-0f424f0f2e79","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"曹瑞","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"13814059984","CreateTime":"2019-07-01 17:21:16","State":2,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"49c9ccfd-aed9-4d15-be35-7e90a2f9d2f5","DepartmentId":"00000000-0000-0000-0000-000000000000","Name":"鲁炳","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"17557289603","CreateTime":"2019-06-28 09:34:33","State":2,"Password":"ZIigWXeS1dQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"aacfda7c-d289-4cca-a0fe-8e316894d354","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"测试","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-06-27 12:54:29","State":1,"Password":"O4Q7SfJYv9k=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"afce57b8-84ca-423b-af39-980f39d1167d","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"aaaa","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"182 1943 4865 ","CreateTime":"2019-06-13 10:19:36","State":1,"Password":"3R/d3nSG3O4=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"1ebd5586-1551-48fa-a3dd-9f22260915e4","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"001","EmpId":"","IdCard":"","Sex":0,"Age":0,"Address":"","Phone":"","CreateTime":"2019-06-26 15:29:08","State":1,"Password":"Wabg2EsJTng=","Photo":null,"PayKey":"","AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"d1ea68fe-a4f5-42f4-bfea-b14049faef9a","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"靓仔","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"13221817181","CreateTime":"2019-07-01 16:07:16","State":1,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"6d4e1341-d458-4d94-8d08-c30f96c9ca5c","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"临时卡","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-06-27 14:11:37","State":1,"Password":"vVAjaOcQo4g=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"ae752aef-e224-4a8a-b7bc-e1a8ae949755","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"llll","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-07-22 16:01:56","State":1,"Password":"WzyM/ebH5As=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"b4982895-fbee-4745-8f43-e6687a790069","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"005","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"15380326726","CreateTime":"2019-07-01 17:09:52","State":1,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"87d0d2c3-04fd-4026-848a-fa9e5e90cc81","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"15558136957","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"15558136957","CreateTime":"2019-07-01 08:56:05","State":1,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null}]}
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
         * Count : 10
         * Rows : [{"Id":"7e62e249-9ff8-4d26-8379-0f424f0f2e79","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"曹瑞","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"13814059984","CreateTime":"2019-07-01 17:21:16","State":2,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"49c9ccfd-aed9-4d15-be35-7e90a2f9d2f5","DepartmentId":"00000000-0000-0000-0000-000000000000","Name":"鲁炳","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"17557289603","CreateTime":"2019-06-28 09:34:33","State":2,"Password":"ZIigWXeS1dQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"aacfda7c-d289-4cca-a0fe-8e316894d354","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"测试","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-06-27 12:54:29","State":1,"Password":"O4Q7SfJYv9k=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"afce57b8-84ca-423b-af39-980f39d1167d","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"aaaa","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"182 1943 4865 ","CreateTime":"2019-06-13 10:19:36","State":1,"Password":"3R/d3nSG3O4=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"1ebd5586-1551-48fa-a3dd-9f22260915e4","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"001","EmpId":"","IdCard":"","Sex":0,"Age":0,"Address":"","Phone":"","CreateTime":"2019-06-26 15:29:08","State":1,"Password":"Wabg2EsJTng=","Photo":null,"PayKey":"","AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"d1ea68fe-a4f5-42f4-bfea-b14049faef9a","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"靓仔","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"13221817181","CreateTime":"2019-07-01 16:07:16","State":1,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"6d4e1341-d458-4d94-8d08-c30f96c9ca5c","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"临时卡","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-06-27 14:11:37","State":1,"Password":"vVAjaOcQo4g=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"ae752aef-e224-4a8a-b7bc-e1a8ae949755","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"llll","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-07-22 16:01:56","State":1,"Password":"WzyM/ebH5As=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"b4982895-fbee-4745-8f43-e6687a790069","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"005","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"15380326726","CreateTime":"2019-07-01 17:09:52","State":1,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},{"Id":"87d0d2c3-04fd-4026-848a-fa9e5e90cc81","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"15558136957","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"15558136957","CreateTime":"2019-07-01 08:56:05","State":1,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null}]
         */

        private int Count;
        private List<RowsBean> Rows;

        public int getCount() {
            return Count;
        }

        public void setCount(int Count) {
            this.Count = Count;
        }

        public List<RowsBean> getRows() {
            return Rows;
        }

        public void setRows(List<RowsBean> Rows) {
            this.Rows = Rows;
        }

        public static class RowsBean {

            /**
             * Id : 7e62e249-9ff8-4d26-8379-0f424f0f2e79
             * DepartmentId : 00000000-0000-0000-0000-000000000001
             * Name : 曹瑞
             * EmpId : null
             * IdCard : null
             * Sex : 0
             * Age : 0
             * Address : null
             * Phone : 13814059984
             * CreateTime : 2019-07-01 17:21:16
             * State : 2
             * Password : WLn1kJuIVBQ=
             * Photo : null
             * PayKey : null
             * AuthType : null
             * AuthUrl : null
             * AuthResult : null
             */

            private String Id;
            private String DepartmentId;
            private String Name;
            private Object EmpId;
            private Object IdCard;
            private int Sex;
            private int Age;
            private Object Address;
            private String Phone;
            private String CreateTime;
            private int State;
            private String Password;
            private Object Photo;
            private Object PayKey;
            private Object AuthType;
            private Object AuthUrl;
            private Object AuthResult;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getDepartmentId() {
                return DepartmentId;
            }

            public void setDepartmentId(String DepartmentId) {
                this.DepartmentId = DepartmentId;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public Object getEmpId() {
                return EmpId;
            }

            public void setEmpId(Object EmpId) {
                this.EmpId = EmpId;
            }

            public Object getIdCard() {
                return IdCard;
            }

            public void setIdCard(Object IdCard) {
                this.IdCard = IdCard;
            }

            public int getSex() {
                return Sex;
            }

            public void setSex(int Sex) {
                this.Sex = Sex;
            }

            public int getAge() {
                return Age;
            }

            public void setAge(int Age) {
                this.Age = Age;
            }

            public Object getAddress() {
                return Address;
            }

            public void setAddress(Object Address) {
                this.Address = Address;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public int getState() {
                return State;
            }

            public void setState(int State) {
                this.State = State;
            }

            public String getPassword() {
                return Password;
            }

            public void setPassword(String Password) {
                this.Password = Password;
            }

            public Object getPhoto() {
                return Photo;
            }

            public void setPhoto(Object Photo) {
                this.Photo = Photo;
            }

            public Object getPayKey() {
                return PayKey;
            }

            public void setPayKey(Object PayKey) {
                this.PayKey = PayKey;
            }

            public Object getAuthType() {
                return AuthType;
            }

            public void setAuthType(Object AuthType) {
                this.AuthType = AuthType;
            }

            public Object getAuthUrl() {
                return AuthUrl;
            }

            public void setAuthUrl(Object AuthUrl) {
                this.AuthUrl = AuthUrl;
            }

            public Object getAuthResult() {
                return AuthResult;
            }

            public void setAuthResult(Object AuthResult) {
                this.AuthResult = AuthResult;
            }
        }
    }
}
