package com.moredian.entrance.guard.http.retrofit;

import com.moredian.entrance.guard.entity.PostRequestBody;
import com.moredian.entrance.guard.entity.PostResponse;
import com.moredian.entrance.guard.entity.PostSimpleExpenseBody;
import com.moredian.entrance.guard.entity.SimpleExpense;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/12 17:17
 */
public interface PostSimpleExpense {

    @POST("Api/Expense/SimpleExpense")
    Observable<SimpleExpense> simpleExpense(@Body PostSimpleExpenseBody postRequestBody, @Header("AccessToken") String token, @Header("moredianToken") String modiantoken);
}
