package com.moredian.entrance.guard.http;

import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.PostDepositBody;
import com.moredian.entrance.guard.http.retrofit.GetCardTypeListService;
import com.moredian.entrance.guard.http.retrofit.GetChannel;
import com.moredian.entrance.guard.http.retrofit.GetDepartmentListService;
import com.moredian.entrance.guard.http.retrofit.GetDepositPage;
import com.moredian.entrance.guard.http.retrofit.GetExpensePage;
import com.moredian.entrance.guard.http.retrofit.GetListByPageService;
import com.moredian.entrance.guard.http.retrofit.GetNextNumberService;
import com.moredian.entrance.guard.http.retrofit.GetReadCardService;
import com.moredian.entrance.guard.http.retrofit.GetSubsidyLevelService;
import com.moredian.entrance.guard.http.retrofit.GetTokenService;
import com.moredian.entrance.guard.http.retrofit.GetUserByUserIdService;
import com.moredian.entrance.guard.http.retrofit.GetUserService;
import com.moredian.entrance.guard.http.retrofit.PostCreate;
import com.moredian.entrance.guard.http.retrofit.PostDeRegister;
import com.moredian.entrance.guard.http.retrofit.PostDefiniteExpense;
import com.moredian.entrance.guard.http.retrofit.PostDelete;
import com.moredian.entrance.guard.http.retrofit.PostDeposit;
import com.moredian.entrance.guard.http.retrofit.PostFaceExpense;
import com.moredian.entrance.guard.http.retrofit.PostQRCodeExpense;
import com.moredian.entrance.guard.http.retrofit.PostRefund;
import com.moredian.entrance.guard.http.retrofit.PostRegister;
import com.moredian.entrance.guard.http.retrofit.PostSimpleExpense;
import com.moredian.entrance.guard.http.retrofit.PostUpdate;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/31 11:29
 */
public class ApiUtils {
    public static final String BASE_URL = Constants.SERVER_URL;

    /**
     * descirption: 账户密码登录换取Token
     */
    public static GetTokenService getTokenService() {
        return RetrofitClient.getClient(BASE_URL).create(GetTokenService.class);
    }

    /**
     * descirption: 分页查询消费者
     */
    public static GetListByPageService getListByPageService() {
        return RetrofitClient.getClient(BASE_URL).create(GetListByPageService.class);
    }

    /**
     * descirption: 按userID查询消费者
     */
    public static GetUserByUserIdService getUserByUserIdService() {
        return RetrofitClient.getClient(BASE_URL).create(GetUserByUserIdService.class);
    }

    /**
     * descirption:创建魔点人员
     */
    public static PostCreate postCreateService() {
        return RetrofitClient.getClient(BASE_URL).create(PostCreate.class);
    }

    /**
     * descirption:删除魔点人员
     */
    public static PostDelete postDeleteService() {
        return RetrofitClient.getClient(BASE_URL).create(PostDelete.class);
    }

    /**
     * descirption:更新魔点人员（上传头像）
     */
    public static PostUpdate postUpdateService() {
        return RetrofitClient.getClient(BASE_URL).create(PostUpdate.class);
    }

    /**
    * descirption: 读取卡信息
    */
    public static GetReadCardService getReadCardService() {
        return RetrofitClient.getClient(BASE_URL).create(GetReadCardService.class);
    }

    /**
     * descirption:简单支付
     */
    public static PostSimpleExpense postSimpleExpenseService() {
        return RetrofitClient.getClient(BASE_URL).create(PostSimpleExpense.class);
    }

    /**
     * descirption:出示二维码支付
     */
    public static PostQRCodeExpense postQRCodeExpenseService() {
        return RetrofitClient.getClient(BASE_URL).create(PostQRCodeExpense.class);
    }

    /**
     * descirption:魔点人脸支付
     */
    public static PostFaceExpense postFaceExpenseService() {
        return RetrofitClient.getClient(BASE_URL).create(PostFaceExpense.class);
    }

    /**
     * descirption:定额支付
     */
    public static PostDefiniteExpense postDefiniteExpenseService() {
        return RetrofitClient.getClient(BASE_URL).create(PostDefiniteExpense.class);
    }

    /**
     * descirption:开户
     */
    public static PostRegister postRegisterService() {
        return RetrofitClient.getClient(BASE_URL).create(PostRegister.class);
    }

    /**
     * descirption:销户
     */
    public static PostDeRegister postDeRegisterService() {
        return RetrofitClient.getClient(BASE_URL).create(PostDeRegister.class);
    }

    /**
     * descirption:获取部门列表
     */
    public static GetDepartmentListService getDepartmentListService() {
        return RetrofitClient.getClient(BASE_URL).create(GetDepartmentListService.class);
    }

    /**
     * descirption:获取卡类型列表
     */
    public static GetCardTypeListService getCardTypeListService() {
        return RetrofitClient.getClient(BASE_URL).create(GetCardTypeListService.class);
    }

    /**
     * descirption:获取下一个卡内码
     */
    public static GetNextNumberService getNextNumberService() {
        return RetrofitClient.getClient(BASE_URL).create(GetNextNumberService.class);
    }

    /**
     * descirption:获取补贴等级
     */
    public static GetSubsidyLevelService getSubsidyLevelService() {
        return RetrofitClient.getClient(BASE_URL).create(GetSubsidyLevelService.class);
    }

    /**
     * descirption:按卡号获取用户
     */
    public static GetUserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(GetUserService.class);
    }

    /**
     * descirption:充值
     */
    public static PostDeposit postDeposit() {
        return RetrofitClient.getClient(BASE_URL).create(PostDeposit.class);
    }

    /**
     * descirption:退款
     */
    public static PostRefund postRefund() {
        return RetrofitClient.getClient(BASE_URL).create(PostRefund.class);
    }

    /**
     * descirption:获取充值渠道列表
     */
    public static GetChannel getChannel() {
        return RetrofitClient.getClient(BASE_URL).create(GetChannel.class);
    }

    /**
     * descirption:获取充值报表
     */
    public static GetDepositPage getDepositPage() {
        return RetrofitClient.getClient(BASE_URL).create(GetDepositPage.class);
    }

    /**
     * descirption:获取消费报表
     */
    public static GetExpensePage getExpensePage() {
        return RetrofitClient.getClient(BASE_URL).create(GetExpensePage.class);
    }
}
