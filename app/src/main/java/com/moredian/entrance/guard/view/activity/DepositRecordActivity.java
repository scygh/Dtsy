package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetDepositPage;
import com.moredian.entrance.guard.entity.GetExpensePage;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.utils.ToastHelper;
import com.moredian.entrance.guard.view.adapter.ConsumeRecordRvAdapter;
import com.moredian.entrance.guard.view.adapter.ExpenseRecordRvAdapter;
import com.moredian.entrance.guard.view.fragment.DatePickerFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DepositRecordActivity extends BaseActivity {

    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.loading_ll)
    RelativeLayout loadingLl;
    @BindView(R.id.record_rv)
    RecyclerView recordRv;
    private List<GetDepositPage.ContentBean.RowsBean> rowsBeans = new ArrayList<>();
    private int pageIndex = 1;
    private LinearLayoutManager linearLayoutManager;
    private ExpenseRecordRvAdapter adapter;
    boolean isLoading = false;
    Handler handler = new Handler();
    private boolean isLimit = false;
    String beginTime;
    String endTime;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getData();
        }
    };

    /**
     * descirption: 获取当前activity 的Intent对象
     */
    public static Intent getDepositRecordActivityIntent(Context context) {
        Intent intent = new Intent(context, DepositRecordActivity.class);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.record_list_layout;
    }

    @Override
    public void initView() {
        pageName.setText("充值报表");
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
        api.setOnResponse(new Api.OnResponse<GetDepositPage.ContentBean.RowsBean>() {
            @Override
            public void onResponse(List<GetDepositPage.ContentBean.RowsBean> r) {
                rowsBeans.clear();
                rowsBeans.addAll(r);
                updateList();
                if (loadingLl != null) {
                    loadingLl.setVisibility(View.GONE);
                }
            }

            @Override
            public void onResponseMore(List<GetDepositPage.ContentBean.RowsBean> r) {
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
        api.getDepositPage(token, 1, 10, new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()), new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        if (loadingLl != null) {
            loadingLl.setVisibility(View.VISIBLE);
        }
        pageIndex = 1;
        isLimit = false;
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
        if (isLimit) {
            api.getDepositPage(token, ++pageIndex, 10, beginTime, endTime);
        } else {
            api.getDepositPage(token, ++pageIndex, 10, new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()), new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        }
    }

    /**
     * descirption: 初始化列表数据
     */
    private void initRecyclerview() {
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        if (linearLayoutManager != null) {
            recordRv.setLayoutManager(linearLayoutManager);
            adapter = new ExpenseRecordRvAdapter(this, rowsBeans);
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
            adapter.setMyItemClickListener(new ExpenseRecordRvAdapter.OnMyItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    GetDepositPage.ContentBean.RowsBean rowsBean = rowsBeans.get(position);
                    startActivity(RecordDetailActivity.getRecordDetailActivityIntent(DepositRecordActivity.this, rowsBean));
                }
            });
        }
    }

    @OnClick({R.id.Manualconsumption_back, R.id.record_fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.record_fab:
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance();
                datePickerFragment.show(getSupportFragmentManager(), "tag_deposit_record");
                datePickerFragment.setOnDialogListener(new DatePickerFragment.OnDialogListener() {
                    @Override
                    public void onDialogClick(Date date) {
                        beginTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
                        endTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
                        api.getDepositPage(token, 1, 10, beginTime, endTime);
                        isLimit = true;
                    }
                });
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
