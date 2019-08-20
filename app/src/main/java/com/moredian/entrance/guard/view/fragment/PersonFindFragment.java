package com.moredian.entrance.guard.view.fragment;

import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.entity.GetListByPage;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.view.activity.PersonDetailActivity;
import com.moredian.entrance.guard.view.adapter.PersonFindRvAdapter;
import com.moredian.entrance.guard.view.adapter.PersonManageRvAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/20 09:27
 */
public class PersonFindFragment extends BaseFragment {

    @BindView(R.id.person_manage_recyclerview)
    RecyclerView personManageRecyclerview;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.loading_tv)
    TextView loadingTv;
    @BindView(R.id.loading_ll)
    RelativeLayout loadingLl;
    private PersonFindRvAdapter adapter;
    List<GetListByPage.ContentBean.RowsBean> arowsBeans = new ArrayList<>();

    @Override
    public int initView() {
        return R.layout.fragment_person_find;
    }

    @Override
    public void initViewController() {
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.icon));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {

    }

    /**
     * descirption: 刷新一次
     */
    private void refresh() {
        loadingLl.setVisibility(View.VISIBLE);
    }

    /**
     * descirption: 初始化列表数据
     */
    private void initRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        personManageRecyclerview.setLayoutManager(linearLayoutManager);
        personManageRecyclerview.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        adapter = new PersonFindRvAdapter(mContext, arowsBeans);
        personManageRecyclerview.setAdapter(adapter);
        adapter.setMyItemClickListener(new PersonFindRvAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(PersonDetailActivity.getPersonDetailActivityIntent(mContext, position));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
