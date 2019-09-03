package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.entity.GetExpensePage;
import com.moredian.entrance.guard.entity.GetListByPage;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.view.adapter.ConsumeRecordRvAdapter;
import com.moredian.entrance.guard.view.adapter.PersonManageRvAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsumeRecordActivity extends BaseActivity {

    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.loading_ll)
    RelativeLayout loadingLl;
    @BindView(R.id.record_rv)
    RecyclerView recordRv;
    private List<GetExpensePage.ContentBean.RowsBean> rowsBeans = new ArrayList<>();
    private int pageIndex = 1;
    private LinearLayoutManager linearLayoutManager;
    private ConsumeRecordRvAdapter adapter;
    boolean isLoading = false;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getData();
        }
    };

    /**
     * descirption: 获取当前activity 的Intent对象
     */
    public static Intent getConsumeRecordActivityIntent(Context context) {
        Intent intent = new Intent(context, ConsumeRecordActivity.class);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.record_list_layout;
    }

    @Override
    public void initView() {
        pageName.setText("消费报表");
    }

    @Override
    public void initData() {
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.icon));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                swipeRefresh.setRefreshing(false);
            }
        });
        refresh();
        api.setOnResponse(new Api.OnResponse<GetExpensePage.ContentBean.RowsBean>() {
            @Override
            public void onResponse(List<GetExpensePage.ContentBean.RowsBean> r) {
                rowsBeans.clear();
                rowsBeans.addAll(r);
                updateList();
                if (loadingLl != null) {
                    loadingLl.setVisibility(View.GONE);
                }
            }

            @Override
            public void onResponseMore(List<GetExpensePage.ContentBean.RowsBean> r) {
                if (r.size() > 0) {
                    ToastUtils.showShort("还有这么一些");
                    rowsBeans.addAll(r);
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
    }

    /**
     * descirption: 刷新一次
     */
    private void refresh() {
        api.getExpensePage(token, 1, 10);
        if (loadingLl != null) {
            loadingLl.setVisibility(View.VISIBLE);
        }
        pageIndex = 1;
    }


    private void updateList() {
        if (adapter == null) {
            initRecyclerview();
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * descirption: 上拉加载更多
     */
    private void getData() {
        api.getExpensePage(token, ++pageIndex, 10);
    }

    /**
     * descirption: 初始化列表数据
     */
    private void initRecyclerview() {
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        if (linearLayoutManager != null) {
            recordRv.setLayoutManager(linearLayoutManager);
            adapter = new ConsumeRecordRvAdapter(this, rowsBeans);
            recordRv.setAdapter(adapter);
            recordRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
            adapter.setMyItemClickListener(new ConsumeRecordRvAdapter.OnMyItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    GetExpensePage.ContentBean.RowsBean rowsBean = rowsBeans.get(position);
                    startActivity(RecordDetailActivity.getRecordDetailActivityIntent(ConsumeRecordActivity.this,rowsBean));
                }
            });
        }
    }

    @OnClick({R.id.Manualconsumption_back})
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
        rowsBeans.clear();
        isLoading = false;
        adapter = null;
        pageIndex = 1;
    }
}
