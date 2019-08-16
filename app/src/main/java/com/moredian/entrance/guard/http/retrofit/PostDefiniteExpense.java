package com.moredian.entrance.guard.http.retrofit;

import com.moredian.entrance.guard.entity.DefiniteExpense;
import com.moredian.entrance.guard.entity.PostDefiniteExpenseBody;

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
public interface PostDefiniteExpense {

    @POST("Api/Expense/DefiniteExpense")
    Observable<DefiniteExpense> definiteExpense(@Body PostDefiniteExpenseBody postDefiniteExpenseBody, @Header("AccessToken") String token, @Header("moredianToken") String modiantoken);
}
