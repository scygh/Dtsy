package com.moredian.entrance.guard.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/31 11:33
 */
public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static  Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
