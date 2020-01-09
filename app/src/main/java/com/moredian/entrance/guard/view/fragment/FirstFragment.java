package com.moredian.entrance.guard.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SPUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetCardTypeList;
import com.moredian.entrance.guard.entity.GetSubsidyLevel;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.view.adapter.SpinnerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/20 09:27
 */
public class FirstFragment extends BaseFragment {


    @BindView(R.id.fpa_cost_et)
    TextInputEditText fpaCostEt;
    @BindView(R.id.fpa_cash_et)
    TextInputEditText fpaCashEt;
    @BindView(R.id.fpa_donate_et)
    TextInputEditText fpaDonateEt;
    @BindView(R.id.fpa_paypassword_et)
    TextInputEditText fpaPaypasswordEt;

    @Override
    public int initView() {
        return R.layout.fragment_first;
    }

    @Override
    public void initViewController() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onStop() {
        super.onStop();
        SPUtils.getInstance().put(Constants.ARGUEMENT_CONST, fpaCostEt.getText().toString());
        SPUtils.getInstance().put(Constants.ARGUEMENT_CASH, fpaCashEt.getText().toString());
        SPUtils.getInstance().put(Constants.ARGUEMENT_DONATE, fpaDonateEt.getText().toString());
        SPUtils.getInstance().put(Constants.ARGUEMENT_PAYPASSWORD, fpaPaypasswordEt.getText().toString());
    }
}
