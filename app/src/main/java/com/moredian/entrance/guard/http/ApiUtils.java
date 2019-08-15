package com.moredian.entrance.guard.http;

import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.http.retrofit.GetListByPageService;
import com.moredian.entrance.guard.http.retrofit.GetReadCardService;
import com.moredian.entrance.guard.http.retrofit.GetTokenService;
import com.moredian.entrance.guard.http.retrofit.PostCreate;
import com.moredian.entrance.guard.http.retrofit.PostDelete;
import com.moredian.entrance.guard.http.retrofit.PostFaceExpense;
import com.moredian.entrance.guard.http.retrofit.PostQRCodeExpense;
import com.moredian.entrance.guard.http.retrofit.PostSimpleExpense;
import com.moredian.entrance.guard.http.retrofit.PostUpdate;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/31 11:29
 */
public class ApiUtils {
    public static final String BASE_URL = Constants.SERVER_URL;

    /**
     * descirption: 账户密码登录换取Token
     */
    public static GetTokenService getTokenService() {
        return RetrofitClient.getClient(BASE_URL).create(GetTokenService.class);
    }

    /**
     * descirption: 分页查询消费者
     */
    public static GetListByPageService getListByPageService() {
        return RetrofitClient.getClient(BASE_URL).create(GetListByPageService.class);
    }

    /**
     * descirption:创建魔点人员
     */
    public static PostCreate postCreateService() {
        return RetrofitClient.getClient(BASE_URL).create(PostCreate.class);
    }

    /**
     * descirption:删除魔点人员
     */
    public static PostDelete postDeleteService() {
        return RetrofitClient.getClient(BASE_URL).create(PostDelete.class);
    }

    /**
     * descirption:更新魔点人员（上传头像）
     */
    public static PostUpdate postUpdateService() {
        return RetrofitClient.getClient(BASE_URL).create(PostUpdate.class);
    }

    /**
    * descirption: 读取卡信息
    */
    public static GetReadCardService getReadCardService() {
        return RetrofitClient.getClient(BASE_URL).create(GetReadCardService.class);
    }

    /**
     * descirption:简单支付
     */
    public static PostSimpleExpense postSimpleExpenseService() {
        return RetrofitClient.getClient(BASE_URL).create(PostSimpleExpense.class);
    }

    /**
     * descirption:出示二维码支付
     */
    public static PostQRCodeExpense postQRCodeExpenseService() {
        return RetrofitClient.getClient(BASE_URL).create(PostQRCodeExpense.class);
    }

    /**
     * descirption:魔点人脸支付
     */
    public static PostFaceExpense postFaceExpenseService() {
        return RetrofitClient.getClient(BASE_URL).create(PostFaceExpense.class);
    }
}
