package com.moredian.entrance.guard.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.entity.GetListByPage;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.view.activity.PersonDetailActivity;
import com.moredian.entrance.guard.view.activity.PersonsManageActivity;
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
public class PersonListFragment extends BaseFragment {

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
    private PersonManageRvAdapter adapter;
    List<GetListByPage.ContentBean.RowsBean> arowsBeans = new ArrayList<>();
    boolean isLoading = false;
    private Handler handler = new Handler();
    private int pageIndex = 1;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getData();
        }
    };

    @Override
    public int initView() {
        return R.layout.fragment_person_list;
    }

    @Override
    public void initViewController() {
        refresh();
        api.setOnResponse(new Api.OnResponse() {
            @Override
            public void onResponse(List<GetListByPage.ContentBean.RowsBean> rowsBeans) {
                arowsBeans.clear();
                arowsBeans.addAll(rowsBeans);
                if (arowsBeans.size() > 0) {
                    if (loadingLl != null) {
                        loadingLl.setVisibility(View.GONE);
                    }
                    if (adapter == null) {
                        initRecyclerview();
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    if (loadingLl != null) {
                        loadingLl.setVisibility(View.GONE);
                    }
                    ToastUtils.showShort("列表为空");
                }
            }

            @Override
            public void onResponseMore(List<GetListByPage.ContentBean.RowsBean> rowsBeans) {
                if (rowsBeans.size() > 0) {
                    ToastUtils.showShort("还有这么一些");
                    arowsBeans.addAll(rowsBeans);
                    adapter.notifyDataSetChanged();
                    adapter.notifyItemRemoved(adapter.getItemCount());
                    isLoading = false;
                } else {
                    ToastUtils.showShort("只有这么多啦！");
                    isLoading = false;
                    adapter.notifyItemRemoved(adapter.getItemCount());
                }
            }

            @Override
            public void onFailed() {
                if (loadingLl != null) {
                    loadingLl.setVisibility(View.GONE);
                }
            }
        });
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
        api.getListByPage(1, 20);
        if (loadingLl != null) {
            loadingLl.setVisibility(View.VISIBLE);
        }
        pageIndex = 1;
    }

    /**
     * descirption: 下拉加载更多
     */
    private void getData() {
        api.getListByPage(++pageIndex, 20);
    }

    /**
     * descirption: 初始化列表数据
     */
    private void initRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        personManageRecyclerview.setLayoutManager(linearLayoutManager);
        personManageRecyclerview.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        adapter = new PersonManageRvAdapter(mContext, arowsBeans);
        personManageRecyclerview.setAdapter(adapter);
        personManageRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    if (isLoading) {
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        handler.postDelayed(runnable, 1000);
                    }
                }
            }
        });
        adapter.setMyItemClickListener(new PersonManageRvAdapter.OnMyItemClickListener() {
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
        pageIndex = 1;
        handler.removeCallbacks(runnable);
    }
}
