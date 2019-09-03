package com.moredian.entrance.guard.http.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;


/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/2 13:45
 */
public interface GetChannel {

    @GET("Api/MoneyExchange/GetChannel")
    Observable<com.moredian.entrance.guard.entity.GetChannel> getChannle(@Header("AccessToken") String token);
}
