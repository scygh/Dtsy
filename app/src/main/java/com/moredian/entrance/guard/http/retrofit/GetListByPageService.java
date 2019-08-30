package com.moredian.entrance.guard.http.retrofit;

import com.moredian.entrance.guard.entity.GetListByPage;
import com.moredian.entrance.guard.entity.GetToken;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/31 11:32
 */
public interface GetListByPageService {

    @GET("Api/User/GetPage")
    Observable<GetListByPage> getListByPage(@Header("AccessToken") String token, @Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize);
}
