package com.moredian.entrance.guard.http.retrofit;

import com.moredian.entrance.guard.entity.FaceExpense;
import com.moredian.entrance.guard.entity.PostFaceExpenseBody;
import com.moredian.entrance.guard.entity.PostQRCodeExpenseBody;
import com.moredian.entrance.guard.entity.QRCodeExpense;

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
public interface PostFaceExpense {

    @POST("Api/Expense/MoreDianFaceExpense")
    Observable<FaceExpense> faceExpense(@Body PostFaceExpenseBody postFaceExpenseBody, @Header("AccessToken") String token, @Header("moredianToken") String modiantoken);
}
