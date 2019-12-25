package com.moredian.entrance.guard.view.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetReadCard;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/12 10:10
 */
public class ShowCardMessageFragment extends DialogFragment {

    TextView scmNameTv;
    TextView scmCradnumTv;
    TextView scmBalanceTv;
    TextView scmCcountTv;
    TextView scmStatusTv;

    public static ShowCardMessageFragment newINstance(GetReadCard.ContentBean contentBean) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BUNDLE_TAG, contentBean);
        ShowCardMessageFragment fragment = new ShowCardMessageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.showcardmessage_layout, null, false);
        scmNameTv = view.findViewById(R.id.scm_name_tv);
        scmCradnumTv = view.findViewById(R.id.scm_cradnum_tv);
        scmBalanceTv = view.findViewById(R.id.scm_balance_tv);
        scmCcountTv = view.findViewById(R.id.scm_ccount_tv);
        scmStatusTv = view.findViewById(R.id.scm_status_tv);
        Bundle bundle = getArguments();
        GetReadCard.ContentBean contentBean = (GetReadCard.ContentBean) bundle.get(Constants.BUNDLE_TAG);
        scmNameTv.setText(contentBean.getUserName());
        scmCradnumTv.setText(contentBean.getNumber()+"");
        scmBalanceTv.setText(contentBean.getBalance()+"元");
        scmCcountTv.setText(contentBean.getPayCount()+"次");
        int status = contentBean.getState();
        if (status == 0) {
            scmStatusTv.setText("允许消费");
        } else if (status == 1) {
            scmStatusTv.setText("非本单位卡");
        } else if (status == 2) {
            scmStatusTv.setText("超过卡片有效期");
        } else if (status == 2) {
            scmStatusTv.setText("未在就餐时间");
        }
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.card_fragment_title)
                .setView(view)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
