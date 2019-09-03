package com.moredian.entrance.guard.entity;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/2 10:10
 */
public class GetUser {


    /**
     * Content : {"UserID":"6369e769-ef3b-47fe-b7ce-f7f8e931c29c","DepartmentID":"00000000-0000-0000-0000-000000000001","DepartmentName":"默认部门","Name":"scy11","EmpID":"","IDCard":"","Sex":0,"Age":0,"Address":"","Phone":"","UserCreateTime":"2019-08-01 10:40:10","Password":"","Photo":null,"PayKey":"1111","CardTypeName":"正常卡","UserState":1,"Number":22,"SerialNo":"scy11","CardType":1,"IsDiscount":false,"IsGotCard":1,"DiscountRate":100,"Foregift":0,"SubsidyLevel":0,"SubsidyLevelName":"无补贴","Cost":0,"Deadline":"2029-09-09 00:00:00","PayCount":220,"LastSubsidyDate":"2019-07-01 10:40:10","SubsidyDatediff":2,"CardCreateTime":"2019-08-01 10:40:10","CardState":1,"Cash":92650,"Subsidy":0,"Times":0,"Donate":2.88,"Integral":0,"LastPayDateTime":"2019-09-03 10:37:55","AuthType":null,"AuthUrl":"","AuthResult":"","CreateSqlCommand":null}
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
         * UserID : 6369e769-ef3b-47fe-b7ce-f7f8e931c29c
         * DepartmentID : 00000000-0000-0000-0000-000000000001
         * DepartmentName : 默认部门
         * Name : scy11
         * EmpID :
         * IDCard :
         * Sex : 0
         * Age : 0
         * Address :
         * Phone :
         * UserCreateTime : 2019-08-01 10:40:10
         * Password :
         * Photo : null
         * PayKey : 1111
         * CardTypeName : 正常卡
         * UserState : 1
         * Number : 22
         * SerialNo : scy11
         * CardType : 1
         * IsDiscount : false
         * IsGotCard : 1
         * DiscountRate : 100
         * Foregift : 0
         * SubsidyLevel : 0
         * SubsidyLevelName : 无补贴
         * Cost : 0
         * Deadline : 2029-09-09 00:00:00
         * PayCount : 220
         * LastSubsidyDate : 2019-07-01 10:40:10
         * SubsidyDatediff : 2
         * CardCreateTime : 2019-08-01 10:40:10
         * CardState : 1
         * Cash : 92650
         * Subsidy : 0
         * Times : 0
         * Donate : 2.88
         * Integral : 0
         * LastPayDateTime : 2019-09-03 10:37:55
         * AuthType : null
         * AuthUrl :
         * AuthResult :
         * CreateSqlCommand : null
         */

        private String UserID;
        private String DepartmentID;
        private String DepartmentName;
        private String Name;
        private String EmpID;
        private String IDCard;
        private int Sex;
        private int Age;
        private String Address;
        private String Phone;
        private String UserCreateTime;
        private String Password;
        private Object Photo;
        private String PayKey;
        private String CardTypeName;
        private int UserState;
        private int Number;
        private String SerialNo;
        private int CardType;
        private boolean IsDiscount;
        private int IsGotCard;
        private int DiscountRate;
        private int Foregift;
        private int SubsidyLevel;
        private String SubsidyLevelName;
        private int Cost;
        private String Deadline;
        private int PayCount;
        private String LastSubsidyDate;
        private int SubsidyDatediff;
        private String CardCreateTime;
        private int CardState;
        private int Cash;
        private int Subsidy;
        private int Times;
        private double Donate;
        private int Integral;
        private String LastPayDateTime;
        private Object AuthType;
        private String AuthUrl;
        private String AuthResult;
        private Object CreateSqlCommand;

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getDepartmentID() {
            return DepartmentID;
        }

        public void setDepartmentID(String DepartmentID) {
            this.DepartmentID = DepartmentID;
        }

        public String getDepartmentName() {
            return DepartmentName;
        }

        public void setDepartmentName(String DepartmentName) {
            this.DepartmentName = DepartmentName;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getEmpID() {
            return EmpID;
        }

        public void setEmpID(String EmpID) {
            this.EmpID = EmpID;
        }

        public String getIDCard() {
            return IDCard;
        }

        public void setIDCard(String IDCard) {
            this.IDCard = IDCard;
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

        public String getUserCreateTime() {
            return UserCreateTime;
        }

        public void setUserCreateTime(String UserCreateTime) {
            this.UserCreateTime = UserCreateTime;
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

        public String getPayKey() {
            return PayKey;
        }

        public void setPayKey(String PayKey) {
            this.PayKey = PayKey;
        }

        public String getCardTypeName() {
            return CardTypeName;
        }

        public void setCardTypeName(String CardTypeName) {
            this.CardTypeName = CardTypeName;
        }

        public int getUserState() {
            return UserState;
        }

        public void setUserState(int UserState) {
            this.UserState = UserState;
        }

        public int getNumber() {
            return Number;
        }

        public void setNumber(int Number) {
            this.Number = Number;
        }

        public String getSerialNo() {
            return SerialNo;
        }

        public void setSerialNo(String SerialNo) {
            this.SerialNo = SerialNo;
        }

        public int getCardType() {
            return CardType;
        }

        public void setCardType(int CardType) {
            this.CardType = CardType;
        }

        public boolean isIsDiscount() {
            return IsDiscount;
        }

        public void setIsDiscount(boolean IsDiscount) {
            this.IsDiscount = IsDiscount;
        }

        public int getIsGotCard() {
            return IsGotCard;
        }

        public void setIsGotCard(int IsGotCard) {
            this.IsGotCard = IsGotCard;
        }

        public int getDiscountRate() {
            return DiscountRate;
        }

        public void setDiscountRate(int DiscountRate) {
            this.DiscountRate = DiscountRate;
        }

        public int getForegift() {
            return Foregift;
        }

        public void setForegift(int Foregift) {
            this.Foregift = Foregift;
        }

        public int getSubsidyLevel() {
            return SubsidyLevel;
        }

        public void setSubsidyLevel(int SubsidyLevel) {
            this.SubsidyLevel = SubsidyLevel;
        }

        public String getSubsidyLevelName() {
            return SubsidyLevelName;
        }

        public void setSubsidyLevelName(String SubsidyLevelName) {
            this.SubsidyLevelName = SubsidyLevelName;
        }

        public int getCost() {
            return Cost;
        }

        public void setCost(int Cost) {
            this.Cost = Cost;
        }

        public String getDeadline() {
            return Deadline;
        }

        public void setDeadline(String Deadline) {
            this.Deadline = Deadline;
        }

        public int getPayCount() {
            return PayCount;
        }

        public void setPayCount(int PayCount) {
            this.PayCount = PayCount;
        }

        public String getLastSubsidyDate() {
            return LastSubsidyDate;
        }

        public void setLastSubsidyDate(String LastSubsidyDate) {
            this.LastSubsidyDate = LastSubsidyDate;
        }

        public int getSubsidyDatediff() {
            return SubsidyDatediff;
        }

        public void setSubsidyDatediff(int SubsidyDatediff) {
            this.SubsidyDatediff = SubsidyDatediff;
        }

        public String getCardCreateTime() {
            return CardCreateTime;
        }

        public void setCardCreateTime(String CardCreateTime) {
            this.CardCreateTime = CardCreateTime;
        }

        public int getCardState() {
            return CardState;
        }

        public void setCardState(int CardState) {
            this.CardState = CardState;
        }

        public int getCash() {
            return Cash;
        }

        public void setCash(int Cash) {
            this.Cash = Cash;
        }

        public int getSubsidy() {
            return Subsidy;
        }

        public void setSubsidy(int Subsidy) {
            this.Subsidy = Subsidy;
        }

        public int getTimes() {
            return Times;
        }

        public void setTimes(int Times) {
            this.Times = Times;
        }

        public double getDonate() {
            return Donate;
        }

        public void setDonate(double Donate) {
            this.Donate = Donate;
        }

        public int getIntegral() {
            return Integral;
        }

        public void setIntegral(int Integral) {
            this.Integral = Integral;
        }

        public String getLastPayDateTime() {
            return LastPayDateTime;
        }

        public void setLastPayDateTime(String LastPayDateTime) {
            this.LastPayDateTime = LastPayDateTime;
        }

        public Object getAuthType() {
            return AuthType;
        }

        public void setAuthType(Object AuthType) {
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

        public Object getCreateSqlCommand() {
            return CreateSqlCommand;
        }

        public void setCreateSqlCommand(Object CreateSqlCommand) {
            this.CreateSqlCommand = CreateSqlCommand;
        }
    }
}
