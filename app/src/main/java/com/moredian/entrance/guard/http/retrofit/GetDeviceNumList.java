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
public interface GetDeviceNumList {

    @GET("Api/Device/GetList")
    Observable<com.moredian.entrance.guard.entity.GetDeviceNumList> getDeviceNumList(@Header("AccessToken") String token);
}
