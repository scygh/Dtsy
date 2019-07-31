package com.moredian.entrance.guard.http;

import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.http.retrofit.GetListByPageService;
import com.moredian.entrance.guard.http.retrofit.GetTokenService;

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

}
