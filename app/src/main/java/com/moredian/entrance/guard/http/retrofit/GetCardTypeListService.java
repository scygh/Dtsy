package com.moredian.entrance.guard.http.retrofit;

import com.moredian.entrance.guard.entity.GetCardTypeList;
import com.moredian.entrance.guard.entity.GetDepartmentList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/31 11:32
 */
public interface GetCardTypeListService {

    @GET("Api/CardType/GetList")
    Observable<GetCardTypeList> getCardTypeListService(@Header("AccessToken") String token);
}
