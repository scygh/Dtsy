package com.moredian.entrance.guard.view.fragment;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.blankj.utilcode.util.SPUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetCardTypeList;
import com.moredian.entrance.guard.entity.GetSubsidyLevel;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.view.adapter.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/20 09:27
 */
public class FirstFragment extends BaseFragment {

    @BindView(R.id.spinner_cardtype)
    Spinner spinnerCardtype;
    @BindView(R.id.spinner_subsidyLevel)
    Spinner spinnerSubsidyLevel;
    @BindView(R.id.fpa_cost_et)
    TextInputEditText fpaCostEt;
    @BindView(R.id.fpa_cash_et)
    TextInputEditText fpaCashEt;
    @BindView(R.id.fpa_donate_et)
    TextInputEditText fpaDonateEt;
    private List<String> typelist;
    private List<String> subsidylist;


    @Override
    public int initView() {
        return R.layout.fragment_first;
    }

    @Override
    public void initViewController() {

    }

    @Override
    public void initData() {
        String token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
        api.getCardTypeList(token);
        api.getSubsidyLevel(token);
        api.setGetResponseListener(new Api.GetResponseListener() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof GetCardTypeList) {
                    int typelength = ((GetCardTypeList) o).getContent().size();
                    if (typelist == null) {
                        typelist = new ArrayList<>();
                    } else {
                        typelist.clear();
                    }
                    for (int i = 0; i < typelength; i++) {
                        typelist.add(((GetCardTypeList) o).getContent().get(i).getName());
                    }
                    String[] typearray = typelist.toArray(new String[typelist.size()]);
                    spinnerCardtype.setAdapter(new SpinnerAdapter(mContext,typearray));
                } else if (o instanceof GetSubsidyLevel) {
                    int subsidylength = ((GetSubsidyLevel) o).getContent().size();
                    if (subsidylist == null) {
                        subsidylist = new ArrayList<>();
                    } else {
                        subsidylist.clear();
                    }
                    for (int i = 0; i < subsidylength; i++) {
                        subsidylist.add(((GetSubsidyLevel) o).getContent().get(i).getName());
                    }
                    String[] subsidyarray = subsidylist.toArray(new String[subsidylist.size()]);
                    spinnerSubsidyLevel.setAdapter(new SpinnerAdapter(mContext,subsidyarray));
                }
            }

            @Override
            public void onFail(String err) {

            }
        });
    }

}
