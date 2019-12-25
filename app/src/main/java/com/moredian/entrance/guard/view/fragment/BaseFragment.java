package com.moredian.entrance.guard.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moredian.entrance.guard.http.Api;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/20 09:44
 */
public abstract class BaseFragment extends Fragment {
    public Activity mContext;
    public View mRootView;
    public Unbinder mUnbinder;
    public Api api;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        api = new Api();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(initView(), container, false);
        }
        //避免重复加载UI
        ViewGroup parent = (ViewGroup)mRootView.getParent();
        if (parent!=null) {
            parent.removeView(mRootView);
        }
        mUnbinder = ButterKnife.bind(this,mRootView);
        initViewController();
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public abstract int initView();

    public abstract void initViewController();

    public abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
