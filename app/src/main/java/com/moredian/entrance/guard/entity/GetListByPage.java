package com.moredian.entrance.guard.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/31 13:35
 */
public class GetListByPage {


    /**
     * Content : {"Count":21,"Rows":[{"User":{"Id":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy11","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:40:10","State":1,"Password":"EIcSosWqXyA=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{"MemberId":"1640744568733302785","MemberFace":"MD_1001_6369e769-ef3b-47fe-b7ce-f7f8e931c29c","MemberType":2}},{"User":{"Id":"e4f13882-a8a6-4754-b709-058ab43db9de","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy10","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:39:57","State":1,"Password":"rFffFDBciN0=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{"MemberId":"1640735867129561089","MemberFace":"MD_1001_e4f13882-a8a6-4754-b709-058ab43db9de","MemberType":2}},{"User":{"Id":"d338e207-68af-4bd2-a13d-184f8b6a9260","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy9","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:39:34","State":1,"Password":"6ko5QPET07s=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"913475b4-2b49-4dda-83c4-1793fa1480cd","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy8","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:38:29","State":1,"Password":"bw58we3N/FM=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{"MemberId":"1640984992412598273","MemberFace":"MD_1001_913475b4-2b49-4dda-83c4-1793fa1480cd","MemberType":2}},{"User":{"Id":"7a022c58-fa6e-45a4-8e37-795d425837ce","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy7","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:38:13","State":1,"Password":"lRYx6pxMznU=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"55a0d5c3-e22c-4506-ac78-71a6887ffda4","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy6","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:37:56","State":1,"Password":"1NpnN894cQI=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{"MemberId":"1641082548291371008","MemberFace":"MD_1001_55a0d5c3-e22c-4506-ac78-71a6887ffda4","MemberType":2}},{"User":{"Id":"27719cc9-f3a8-4b78-a6d8-230dd8a0458a","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy5","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:37:05","State":1,"Password":"2TlDbSx3lL8=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{"MemberId":"1640718240575389697","MemberFace":"MD_1001_27719cc9-f3a8-4b78-a6d8-230dd8a0458a","MemberType":2}},{"User":{"Id":"276f3b86-a6e9-4f5f-8d58-11ec33beae77","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy4","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:36:43","State":1,"Password":"5DKc0W7dyBY=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{"MemberId":"1641003349094432768","MemberFace":"MD_1001_276f3b86-a6e9-4f5f-8d58-11ec33beae77","MemberType":2}},{"User":{"Id":"9e795d1c-49ea-4968-b632-c5f1273aaefa","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy3","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:36:30","State":1,"Password":"AvMQpzF6s68=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"4aca6102-1c83-489f-893c-b017dd78fc55","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy2","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:36:17","State":1,"Password":"cXzZc/YuBDE=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"c306c66b-896d-4e10-8b5e-0c3e119534eb","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy1","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:35:59","State":1,"Password":"ZuaHjxxwYDQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"ae752aef-e224-4a8a-b7bc-e1a8ae949755","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"llll","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-07-22 16:01:56","State":1,"Password":"WzyM/ebH5As=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"7e62e249-9ff8-4d26-8379-0f424f0f2e79","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"曹瑞","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"13814059984","CreateTime":"2019-07-01 17:21:16","State":2,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"b4982895-fbee-4745-8f43-e6687a790069","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"005","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"15380326726","CreateTime":"2019-07-01 17:09:52","State":1,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"d1ea68fe-a4f5-42f4-bfea-b14049faef9a","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"靓仔","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"13221817181","CreateTime":"2019-07-01 16:07:16","State":1,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"87d0d2c3-04fd-4026-848a-fa9e5e90cc81","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"15558136957","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"15558136957","CreateTime":"2019-07-01 08:56:05","State":1,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"49c9ccfd-aed9-4d15-be35-7e90a2f9d2f5","DepartmentId":"00000000-0000-0000-0000-000000000000","Name":"鲁炳","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"17557289603","CreateTime":"2019-06-28 09:34:33","State":2,"Password":"ZIigWXeS1dQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"6d4e1341-d458-4d94-8d08-c30f96c9ca5c","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"临时卡","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-06-27 14:11:37","State":1,"Password":"vVAjaOcQo4g=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"aacfda7c-d289-4cca-a0fe-8e316894d354","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"测试","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-06-27 12:54:29","State":1,"Password":"O4Q7SfJYv9k=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"1ebd5586-1551-48fa-a3dd-9f22260915e4","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"001","EmpId":"","IdCard":"","Sex":0,"Age":0,"Address":"","Phone":"","CreateTime":"2019-06-26 15:29:08","State":1,"Password":"Wabg2EsJTng=","Photo":null,"PayKey":"","AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}}]}
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
         * Count : 21
         * Rows : [{"User":{"Id":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy11","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:40:10","State":1,"Password":"EIcSosWqXyA=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{"MemberId":"1640744568733302785","MemberFace":"MD_1001_6369e769-ef3b-47fe-b7ce-f7f8e931c29c","MemberType":2}},{"User":{"Id":"e4f13882-a8a6-4754-b709-058ab43db9de","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy10","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:39:57","State":1,"Password":"rFffFDBciN0=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{"MemberId":"1640735867129561089","MemberFace":"MD_1001_e4f13882-a8a6-4754-b709-058ab43db9de","MemberType":2}},{"User":{"Id":"d338e207-68af-4bd2-a13d-184f8b6a9260","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy9","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:39:34","State":1,"Password":"6ko5QPET07s=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"913475b4-2b49-4dda-83c4-1793fa1480cd","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy8","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:38:29","State":1,"Password":"bw58we3N/FM=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{"MemberId":"1640984992412598273","MemberFace":"MD_1001_913475b4-2b49-4dda-83c4-1793fa1480cd","MemberType":2}},{"User":{"Id":"7a022c58-fa6e-45a4-8e37-795d425837ce","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy7","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:38:13","State":1,"Password":"lRYx6pxMznU=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"55a0d5c3-e22c-4506-ac78-71a6887ffda4","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy6","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:37:56","State":1,"Password":"1NpnN894cQI=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{"MemberId":"1641082548291371008","MemberFace":"MD_1001_55a0d5c3-e22c-4506-ac78-71a6887ffda4","MemberType":2}},{"User":{"Id":"27719cc9-f3a8-4b78-a6d8-230dd8a0458a","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy5","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:37:05","State":1,"Password":"2TlDbSx3lL8=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{"MemberId":"1640718240575389697","MemberFace":"MD_1001_27719cc9-f3a8-4b78-a6d8-230dd8a0458a","MemberType":2}},{"User":{"Id":"276f3b86-a6e9-4f5f-8d58-11ec33beae77","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy4","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:36:43","State":1,"Password":"5DKc0W7dyBY=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{"MemberId":"1641003349094432768","MemberFace":"MD_1001_276f3b86-a6e9-4f5f-8d58-11ec33beae77","MemberType":2}},{"User":{"Id":"9e795d1c-49ea-4968-b632-c5f1273aaefa","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy3","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:36:30","State":1,"Password":"AvMQpzF6s68=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"4aca6102-1c83-489f-893c-b017dd78fc55","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy2","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:36:17","State":1,"Password":"cXzZc/YuBDE=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"c306c66b-896d-4e10-8b5e-0c3e119534eb","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy1","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:35:59","State":1,"Password":"ZuaHjxxwYDQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"ae752aef-e224-4a8a-b7bc-e1a8ae949755","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"llll","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-07-22 16:01:56","State":1,"Password":"WzyM/ebH5As=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"7e62e249-9ff8-4d26-8379-0f424f0f2e79","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"曹瑞","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"13814059984","CreateTime":"2019-07-01 17:21:16","State":2,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"b4982895-fbee-4745-8f43-e6687a790069","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"005","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"15380326726","CreateTime":"2019-07-01 17:09:52","State":1,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"d1ea68fe-a4f5-42f4-bfea-b14049faef9a","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"靓仔","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"13221817181","CreateTime":"2019-07-01 16:07:16","State":1,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"87d0d2c3-04fd-4026-848a-fa9e5e90cc81","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"15558136957","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"15558136957","CreateTime":"2019-07-01 08:56:05","State":1,"Password":"WLn1kJuIVBQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"49c9ccfd-aed9-4d15-be35-7e90a2f9d2f5","DepartmentId":"00000000-0000-0000-0000-000000000000","Name":"鲁炳","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":"17557289603","CreateTime":"2019-06-28 09:34:33","State":2,"Password":"ZIigWXeS1dQ=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"6d4e1341-d458-4d94-8d08-c30f96c9ca5c","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"临时卡","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-06-27 14:11:37","State":1,"Password":"vVAjaOcQo4g=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"aacfda7c-d289-4cca-a0fe-8e316894d354","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"测试","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-06-27 12:54:29","State":1,"Password":"O4Q7SfJYv9k=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}},{"User":{"Id":"1ebd5586-1551-48fa-a3dd-9f22260915e4","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"001","EmpId":"","IdCard":"","Sex":0,"Age":0,"Address":"","Phone":"","CreateTime":"2019-06-26 15:29:08","State":1,"Password":"Wabg2EsJTng=","Photo":null,"PayKey":"","AuthType":null,"AuthUrl":null,"AuthResult":null},"UserFace":{}}]
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

        public static class RowsBean implements Parcelable {
            /**
             * User : {"Id":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","DepartmentId":"00000000-0000-0000-0000-000000000001","Name":"scy11","EmpId":null,"IdCard":null,"Sex":0,"Age":0,"Address":null,"Phone":null,"CreateTime":"2019-08-01 10:40:10","State":1,"Password":"EIcSosWqXyA=","Photo":null,"PayKey":null,"AuthType":null,"AuthUrl":null,"AuthResult":null}
             * UserFace : {"MemberId":"1640744568733302785","MemberFace":"MD_1001_6369e769-ef3b-47fe-b7ce-f7f8e931c29c","MemberType":2}
             */


            private UserBean User;
            private UserFaceBean UserFace;

            public UserBean getUser() {
                return User;
            }

            public void setUser(UserBean User) {
                this.User = User;
            }

            public UserFaceBean getUserFace() {
                return UserFace;
            }

            public void setUserFace(UserFaceBean UserFace) {
                this.UserFace = UserFace;
            }

            public static class UserBean implements Parcelable {


                /**
                 * Id : 6369e769-ef3b-47fe-b7ce-f7f8e931c29c
                 * DepartmentId : 00000000-0000-0000-0000-000000000001
                 * Name : scy11
                 * EmpId : null
                 * IdCard : null
                 * Sex : 0
                 * Age : 0
                 * Address : null
                 * Phone : null
                 * CreateTime : 2019-08-01 10:40:10
                 * State : 1
                 * Password : EIcSosWqXyA=
                 * Photo : null
                 * PayKey : null
                 * AuthType : null
                 * AuthUrl : null
                 * AuthResult : null
                 */

                private String Id;
                private String DepartmentId;
                private String Name;
                private String EmpId;
                private String IdCard;
                private int Sex;
                private int Age;
                private String Address;
                private String Phone;
                private String CreateTime;
                private int State;
                private String Password;
                private String Photo;
                private String PayKey;
                private String AuthType;
                private String AuthUrl;
                private String AuthResult;

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

                public String getEmpId() {
                    return EmpId;
                }

                public void setEmpId(String EmpId) {
                    this.EmpId = EmpId;
                }

                public String getIdCard() {
                    return IdCard;
                }

                public void setIdCard(String IdCard) {
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

                public String getAddress() {
                    return Address;
                }

                public void setAddress(String Address) {
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

                public String getPhoto() {
                    return Photo;
                }

                public void setPhoto(String Photo) {
                    this.Photo = Photo;
                }

                public String getPayKey() {
                    return PayKey;
                }

                public void setPayKey(String PayKey) {
                    this.PayKey = PayKey;
                }

                public String getAuthType() {
                    return AuthType;
                }

                public void setAuthType(String AuthType) {
                    this.AuthType = AuthType;
                }

                public String getAuthUrl() {
                    return AuthUrl;
                }

                public void setAuthUrl(String AuthUrl) {
                    this.AuthUrl = AuthUrl;
                }

                public String getAuthResult() {
                    return AuthResult;
                }

                public void setAuthResult(String AuthResult) {
                    this.AuthResult = AuthResult;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.Id);
                    dest.writeString(this.DepartmentId);
                    dest.writeString(this.Name);
                    dest.writeString(this.EmpId);
                    dest.writeString(this.IdCard);
                    dest.writeInt(this.Sex);
                    dest.writeInt(this.Age);
                    dest.writeString(this.Address);
                    dest.writeString(this.Phone);
                    dest.writeString(this.CreateTime);
                    dest.writeInt(this.State);
                    dest.writeString(this.Password);
                    dest.writeString(this.Photo);
                    dest.writeString(this.PayKey);
                    dest.writeString(this.AuthType);
                    dest.writeString(this.AuthUrl);
                    dest.writeString(this.AuthResult);
                }

                public UserBean() {
                }

                protected UserBean(Parcel in) {
                    this.Id = in.readString();
                    this.DepartmentId = in.readString();
                    this.Name = in.readString();
                    this.EmpId = in.readString();
                    this.IdCard = in.readString();
                    this.Sex = in.readInt();
                    this.Age = in.readInt();
                    this.Address = in.readString();
                    this.Phone = in.readString();
                    this.CreateTime = in.readString();
                    this.State = in.readInt();
                    this.Password = in.readString();
                    this.Photo = in.readString();
                    this.PayKey = in.readString();
                    this.AuthType = in.readString();
                    this.AuthUrl = in.readString();
                    this.AuthResult = in.readString();
                }

                public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
                    @Override
                    public UserBean createFromParcel(Parcel source) {
                        return new UserBean(source);
                    }

                    @Override
                    public UserBean[] newArray(int size) {
                        return new UserBean[size];
                    }
                };
            }

            public static class UserFaceBean implements Parcelable {
                /**
                 * MemberId : 1640744568733302785
                 * MemberFace : MD_1001_6369e769-ef3b-47fe-b7ce-f7f8e931c29c
                 * MemberType : 2
                 */

                private String MemberBase64;
                private String MemberId;
                private String MemberFace;
                private int MemberType;

                public String getMemberBase64() {
                    return MemberBase64;
                }

                public void setMemberBase64(String memberBase64) {
                    MemberBase64 = memberBase64;
                }

                public String getMemberId() {
                    return MemberId;
                }

                public void setMemberId(String MemberId) {
                    this.MemberId = MemberId;
                }

                public String getMemberFace() {
                    return MemberFace;
                }

                public void setMemberFace(String MemberFace) {
                    this.MemberFace = MemberFace;
                }

                public int getMemberType() {
                    return MemberType;
                }

                public void setMemberType(int MemberType) {
                    this.MemberType = MemberType;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.MemberBase64);
                    dest.writeString(this.MemberId);
                    dest.writeString(this.MemberFace);
                    dest.writeInt(this.MemberType);
                }

                public UserFaceBean() {
                }

                protected UserFaceBean(Parcel in) {
                    this.MemberBase64 = in.readString();
                    this.MemberId = in.readString();
                    this.MemberFace = in.readString();
                    this.MemberType = in.readInt();
                }

                public static final Creator<UserFaceBean> CREATOR = new Creator<UserFaceBean>() {
                    @Override
                    public UserFaceBean createFromParcel(Parcel source) {
                        return new UserFaceBean(source);
                    }

                    @Override
                    public UserFaceBean[] newArray(int size) {
                        return new UserFaceBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeParcelable(this.User, flags);
                dest.writeParcelable(this.UserFace, flags);
            }

            public RowsBean() {
            }

            protected RowsBean(Parcel in) {
                this.User = in.readParcelable(UserBean.class.getClassLoader());
                this.UserFace = in.readParcelable(UserFaceBean.class.getClassLoader());
            }

            public static final Parcelable.Creator<RowsBean> CREATOR = new Parcelable.Creator<RowsBean>() {
                @Override
                public RowsBean createFromParcel(Parcel source) {
                    return new RowsBean(source);
                }

                @Override
                public RowsBean[] newArray(int size) {
                    return new RowsBean[size];
                }
            };
        }
    }
}
