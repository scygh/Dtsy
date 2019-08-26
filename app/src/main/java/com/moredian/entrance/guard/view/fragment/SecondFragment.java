package com.moredian.entrance.guard.view.fragment;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.blankj.utilcode.util.SPUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetCardTypeList;
import com.moredian.entrance.guard.entity.GetDepartmentList;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.view.adapter.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/20 09:27
 */
public class SecondFragment extends BaseFragment {

    @BindView(R.id.fpa_serialNo_et)
    TextInputEditText fpaSerialNoEt;
    @BindView(R.id.fpa_paypassword_et)
    TextInputEditText fpaPaypasswordEt;
    @BindView(R.id.spinner_deadtime)
    Spinner spinnerDeadtime;
    @BindView(R.id.spinner_department)
    Spinner spinnerDepartment;
    private List<String> dlist;

    @Override
    public int initView() {
        return R.layout.fragment_second;
    }

    @Override
    public void initViewController() {
    }

    @Override
    public void initData() {
        spinnerDeadtime.setAdapter(new SpinnerAdapter(mContext,new String[] {"2029-12-31 00:00:00"}));
        String token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
        api.getDepartmentList(token);
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
                    }
                    String[] darray = dlist.toArray(new String[dlist.size()]);
                    spinnerDepartment.setAdapter(new SpinnerAdapter(mContext,darray));
                }
            }

            @Override
            public void onFail(String err) {

            }
        });
    }
}
