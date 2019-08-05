package com.moredian.entrance.guard.http.retrofit;

import com.moredian.entrance.guard.entity.GetToken;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/31 11:32
 */
public interface GetTokenService {

    @GET("Api/Token/GetToken")
    Call<GetToken> getToken(@Query("account") String account, @Query("password") String password);
}
