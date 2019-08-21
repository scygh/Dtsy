package com.moredian.entrance.guard.view.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.entity.GetListByPage;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.view.activity.PersonDetailActivity;
import com.moredian.entrance.guard.view.adapter.PersonFindRvAdapter;

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

    private static final String TAG = "PersonFindFragment";
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
    @BindView(R.id.fpf_find_et)
    EditText fpfFindEt;
    @BindView(R.id.fpf_find_clear)
    ImageView fpfFindClear;
    private PersonFindRvAdapter adapter;
    List<GetListByPage.ContentBean.RowsBean> alldata = new ArrayList<>();
    List<GetListByPage.ContentBean.RowsBean> findData = new ArrayList<>();
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateData();
        }
    };

    private void updateData() {
        String data = fpfFindEt.getText().toString();
        if (!TextUtils.isEmpty(data)) {
            if (findData != null) {
                findData.clear();
            }
            for (int i = 0; i < alldata.size(); i++) {
                if (alldata.get(i).getUser().getName().contains(data)) {
                    findData.add(alldata.get(i));
                }
            }
            if (adapter == null) {
                initRecyclerview();
            } else {
                adapter.notifyDataSetChanged();
            }
        }
    }

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
        fpfFindClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fpfFindEt.setText("");
            }
        });
        fpfFindEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    fpfFindClear.setVisibility(View.INVISIBLE);
                } else {
                    fpfFindClear.setVisibility(View.VISIBLE);
                }
                handler.post(runnable);
            }
        });
    }

    @Override
    public void initData() {
        api.getListByPage(1, 5000);
        api.setOnResponse(new Api.OnResponse() {
            @Override
            public void onResponse(List<GetListByPage.ContentBean.RowsBean> rowsBeans) {
                if (alldata != null) {
                    alldata.clear();
                }
                alldata.addAll(rowsBeans);
            }

            @Override
            public void onResponseMore(List<GetListByPage.ContentBean.RowsBean> rowsBeans) {

            }

            @Override
            public void onFailed() {

            }
        });
    }

    /**
     * descirption: 刷新一次
     */
    private void refresh() {
        initData();
        handler.post(runnable);
    }

    /**
     * descirption: 初始化列表数据
     */
    private void initRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        personManageRecyclerview.setLayoutManager(linearLayoutManager);
        personManageRecyclerview.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        adapter = new PersonFindRvAdapter(mContext, findData);
        personManageRecyclerview.setAdapter(adapter);
        adapter.setMyItemClickListener(new PersonFindRvAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(String userid) {
                startActivity(PersonDetailActivity.getPersonDetailActivityIntent(mContext, userid));
            }
        });
    }

}
