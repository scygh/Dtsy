package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.entity.GetListByPage;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.view.adapter.PersonManageRvAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonsManageActivity extends AppCompatActivity {

    private static final String TAG = "PersonsManageActivity";
    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.person_manage_recyclerview)
    RecyclerView personManageRecyclerview;
    @BindView(R.id.loading_ll)
    RelativeLayout loadingLl;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private PersonManageRvAdapter adapter;
    private Api api;
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
        refresh();
        api.setOnResponse(new Api.OnResponse() {
            @Override
            public void onResponse(List<GetListByPage.ContentBean.RowsBean> rowsBeans) {
                arowsBeans.clear();
                arowsBeans.addAll(rowsBeans);
                if (arowsBeans.size() > 0) {
                    loadingLl.setVisibility(View.GONE);
                    if (adapter == null) {
                        initRecyclerview();
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    loadingLl.setVisibility(View.GONE);
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
                loadingLl.setVisibility(View.GONE);
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

    /**
    * descirption: 刷新一次
    */
    private void refresh() {
        api.getListByPage(1, 20);
        loadingLl.setVisibility(View.VISIBLE);
        pageIndex = 1;
    }

    private void getData() {
        api.getListByPage(++pageIndex, 20);
        Log.d(TAG, "getData: " + pageIndex);
    }

    /**
    * descirption: 初始化列表数据
    */
    private void initRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        personManageRecyclerview.setLayoutManager(linearLayoutManager);
        personManageRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new PersonManageRvAdapter(PersonsManageActivity.this, arowsBeans);
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
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pageIndex = 1;
        handler.removeCallbacks(runnable);
    }
}
