package com.moredian.entrance.guard.view.fragment;

import android.support.design.widget.TextInputEditText;
import android.widget.Spinner;

import com.blankj.utilcode.util.SPUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetDepartmentList;
import com.moredian.entrance.guard.entity.ReisterResponse;
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
        api.getDepartmentList(token);
        api.getNextCardNumber(token);
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
                    spinnerDepartment.setAdapter(new SpinnerAdapter(mContext, darray));
                }
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
        SPUtils.getInstance().put(Constants.ARGUEMENT_SERIALNO, fpaSerialNoEt.getText().toString());
        SPUtils.getInstance().put(Constants.ARGUEMENT_PAYPASSWORD, fpaPaypasswordEt.getText().toString());
        SPUtils.getInstance().put(Constants.ARGUEMENT_DEADLINE, (String) spinnerDeadtime.getSelectedItem());
        SPUtils.getInstance().put(Constants.ARGUEMENT_DEPARTMENT, (String) spinnerDepartment.getSelectedItem());
        SPUtils.getInstance().put(Constants.ARGUEMENT_NUMBER, number);
    }
}
