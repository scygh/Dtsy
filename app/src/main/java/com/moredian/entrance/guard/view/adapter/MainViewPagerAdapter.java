package com.moredian.entrance.guard.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.moredian.entrance.guard.view.fragment.BaseFragment;

import java.util.List;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/20 10:06
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragments;

    public MainViewPagerAdapter(FragmentManager fm, List<BaseFragment> cmFragments) {
        super(fm);
        mFragments = cmFragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
