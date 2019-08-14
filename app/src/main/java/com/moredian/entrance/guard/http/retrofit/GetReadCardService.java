package com.moredian.entrance.guard.http.retrofit;

import com.moredian.entrance.guard.entity.GetReadCard;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/12 08:56
 */
public interface GetReadCardService {

    @GET("Api/User/Card/Read")
    Observable<GetReadCard> GetReadCard(@Query("companyCode") Integer companyCode, @Query("deviceID") Integer deviceID, @Query("number") Integer number, @Header("AccessToken") String token, @Header("moredianToken") String modiantoken);
}
