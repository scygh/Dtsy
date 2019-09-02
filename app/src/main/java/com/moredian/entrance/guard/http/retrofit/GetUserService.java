package com.moredian.entrance.guard.http.retrofit;

import com.moredian.entrance.guard.entity.GetCardTypeList;
import com.moredian.entrance.guard.entity.GetUser;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/31 11:32
 */
public interface GetUserService {

    @GET("Api/User/GetByNumber")
    Observable<GetUser> getUserService(@Header("AccessToken") String token, @Query("number") Integer number);
}
