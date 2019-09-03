package com.moredian.entrance.guard.http;

import android.content.Context;

import com.blankj.utilcode.util.SPUtils;

import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.DefiniteExpense;
import com.moredian.entrance.guard.entity.FaceExpense;
import com.moredian.entrance.guard.entity.GetCardTypeList;
import com.moredian.entrance.guard.entity.GetChannel;
import com.moredian.entrance.guard.entity.GetDepartmentList;
import com.moredian.entrance.guard.entity.GetDepositPage;
import com.moredian.entrance.guard.entity.GetExpensePage;
import com.moredian.entrance.guard.entity.GetListByPage;
import com.moredian.entrance.guard.entity.GetReadCard;
import com.moredian.entrance.guard.entity.GetSubsidyLevel;
import com.moredian.entrance.guard.entity.GetToken;
import com.moredian.entrance.guard.entity.GetUser;
import com.moredian.entrance.guard.entity.GetUserByUserID;
import com.moredian.entrance.guard.entity.PostDefiniteExpenseBody;
import com.moredian.entrance.guard.entity.PostDepositBody;
import com.moredian.entrance.guard.entity.PostDeregister;
import com.moredian.entrance.guard.entity.PostFaceExpenseBody;
import com.moredian.entrance.guard.entity.PostQRCodeExpenseBody;
import com.moredian.entrance.guard.entity.PostRegister;
import com.moredian.entrance.guard.entity.PostResponse;
import com.moredian.entrance.guard.entity.PostRequestBody;
import com.moredian.entrance.guard.entity.PostResponseNoContent;
import com.moredian.entrance.guard.entity.PostSimpleExpenseBody;
import com.moredian.entrance.guard.entity.QRCodeExpense;
import com.moredian.entrance.guard.entity.ReisterResponse;
import com.moredian.entrance.guard.entity.SimpleExpense;
import com.moredian.entrance.guard.utils.ToastHelper;
import com.moredian.entrance.guard.view.activity.LoginActivity;
import com.moredian.entrance.guard.view.activity.MainActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/31 13:58
 */
public class Api {

    List<GetListByPage.ContentBean.RowsBean> rowsBeans;
    /**
     * descirption:回调list状态接口
     */
    private OnResponse onResponse;

    public interface OnResponse<T> {
        void onResponse(List<T> rowsBeans);

        void onResponseMore(List<T> rowsBeans);

        void onFailed();
    }

    public void setOnResponse(OnResponse onResponse) {
        this.onResponse = onResponse;
    }

    /**
     * descirption: 通知更新接口
     */
    private CreateResponse createResponse;

    public void setCreateResponse(CreateResponse createResponse) {
        this.createResponse = createResponse;
    }

    public interface CreateResponse {
        void onCreate();
    }

    /**
     * descirption: 收到信息通知回调
     */
    private GetResponseListener getResponseListener;

    public void setGetResponseListener(GetResponseListener getResponseListener) {
        this.getResponseListener = getResponseListener;
    }

    public interface GetResponseListener<T> {
        void onRespnse(T t);

        void onFail(String err);
    }

    /**
     * descirption: 获取登录者的信息
     */
    public void getToken(String name, String password, Context context) {
        ApiUtils.getTokenService().getToken(name, password).enqueue(new Callback<GetToken>() {
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
                        ToastHelper.showToast("登录成功");
                        context.startActivity(MainActivity.getMainActivityIntent(context));
                        ((LoginActivity) context).finish();
                    } else {
                        ToastHelper.showToast(getToken.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetToken> call, Throwable e) {
                if (e instanceof HttpException) {
                    ResponseBody body = ((HttpException) e).response().errorBody();
                    try {
                        JSONObject jsonObject = new JSONObject(body.string());
                        String error = jsonObject.getString("Message");
                        ToastHelper.showToast(error);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * descirption: 获取消费者列表
     */
    public void getListByPage(int pageIndex, int pageSize) {
        String token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
        ApiUtils.getListByPageService().getListByPage(token, pageIndex, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetListByPage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetListByPage getListByPage) {
                        if (getListByPage != null && getListByPage.getStatusCode() == 200) {
                            rowsBeans = getListByPage.getContent().getRows();
                            if (onResponse != null) {
                                if (pageIndex > 1) {
                                    onResponse.onResponseMore(rowsBeans);
                                } else {
                                    onResponse.onResponse(rowsBeans);
                                }
                            }
                        } else {
                            ToastHelper.showToast(getListByPage.getMessage());
                            if (onResponse != null) {
                                onResponse.onFailed();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                                if (onResponse != null) {
                                    onResponse.onFailed();
                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 创建人员
     */
    public void postCreate(PostRequestBody postRequestBody, String token, String modiantoken) {
        ApiUtils.postCreateService().createTask(postRequestBody, token, modiantoken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PostResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PostResponse postResponse) {
                        if (postResponse != null && postResponse.getStatusCode() == 200) {
                            ToastHelper.showToast("创建成功");
                            if (createResponse != null) {
                                createResponse.onCreate();
                            }
                        } else {
                            ToastHelper.showToast(postResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 更新人员
     */
    public void postUpdate(PostRequestBody postRequestBody, String token, String modiantoken) {
        ApiUtils.postUpdateService().createTask(postRequestBody, token, modiantoken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PostResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PostResponse postResponse) {
                        if (postResponse != null && postResponse.getStatusCode() == 200) {
                            ToastHelper.showToast("更新成功");
                        } else {
                            ToastHelper.showToast(postResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 删除人员
     */
    public void postDelete(PostRequestBody postRequestBody, String token, String modiantoken) {
        Observable<PostResponse> observable = ApiUtils.postDeleteService().createTask(postRequestBody, token, modiantoken);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PostResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PostResponse postResponse) {
                        if (postResponse != null && postResponse.getStatusCode() == 200) {
                            ToastHelper.showToast("删除成功");
                        } else {
                            ToastHelper.showToast(postResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 读取卡信息
     */
    public void getReadCard(Integer companyCode, Integer diviceID, Integer number, String token, String modiantoken) {
        Observable<GetReadCard> readCardObservable = ApiUtils.getReadCardService().GetReadCard(companyCode, diviceID, number, token, modiantoken);
        readCardObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetReadCard>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetReadCard getReadCard) {
                        if (getReadCard != null && getReadCard.getStatusCode() == 200) {
                            ToastHelper.showToast("查询成功");
                            if (getResponseListener != null) {
                                getResponseListener.onRespnse(getReadCard);
                            }
                        } else {
                            ToastHelper.showToast(getReadCard.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 简单刷卡支付
     */
    public void postSimpleExpense(PostSimpleExpenseBody body, String token) {
        ApiUtils.postSimpleExpenseService().simpleExpense(body, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SimpleExpense>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SimpleExpense simpleExpense) {
                        if (simpleExpense != null && simpleExpense.getStatusCode() == 200) {
                            ToastHelper.showToast("支付成功");
                            if (getResponseListener != null) {
                                getResponseListener.onRespnse(simpleExpense);
                            }
                        } else {
                            ToastHelper.showToast(simpleExpense.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                                if (getResponseListener != null) {
                                    getResponseListener.onFail(error);
                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 二维码支付
     */
    public void postQRCodeExpense(PostQRCodeExpenseBody body, String token, String modiantoken) {
        ApiUtils.postQRCodeExpenseService().QRCodeExpense(body, token, modiantoken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QRCodeExpense>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QRCodeExpense qrCodeExpense) {
                        if (qrCodeExpense != null && qrCodeExpense.getStatusCode() == 200) {
                            ToastHelper.showToast("消费成功");
                            if (getResponseListener != null) {
                                getResponseListener.onRespnse(qrCodeExpense);
                            }
                        } else {
                            ToastHelper.showToast(qrCodeExpense.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                                if (getResponseListener != null) {
                                    getResponseListener.onFail(error);
                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 人脸支付
     */
    public void postFaceExpense(PostFaceExpenseBody body, String token, String modiantoken) {
        ApiUtils.postFaceExpenseService().faceExpense(body, token, modiantoken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FaceExpense>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FaceExpense expense) {
                        if (expense != null && expense.getStatusCode() == 200) {
                            ToastHelper.showToast("支付成功");
                            if (getResponseListener != null) {
                                getResponseListener.onRespnse(expense);
                            }
                        } else {
                            ToastHelper.showToast(expense.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                                if (getResponseListener != null) {
                                    getResponseListener.onFail(error);
                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 定额支付
     */
    public void postDefiniteExpense(PostDefiniteExpenseBody body, String token, String modiantoken) {
        ApiUtils.postDefiniteExpenseService().definiteExpense(body, token, modiantoken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DefiniteExpense>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DefiniteExpense expense) {
                        if (expense != null && expense.getStatusCode() == 200) {
                            ToastHelper.showToast("消费成功");
                            if (getResponseListener != null) {
                                getResponseListener.onRespnse(expense);
                            }
                        } else {
                            ToastHelper.showToast(expense.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                                if (getResponseListener != null) {
                                    getResponseListener.onFail(error);
                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 按userID查询消费者
     */
    public void getUserByuserID(String userID, String token) {
        ApiUtils.getUserByUserIdService().GetByUserID(userID, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetUserByUserID>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetUserByUserID user) {
                        if (user != null && user.getStatusCode() == 200) {
                            ToastHelper.showToast("查询成功");
                            if (getResponseListener != null) {
                                getResponseListener.onRespnse(user);
                            }
                        } else {
                            ToastHelper.showToast(user.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                                if (getResponseListener != null) {
                                    getResponseListener.onFail(error);
                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 开户
     */
    public void postRegister(PostRegister body, String token) {
        ApiUtils.postRegisterService().register(body, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PostResponseNoContent>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PostResponseNoContent postResponseNoContent) {
                        if (postResponseNoContent != null && postResponseNoContent.getStatusCode() == 200) {
                            ToastHelper.showToast("开户成功");
                            if (getResponseListener != null) {
                                getResponseListener.onRespnse(postResponseNoContent);
                            }
                        } else {
                            ToastHelper.showToast(postResponseNoContent.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 销户
     */
    public void postDeRegister(PostDeregister body, String token) {
        ApiUtils.postDeRegisterService().deregister(body, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PostResponseNoContent>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PostResponseNoContent postResponseNoContent) {
                        if (postResponseNoContent != null && postResponseNoContent.getStatusCode() == 200) {
                            ToastHelper.showToast("销户成功");
                        } else {
                            ToastHelper.showToast(postResponseNoContent.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 获取下一个卡内码
     */
    public void getNextCardNumber(String token) {
        ApiUtils.getNextNumberService().getNextNumberService(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReisterResponse postResponse) {
                        if (postResponse != null && postResponse.getStatusCode() == 200) {
                            ToastHelper.showToast("获取下一个卡内码成功");
                            if (getResponseListener != null) {
                                getResponseListener.onRespnse(postResponse);
                            }
                        } else {
                            ToastHelper.showToast(postResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 获取部门列表
     */
    public void getDepartmentList(String token) {
        ApiUtils.getDepartmentListService().getDepartmentList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetDepartmentList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetDepartmentList departmentList) {
                        if (departmentList != null && departmentList.getStatusCode() == 200) {
                            ToastHelper.showToast("获取部门列表成功");
                            if (getResponseListener != null) {
                                getResponseListener.onRespnse(departmentList);
                            }
                        } else {
                            ToastHelper.showToast(departmentList.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 获取卡片类型列表
     */
    public void getCardTypeList(String token) {
        ApiUtils.getCardTypeListService().getCardTypeListService(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetCardTypeList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetCardTypeList cardTypeList) {
                        if (cardTypeList != null && cardTypeList.getStatusCode() == 200) {
                            ToastHelper.showToast("获取卡片类型列表成功");
                            if (getResponseListener != null) {
                                getResponseListener.onRespnse(cardTypeList);
                            }
                        } else {
                            ToastHelper.showToast(cardTypeList.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 获取补贴等级列表
     */
    public void getSubsidyLevel(String token) {
        ApiUtils.getSubsidyLevelService().getSubsidyLevelService(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetSubsidyLevel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetSubsidyLevel getSubsidyLevel) {
                        if (getSubsidyLevel != null && getSubsidyLevel.getStatusCode() == 200) {
                            ToastHelper.showToast("获取补贴等级成功");
                            if (getResponseListener != null) {
                                getResponseListener.onRespnse(getSubsidyLevel);
                            }
                        } else {
                            ToastHelper.showToast(getSubsidyLevel.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 按卡号获取用户
     */
    public void getUser(String token, Integer number) {
        ApiUtils.getUserService().getUserService(token, number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetUser user) {
                        if (user != null && user.getStatusCode() == 200) {
                            ToastHelper.showToast("获取用户信息成功");
                            if (getResponseListener != null) {
                                getResponseListener.onRespnse(user);
                            }
                        } else {
                            ToastHelper.showToast(user.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 充值渠道
     */
    public void getChannel(String token) {
        ApiUtils.getChannel().getChannle(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetChannel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetChannel channel) {
                        if (channel != null && channel.getStatusCode() == 200) {
                            ToastHelper.showToast("获取充值渠道成功");
                            if (getResponseListener != null) {
                                getResponseListener.onRespnse(channel);
                            }
                        } else {
                            ToastHelper.showToast(channel.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 充值
     */
    public void postDeposit(String token, PostDepositBody postDepositBody) {
        ApiUtils.postDeposit().deposit(postDepositBody, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PostResponseNoContent>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PostResponseNoContent postResponseNoContent) {
                        if (postResponseNoContent != null && postResponseNoContent.getStatusCode() == 200) {
                            ToastHelper.showToast("充值成功");
                        } else {
                            ToastHelper.showToast(postResponseNoContent.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 获取充值报表
     */
    public void getDepositPage(String token, int pageIndex, int pagesize) {
        ApiUtils.getDepositPage().getDepositPage(pageIndex, pagesize, "TradeDateTime", "desc",  token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetDepositPage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetDepositPage getDepositPage) {
                        if (getDepositPage != null && getDepositPage.getStatusCode() == 200) {
                            ToastHelper.showToast("查询充值记录成功");
                            if (onResponse != null) {
                                if (pageIndex > 1) {
                                    onResponse.onResponseMore(getDepositPage.getContent().getRows());
                                } else {
                                    onResponse.onResponse(getDepositPage.getContent().getRows());
                                }
                            }
                        } else {
                            ToastHelper.showToast(getDepositPage.getMessage());
                            if (onResponse != null) {
                                onResponse.onFailed();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                                if (onResponse != null) {
                                    onResponse.onFailed();
                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * descirption: 获取消费记录报表
     */
    public void getExpensePage(String token, int pageIndex, int pagesize) {
        ApiUtils.getExpensePage().getExpensePage(pageIndex, pagesize, "TradeDateTime", "desc", token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetExpensePage>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetExpensePage getExpensePage) {
                        if (getExpensePage != null && getExpensePage.getStatusCode() == 200) {
                            ToastHelper.showToast("查询消费记录成功");
                            if (onResponse != null) {
                                if (pageIndex > 1) {
                                    onResponse.onResponseMore(getExpensePage.getContent().getRows());
                                } else {
                                    onResponse.onResponse(getExpensePage.getContent().getRows());
                                }
                            }
                        } else {
                            ToastHelper.showToast(getExpensePage.getMessage());
                            if (onResponse != null) {
                                onResponse.onFailed();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                JSONObject jsonObject = new JSONObject(body.string());
                                String error = jsonObject.getString("Message");
                                ToastHelper.showToast(error);
                                if (onResponse != null) {
                                    onResponse.onFailed();
                                }
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
