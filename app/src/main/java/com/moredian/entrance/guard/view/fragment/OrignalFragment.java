package com.moredian.entrance.guard.view.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.SPUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.PostRegister;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/20 09:27
 */
public class OrignalFragment extends BaseFragment {

    private static final String TAG = "OrignalFragment";

    @BindView(R.id.fpa_name_et)
    TextInputEditText fpaNameEt;
    @BindView(R.id.fpa_empid_et)
    TextInputEditText fpaEmpidEt;
    @BindView(R.id.fpa_idcard_et)
    TextInputEditText fpaIdcardEt;
    @BindView(R.id.fpa_phone_et)
    TextInputEditText fpaPhoneEt;
    @BindView(R.id.fpa_address_et)
    TextInputEditText fpaAddressEt;

    @Override
    public int initView() {
        return R.layout.fragment_orignal;
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
        SPUtils.getInstance().put(Constants.ARGUEMENT_NAME, fpaNameEt.getText().toString());
        SPUtils.getInstance().put(Constants.ARGUEMENT_EMPID, fpaEmpidEt.getText().toString());
        SPUtils.getInstance().put(Constants.ARGUEMENT_IDCARD, fpaIdcardEt.getText().toString());
        SPUtils.getInstance().put(Constants.ARGUEMENT_PHONE, fpaPhoneEt.getText().toString());
        SPUtils.getInstance().put(Constants.ARGUEMENT_ADDRESS, fpaAddressEt.getText().toString());
    }


}