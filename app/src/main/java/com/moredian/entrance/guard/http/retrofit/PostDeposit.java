package com.moredian.entrance.guard.http.retrofit;

import com.moredian.entrance.guard.entity.PostDepositBody;
import com.moredian.entrance.guard.entity.PostRequestBody;
import com.moredian.entrance.guard.entity.PostResponse;
import com.moredian.entrance.guard.entity.PostResponseNoContent;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/1 15:59
 */
public interface PostDeposit {

    @POST("Api/MoneyExchange/Deposit")
    Observable<PostResponseNoContent> deposit(@Body PostDepositBody postRequestBody, @Header("AccessToken") String token);
}
