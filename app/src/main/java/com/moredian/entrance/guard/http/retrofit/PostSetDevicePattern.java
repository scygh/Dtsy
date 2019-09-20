package com.moredian.entrance.guard.http.retrofit;

import com.moredian.entrance.guard.entity.PostResponse;
import com.moredian.entrance.guard.entity.PostsetDevicePattern;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/20 11:09
 */
public interface PostSetDevicePattern {

    @POST("Api/Device/Set")
    Observable<PostResponse> postSetDevicePattern(@Body PostsetDevicePattern postRequestBody, @Query("id") Integer deviceId, @Header("AccessToken") String token);
}
