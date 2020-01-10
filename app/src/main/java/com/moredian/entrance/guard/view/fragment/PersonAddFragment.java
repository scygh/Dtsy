package com.moredian.entrance.guard.view.fragment;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.SPUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.PostRegister;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.utils.ToastHelper;
import com.moredian.entrance.guard.view.designview.ComonDialog;
import com.xyz.step.FlowViewHorizontal;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/20 09:27
 */
public class PersonAddFragment extends BaseFragment {

    private static final String TAG = "PersonAddFragment";
    @BindView(R.id.fvh)
    FlowViewHorizontal fvh;
    @BindView(R.id.personadd_container)
    FrameLayout personaddContainer;
    @BindView(R.id.step_btn)
    Button stepBtn;
    @BindView(R.id.step_previous_btn)
    Button stepPreviousBtn;
    private FragmentManager fm;
    private Fragment childfragment;
    private PostRegister postRegister = new PostRegister();

    @Override
    public int initView() {
        return R.layout.fragment_person_add;
    }

    @Override
    public void initViewController() {
        stepBtn.setText("下一步");
        fvh.setProgress(0, 3, getResources().getStringArray(R.array.step), null);
        fm = this.getChildFragmentManager();
        showOrignalFragment();
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.step_btn, R.id.step_previous_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.step_btn:
                if (childfragment instanceof OrignalFragment) {
                    showFirstFragment();
                } else if (childfragment instanceof FirstFragment) {
                    showSecondFragment();
                } else if (childfragment instanceof SecondFragment) {
                    ((SecondFragment) childfragment).save();
                    showDialog();
                }
                break;
            case R.id.step_previous_btn:
                if (childfragment instanceof FirstFragment) {
                    childfragment = new OrignalFragment();
                    fm.beginTransaction()
                            .setCustomAnimations(
                                    R.anim.slide_left_in,
                                    R.anim.slide_right_out,
                                    R.anim.slide_right_in,
                                    R.anim.slide_left_out
                            )
                            .replace(R.id.personadd_container, childfragment).commit();
                    stepBtn.setText("下一步");
                    stepPreviousBtn.setVisibility(View.GONE);
                    fvh.setProgress(0, 3, getResources().getStringArray(R.array.step), null);
                } else if (childfragment instanceof SecondFragment) {
                    childfragment = new FirstFragment();
                    fm.beginTransaction()
                            .setCustomAnimations(
                                    R.anim.slide_left_in,
                                    R.anim.slide_right_out,
                                    R.anim.slide_right_in,
                                    R.anim.slide_left_out
                            )
                            .replace(R.id.personadd_container, childfragment).commit();
                    stepBtn.setText("下一步");
                    fvh.setProgress(1, 3, getResources().getStringArray(R.array.step), null);
                }
                break;
        }
    }

    ComonDialog comonDialog;

    /**
     * descirption: 显示提示
     */
    private void showDialog() {
        if (comonDialog != null) {
            return;
        }
        comonDialog = new ComonDialog(getActivity());
        comonDialog.setMessage(
                "姓名：" + SPUtils.getInstance().getString(Constants.ARGUEMENT_NAME) + "\n" +
                        "卡号：" + SPUtils.getInstance().getString(Constants.ARGUEMENT_SERIALNO) + "\n" +
                        "充值金额：" + SPUtils.getInstance().getString(Constants.ARGUEMENT_CASH)
        );
        comonDialog.setTitle("请确认开户信息")
                .setSingle(true).setOnClickBottomListener(new ComonDialog.OnClickBottomListener() {
            @Override
            public void onPositiveClick() {
                comonDialog.dismiss();
                GotoRegister();
                comonDialog = null;
            }

            @Override
            public void onNegtiveClick() {
                comonDialog.dismiss();
                comonDialog = null;
            }
        }).show();
    }

    /**
     * descirption: 显示第三个界面
     */
    private void showSecondFragment() {
        childfragment = new SecondFragment();
        fm.beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_right_in,
                        R.anim.slide_left_out,
                        R.anim.slide_left_in,
                        R.anim.slide_right_out
                )
                .replace(R.id.personadd_container, childfragment).commit();
        stepBtn.setText("开户");
        stepPreviousBtn.setVisibility(View.VISIBLE);
        fvh.setProgress(2, 3, getResources().getStringArray(R.array.step), null);
    }

    /**
     * descirption: 显示第二个界面
     */
    private void showFirstFragment() {
        childfragment = new FirstFragment();
        fm.beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_right_in,
                        R.anim.slide_left_out,
                        R.anim.slide_left_in,
                        R.anim.slide_right_out
                )
                .replace(R.id.personadd_container, childfragment).commit();
        stepBtn.setText("下一步");
        stepPreviousBtn.setVisibility(View.VISIBLE);
        fvh.setProgress(1, 3, getResources().getStringArray(R.array.step), null);
    }

    /**
     * descirption: 显示第一页
     */
    private void showOrignalFragment() {
        childfragment = fm.findFragmentById(R.id.personadd_container);
        if (childfragment == null) {
            childfragment = new OrignalFragment();
            fm.beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_right_in,
                            R.anim.slide_left_out,
                            R.anim.slide_left_in,
                            R.anim.slide_right_out
                    )
                    .add(R.id.personadd_container, childfragment).commit();
        } else {
            childfragment = new OrignalFragment();
            fm.beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_right_in,
                            R.anim.slide_left_out,
                            R.anim.slide_left_in,
                            R.anim.slide_right_out
                    )
                    .replace(R.id.personadd_container, childfragment).commit();
        }
    }

    /**
     * descirption: 开户去
     */
    private void GotoRegister() {
        // TODO: 2019/8/27 开户去
        String token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
        PostRegister postRegister = new PostRegister();
        postRegister.setName(SPUtils.getInstance().getString(Constants.ARGUEMENT_NAME));
        postRegister.setEmpID(SPUtils.getInstance().getString(Constants.ARGUEMENT_EMPID));
        postRegister.setIDCard(SPUtils.getInstance().getString(Constants.ARGUEMENT_IDCARD));
        postRegister.setPhone(SPUtils.getInstance().getString(Constants.ARGUEMENT_PHONE));
        postRegister.setAddress(SPUtils.getInstance().getString(Constants.ARGUEMENT_ADDRESS));
        postRegister.setCardType(SPUtils.getInstance().getInt(Constants.ARGUEMENT_CARDTYPE));
        postRegister.setSubsidyLevel(SPUtils.getInstance().getInt(Constants.ARGUEMENT_SUBSIDY));
        String cost = SPUtils.getInstance().getString(Constants.ARGUEMENT_CONST);
        if (!TextUtils.isEmpty(cost)) {
            postRegister.setCost(Double.parseDouble(cost));
        } else {
            postRegister.setCost(0.0);
        }
        String cash = SPUtils.getInstance().getString(Constants.ARGUEMENT_CASH);
        if (!TextUtils.isEmpty(cash)) {
            postRegister.setCash(Double.parseDouble(cash));
        } else {
            postRegister.setCash(0.0);
        }
        String donate = SPUtils.getInstance().getString(Constants.ARGUEMENT_DONATE);
        if (!TextUtils.isEmpty(donate)) {
            postRegister.setDonate(Double.parseDouble(donate));
        } else {
            postRegister.setDonate(0.0);
        }
        String serialno = SPUtils.getInstance().getString(Constants.ARGUEMENT_SERIALNO);
        String name = SPUtils.getInstance().getString(Constants.ARGUEMENT_NAME);
        postRegister.setSerialNo(serialno);
        postRegister.setPayKey(SPUtils.getInstance().getString(Constants.ARGUEMENT_PAYPASSWORD));
        postRegister.setDeadline(SPUtils.getInstance().getString(Constants.ARGUEMENT_DEADLINE));
        postRegister.setDepartmentName(SPUtils.getInstance().getString(Constants.ARGUEMENT_DEPARTMENT));
        postRegister.setNumber(SPUtils.getInstance().getInt(Constants.ARGUEMENT_NUMBER));
        postRegister.setCardState(1);
        postRegister.setDepartmentID(SPUtils.getInstance().getString(Constants.ARGUEMENT_DEPARTMENT_ID));
        if (!TextUtils.isEmpty(serialno) && !TextUtils.isEmpty(name)) {
            api.postRegister(postRegister, token);
            api.setGetResponseListener(new Api.GetResponseListener() {
                @Override
                public void onRespnse(Object o) {
                    fvh.setProgress(3, 3, getResources().getStringArray(R.array.step), null);
                }

                @Override
                public void onFail(String err) {

                }
            });
        } else {
            ToastHelper.showToast("卡号或者姓名不能为空！");
        }
    }
}
