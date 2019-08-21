package com.moredian.entrance.guard.http.retrofit;

import com.moredian.entrance.guard.entity.GetReadCard;
import com.moredian.entrance.guard.entity.GetUserByUserID;

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
public interface GetUserByUserIdService {

    @GET("Api/User/GetByUserID")
    Observable<GetUserByUserID> GetByUserID(@Query("userID") String userID, @Header("AccessToken") String token);
}
