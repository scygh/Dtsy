package com.moredian.entrance.guard.http.retrofit;

import com.moredian.entrance.guard.entity.PostResponse;
import com.moredian.entrance.guard.entity.PostRequestBody;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/1 15:59
 */
public interface PostUpdate {

    @POST("api/Member/Update")
    Observable<PostResponse> createTask(@Body PostRequestBody postRequestBody, @Header("AccessToken") String token, @Header("moredianToken") String modiantoken);
}