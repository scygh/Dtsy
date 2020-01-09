package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.view.adapter.MainViewPagerAdapter;
import com.moredian.entrance.guard.view.fragment.PersonAddFragment;
import com.moredian.entrance.guard.view.fragment.PersonFindFragment;
import com.moredian.entrance.guard.view.fragment.PersonListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonsAddActivity extends BaseActivity {

    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.fragment_container)
    LinearLayout fragmentContainer;

    public static Intent getPersonAddActivityIntent(Context context) {
        Intent intent = new Intent(context, PersonsAddActivity.class);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.activity_persons_add;
    }

    @Override
    public void initView() {
        pageName.setText("添加用户");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,new PersonAddFragment())
                .commit();
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.Manualconsumption_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
        }
    }

}
