package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetListByPage;
import com.moredian.entrance.guard.entity.GetToken;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.http.ApiUtils;
import com.moredian.entrance.guard.view.adapter.NetSettingRvAdapter;
import com.moredian.entrance.guard.view.adapter.PersonManageRvAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonsManageActivity extends AppCompatActivity {

    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.person_manage_recyclerview)
    RecyclerView personManageRecyclerview;
    private PersonManageRvAdapter adapter;
    private Api api;
    List<GetListByPage.ContentBean.RowsBean> arowsBeans;

    public static Intent getPersonsManageActivityIntent(Context context) {
        Intent intent = new Intent(context, PersonsManageActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_manage);
        ButterKnife.bind(this);
        pageName.setText("人员管理");
        api = new Api();
        api.getListByPage();
        api.setOnResponse(new Api.OnResponse() {
            @Override
            public void onResponse(List<GetListByPage.ContentBean.RowsBean> rowsBeans) {
                arowsBeans = rowsBeans;
                if (arowsBeans != null) {
                    initRecyclerview();
                } else {
                    ToastUtils.showShort("列表为空");
                }
            }
        });
    }

    private void initRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        personManageRecyclerview.setLayoutManager(linearLayoutManager);
        personManageRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new PersonManageRvAdapter(PersonsManageActivity.this, arowsBeans);
        personManageRecyclerview.setAdapter(adapter);
        adapter.setMyItemClickListener(new PersonManageRvAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(PersonDetailActivity.getPersonDetailActivityIntent(PersonsManageActivity.this, arowsBeans.get(position)));
            }
        });
    }

    @OnClick(R.id.Manualconsumption_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
