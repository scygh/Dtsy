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

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.view.adapter.NetSettingRvAdapter;
import com.moredian.entrance.guard.view.adapter.PersonManageRvAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonsManageActivity extends AppCompatActivity {

    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.person_manage_recyclerview)
    RecyclerView personManageRecyclerview;
    private PersonManageRvAdapter adapter;

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
        List<String> persons = new ArrayList<>();
        persons.add("张三");
        persons.add("李四");
        persons.add("王五");
        persons.add("赵六");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        personManageRecyclerview.setLayoutManager(linearLayoutManager);
        personManageRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new PersonManageRvAdapter(PersonsManageActivity.this,persons);
        personManageRecyclerview.setAdapter(adapter);
        adapter.setMyItemClickListener(new PersonManageRvAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(PersonDetailActivity.getPersonDetailActivityIntent(PersonsManageActivity.this));
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
}
