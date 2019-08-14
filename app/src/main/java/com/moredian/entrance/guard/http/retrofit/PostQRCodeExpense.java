package com.moredian.entrance.guard.http.retrofit;

import com.moredian.entrance.guard.entity.PostQRCodeExpenseBody;
import com.moredian.entrance.guard.entity.PostSimpleExpenseBody;
import com.moredian.entrance.guard.entity.QRCodeExpense;
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
public interface PostQRCodeExpense {

    @POST("Api/Expense/QRExpense")
    Observable<QRCodeExpense> QRCodeExpense(@Body PostQRCodeExpenseBody postQRCodeExpenseBody, @Header("AccessToken") String token, @Header("moredianToken") String modiantoken);
}
