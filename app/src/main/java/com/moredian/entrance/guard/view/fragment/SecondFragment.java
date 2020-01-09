package com.moredian.entrance.guard.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.blankj.utilcode.util.SPUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.ReisterResponse;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.view.adapter.SpinnerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/20 09:27
 */
public class SecondFragment extends BaseFragment {

    @BindView(R.id.fpa_empid_et)
    TextInputEditText fpaEmpidEt;
    @BindView(R.id.spinner_deadtime)
    Spinner spinnerDeadtime;
    @BindView(R.id.fpa_idcard_et)
    TextInputEditText fpaIdcardEt;
    @BindView(R.id.fpa_phone_et)
    TextInputEditText fpaPhoneEt;
    @BindView(R.id.fpa_address_et)
    TextInputEditText fpaAddressEt;
    private int number;


    @Override
    public int initView() {
        return R.layout.fragment_second;
    }

    @Override
    public void initViewController() {
    }

    @Override
    public void initData() {
        spinnerDeadtime.setAdapter(new SpinnerAdapter(mContext, new String[]{"2029-12-31 00:00:00"}));
        String token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
        api.getNextCardNumber(token);
        api.setGetResponseListener(new Api.GetResponseListener() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof ReisterResponse) {
                    number = ((ReisterResponse) o).getContent();
                }
            }

            @Override
            public void onFail(String err) {

            }
        });
    }

    public void save() {
        SPUtils.getInstance().put(Constants.ARGUEMENT_NUMBER, number);
        SPUtils.getInstance().put(Constants.ARGUEMENT_DEADLINE, (String) spinnerDeadtime.getSelectedItem());
        SPUtils.getInstance().put(Constants.ARGUEMENT_EMPID, fpaEmpidEt.getText().toString());
        SPUtils.getInstance().put(Constants.ARGUEMENT_IDCARD, fpaIdcardEt.getText().toString());
        SPUtils.getInstance().put(Constants.ARGUEMENT_PHONE, fpaPhoneEt.getText().toString());
        SPUtils.getInstance().put(Constants.ARGUEMENT_ADDRESS, fpaAddressEt.getText().toString());
    }
}
