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
import com.moredian.entrance.guard.entity.GetCardTypeList;
import com.moredian.entrance.guard.entity.GetDepartmentList;
import com.moredian.entrance.guard.entity.GetSubsidyLevel;
import com.moredian.entrance.guard.entity.ReisterResponse;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.view.adapter.SpinnerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/20 09:27
 */
public class OrignalFragment extends BaseFragment {

    private static final String TAG = "OrignalFragment";
    @BindView(R.id.fpa_serialNo_et)
    TextInputEditText fpaSerialNoEt;
    @BindView(R.id.fpa_name_et)
    TextInputEditText fpaNameEt;
    @BindView(R.id.spinner_department)
    Spinner spinnerDepartment;
    @BindView(R.id.spinner_cardtype)
    Spinner spinnerCardtype;
    @BindView(R.id.spinner_subsidyLevel)
    Spinner spinnerSubsidyLevel;
    private List<String> dlist;
    private List<String> typelist;
    private List<String> subsidylist;
    private HashMap<String, String> dmap = new HashMap();
    private HashMap<String, Integer> typemap = new HashMap();
    private HashMap<String, Integer> subsidylistmap = new HashMap();
    private SpinnerAdapter cardtypeadapter;
    private SpinnerAdapter subsidydapter;


    @Override
    public int initView() {
        return R.layout.fragment_orignal;
    }

    @Override
    public void initViewController() {

    }

    @Override
    public void initData() {
        String token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
        api.getDepartmentList(token);
        api.getCardTypeList(token);
        api.getSubsidyLevel(token);
        api.setGetResponseListener(new Api.GetResponseListener() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof GetDepartmentList) {
                    int dlength = ((GetDepartmentList) o).getContent().size();
                    if (dlist == null) {
                        dlist = new ArrayList<>();
                    } else {
                        dlist.clear();
                    }
                    for (int i = 0; i < dlength; i++) {
                        dlist.add(((GetDepartmentList) o).getContent().get(i).getName());
                        dmap.put(((GetDepartmentList) o).getContent().get(i).getName(), ((GetDepartmentList) o).getContent().get(i).getID());
                    }
                    String[] darray = dlist.toArray(new String[dlist.size()]);
                    spinnerDepartment.setAdapter(new SpinnerAdapter(mContext, darray));
                } else if (o instanceof GetCardTypeList) {
                    int typelength = ((GetCardTypeList) o).getContent().size();
                    if (typelist == null) {
                        typelist = new ArrayList<>();
                    } else {
                        typelist.clear();
                    }
                    for (int i = 0; i < typelength; i++) {
                        typelist.add(((GetCardTypeList) o).getContent().get(i).getName());
                        typemap.put(((GetCardTypeList) o).getContent().get(i).getName(), ((GetCardTypeList) o).getContent().get(i).getID());
                    }
                    String[] typearray = typelist.toArray(new String[typelist.size()]);
                    cardtypeadapter = new SpinnerAdapter(mContext, typearray);
                    spinnerCardtype.setAdapter(cardtypeadapter);
                } else if (o instanceof GetSubsidyLevel) {
                    int subsidylength = ((GetSubsidyLevel) o).getContent().size();
                    if (subsidylist == null) {
                        subsidylist = new ArrayList<>();
                    } else {
                        subsidylist.clear();
                    }
                    for (int i = 0; i < subsidylength; i++) {
                        subsidylist.add(((GetSubsidyLevel) o).getContent().get(i).getName());
                        subsidylistmap.put(((GetSubsidyLevel) o).getContent().get(i).getName(), ((GetSubsidyLevel) o).getContent().get(i).getLeve());
                    }
                    String[] subsidyarray = subsidylist.toArray(new String[subsidylist.size()]);
                    subsidydapter = new SpinnerAdapter(mContext, subsidyarray);
                    spinnerSubsidyLevel.setAdapter(subsidydapter);
                }
            }

            @Override
            public void onFail(String err) {

            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        SPUtils.getInstance().put(Constants.ARGUEMENT_SERIALNO, fpaSerialNoEt.getText().toString());
        SPUtils.getInstance().put(Constants.ARGUEMENT_NAME, fpaNameEt.getText().toString());
        SPUtils.getInstance().put(Constants.ARGUEMENT_DEPARTMENT, (String) spinnerDepartment.getSelectedItem());
        SPUtils.getInstance().put(Constants.ARGUEMENT_DEPARTMENT_ID, dmap.get((String) spinnerDepartment.getSelectedItem()));
        SPUtils.getInstance().put(Constants.ARGUEMENT_CARDTYPE, typemap.get((String) spinnerCardtype.getSelectedItem()));
        SPUtils.getInstance().put(Constants.ARGUEMENT_SUBSIDY, subsidylistmap.get((String) spinnerSubsidyLevel.getSelectedItem()));
    }

}