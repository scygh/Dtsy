package com.moredian.entrance.guard.http;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetListByPage;
import com.moredian.entrance.guard.entity.GetToken;
import com.moredian.entrance.guard.view.activity.LoginActivity;
import com.moredian.entrance.guard.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/31 13:58
 */
public class Api {

    private static final String TAG = "Api";

    List<GetListByPage.ContentBean.RowsBean> rowsBeans;

    private OnResponse onResponse;
    public interface OnResponse {
        void onResponse(List<GetListByPage.ContentBean.RowsBean> rowsBeans);
        void onResponseMore(List<GetListByPage.ContentBean.RowsBean> rowsBeans);
    }
    public void setOnResponse(OnResponse onResponse) {
        this.onResponse = onResponse;
    }

    /**
    * descirption: 获取登录者的信息
    */
    public void getToken(String name, String password, Context context) {
        ApiUtils.getTokenService().getToken(name,password).enqueue(new Callback<GetToken>() {
            @Override
            public void onResponse(Call<GetToken> call, Response<GetToken> response) {
                GetToken getToken = response.body();
                if (getToken != null) {
                    if (getToken.getStatusCode() == 200) {
                        GetToken.ContentBean contentBean = getToken.getContent();
                        SPUtils.getInstance().put(Constants.ISLOGIN, true);
                        SPUtils.getInstance().put(Constants.ACCESSTOKEN, contentBean.getAccessToken());
                        SPUtils.getInstance().put(Constants.USERID, contentBean.getUserID());
                        SPUtils.getInstance().put(Constants.ACCOUNT, contentBean.getAccount());
                        ToastUtils.showShort("登录成功");
                        context.startActivity(MainActivity.getMainActivityIntent(context));
                        ((LoginActivity)context).finish();
                    }
                } else {
                    ToastUtils.showShort("用户名或者密码错误");
                }
            }
            @Override
            public void onFailure(Call<GetToken> call, Throwable t) {
                ToastUtils.showShort("网络连接失败");
            }
        });
    }

    /**
    * descirption: 获取消费者列表
    */
    public void getListByPage(int pageIndex) {
        String token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
        Log.d(TAG, "onResponse: tokene" + token);
        if (token != null) {
            ApiUtils.getListByPageService().getListByPage(token, pageIndex, 20).enqueue(new Callback<GetListByPage>() {
                @Override
                public void onResponse(Call<GetListByPage> call, Response<GetListByPage> response) {
                    GetListByPage getListByPage = response.body();
                    if (getListByPage != null) {
                        if (getListByPage.getStatusCode() == 200) {
                            rowsBeans = getListByPage.getContent().getRows();
                            if (onResponse != null) {
                                if (pageIndex > 1) {
                                    onResponse.onResponseMore(rowsBeans);
                                } else {
                                    onResponse.onResponse(rowsBeans);
                                }
                            }
                        }
                    } else {
                        ToastUtils.showShort("页面不存在");
                    }
                }
                @Override
                public void onFailure(Call<GetListByPage> call, Throwable t) {
                    ToastUtils.showShort("网络连接失败");
                }
            });
        } else {
            ToastUtils.showShort("Token获取失败");
        }
    }
}
