package com.moredian.entrance.guard.http.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/2 13:45
 */
public interface GetDepositPage {

    @GET("Api/ReportCenter/Deposit/Paging")
    Observable<com.moredian.entrance.guard.entity.GetDepositPage> getDepositPage(@Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize, @Query("orderColumn") String orderColumn, @Query("orderPattern") String orderPattern,@Header("AccessToken") String token);
}
