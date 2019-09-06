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
     * Content : [{"DeviceMeal":{"Id":"dee13c9e-f617-4221-ba8c-ca3d57663222","DeviceId":1,"MealId":1,"IsLimitMeal":true,"MealQuota":10,"MealTimes":1,"IsPreferential":true,"PreferentialAmount":1,"MealAmount":10},"Meal":{"Id":1,"Name":"早餐","BeginTime":"07:00","EndTime":"09:00","State":1}},{"DeviceMeal":{"Id":"cd220bb4-aa71-4dbe-aa77-1c9b0080ff63","DeviceId":1,"MealId":2,"IsLimitMeal":false,"MealQuota":0,"MealTimes":0,"IsPreferential":false,"PreferentialAmount":0,"MealAmount":15},"Meal":{"Id":2,"Name":"中餐","BeginTime":"10:00","EndTime":"13:00","State":1}},{"DeviceMeal":{"Id":"dfb8a83e-a027-4713-a098-3bada847445d","DeviceId":1,"MealId":3,"IsLimitMeal":false,"MealQuota":0,"MealTimes":0,"IsPreferential":false,"PreferentialAmount":0,"MealAmount":15},"Meal":{"Id":3,"Name":"晚餐","BeginTime":"17:00","EndTime":"19:00","State":1}},{"DeviceMeal":{"Id":"4075b40e-9da6-4eab-8305-37407d4657ba","DeviceId":1,"MealId":4,"IsLimitMeal":false,"MealQuota":0,"MealTimes":0,"IsPreferential":false,"PreferentialAmount":0,"MealAmount":0.01},"Meal":{"Id":4,"Name":"夜宵","BeginTime":"21:00","EndTime":"00:00","State":1}}]
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
         * DeviceMeal : {"Id":"dee13c9e-f617-4221-ba8c-ca3d57663222","DeviceId":1,"MealId":1,"IsLimitMeal":true,"MealQuota":10,"MealTimes":1,"IsPreferential":true,"PreferentialAmount":1,"MealAmount":10}
         * Meal : {"Id":1,"Name":"早餐","BeginTime":"07:00","EndTime":"09:00","State":1}
         */

        private DeviceMealBean DeviceMeal;
        private MealBean Meal;

        public DeviceMealBean getDeviceMeal() {
            return DeviceMeal;
        }

        public void setDeviceMeal(DeviceMealBean DeviceMeal) {
            this.DeviceMeal = DeviceMeal;
        }

        public MealBean getMeal() {
            return Meal;
        }

        public void setMeal(MealBean Meal) {
            this.Meal = Meal;
        }

        public static class DeviceMealBean {
            /**
             * Id : dee13c9e-f617-4221-ba8c-ca3d57663222
             * DeviceId : 1
             * MealId : 1
             * IsLimitMeal : true
             * MealQuota : 10
             * MealTimes : 1
             * IsPreferential : true
             * PreferentialAmount : 1
             * MealAmount : 10
             */

            private String Id;
            private int DeviceId;
            private int MealId;
            private boolean IsLimitMeal;
            private int MealQuota;
            private int MealTimes;
            private boolean IsPreferential;
            private int PreferentialAmount;
            private int MealAmount;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public int getDeviceId() {
                return DeviceId;
            }

            public void setDeviceId(int DeviceId) {
                this.DeviceId = DeviceId;
            }

            public int getMealId() {
                return MealId;
            }

            public void setMealId(int MealId) {
                this.MealId = MealId;
            }

            public boolean isIsLimitMeal() {
                return IsLimitMeal;
            }

            public void setIsLimitMeal(boolean IsLimitMeal) {
                this.IsLimitMeal = IsLimitMeal;
            }

            public int getMealQuota() {
                return MealQuota;
            }

            public void setMealQuota(int MealQuota) {
                this.MealQuota = MealQuota;
            }

            public int getMealTimes() {
                return MealTimes;
            }

            public void setMealTimes(int MealTimes) {
                this.MealTimes = MealTimes;
            }

            public boolean isIsPreferential() {
                return IsPreferential;
            }

            public void setIsPreferential(boolean IsPreferential) {
                this.IsPreferential = IsPreferential;
            }

            public int getPreferentialAmount() {
                return PreferentialAmount;
            }

            public void setPreferentialAmount(int PreferentialAmount) {
                this.PreferentialAmount = PreferentialAmount;
            }

            public int getMealAmount() {
                return MealAmount;
            }

            public void setMealAmount(int MealAmount) {
                this.MealAmount = MealAmount;
            }
        }

        public static class MealBean {
            /**
             * Id : 1
             * Name : 早餐
             * BeginTime : 07:00
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
}
