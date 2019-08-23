package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.view.adapter.MainViewPagerAdapter;
import com.moredian.entrance.guard.view.fragment.BaseFragment;
import com.moredian.entrance.guard.view.fragment.PersonAddFragment;
import com.moredian.entrance.guard.view.fragment.PersonFindFragment;
import com.moredian.entrance.guard.view.fragment.PersonListFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonsManageActivity extends BaseActivity {

    private static final String TAG = "PersonsManageActivity";
    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.invest_viewpager)
    ViewPager investViewpager;
    @BindView(R.id.invest_tablayout)
    TabLayout investTablayout;
    private ArrayList<BaseFragment> mFragments = new ArrayList<>();
    private MainViewPagerAdapter mainViewPagerAdapter;

    public static Intent getPersonsManageActivityIntent(Context context) {
        Intent intent = new Intent(context, PersonsManageActivity.class);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.activity_persons_manage;
    }

    @Override
    public void initView() {
        pageName.setText("人员管理");
        mFragments.clear();
        investTablayout.removeAllTabs();
        String[] arr = getResources().getStringArray(R.array.tab);
        mFragments.add(new PersonListFragment());
        mFragments.add(new PersonAddFragment());
        mFragments.add(new PersonFindFragment());
        for (int i = 0; i < arr.length; i++) {
            investTablayout.addTab(investTablayout.newTab());
        }
        investTablayout.setupWithViewPager(investViewpager, false);
        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), mFragments);
        investViewpager.setAdapter(mainViewPagerAdapter);
        for (int i = 0; i < arr.length; i++) {
            investTablayout.getTabAt(i).setText(arr[i]);
        }
    }

    @Override
    public void initData() {

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
