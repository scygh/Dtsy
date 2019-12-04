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

import com.blankj.utilcode.util.SPUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetListByPage;
import com.moredian.entrance.guard.entity.GetUserByUserID;
import com.moredian.entrance.guard.entity.PostDeregister;
import com.moredian.entrance.guard.entity.PostRequestBody;
import com.moredian.entrance.guard.entity.PostResponse;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.view.activity.PersonDetailActivity;
import com.moredian.entrance.guard.view.adapter.PersonFindRvAdapter;
import com.moredian.entrance.guard.view.designview.SlideRecyclerView;

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
    SlideRecyclerView personManageRecyclerview;
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
    String token;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateData();
        }
    };
    private String toDeleteUserId;

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
        refresh();
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
        token = SPUtils.getInstance().getString(Constants.ACCESSTOKEN);
        api.getListByPage(1, 5000);
        api.setOnResponse(new Api.OnResponse<GetListByPage.ContentBean.RowsBean>() {
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
        api.setGetResponseListener(new Api.GetResponseListener() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof GetUserByUserID) {
                    //查询到了用户信息去删除
                    PostDeregister postDeregister = new PostDeregister();
                    postDeregister.setCost(((GetUserByUserID) o).getContent().getCost());
                    postDeregister.setMoney(((GetUserByUserID) o).getContent().getCash());
                    postDeregister.setUserID(((GetUserByUserID) o).getContent().getUserID());
                    api.postDeRegister(postDeregister, token);
                    refresh();
                } else if (o instanceof PostResponse) {
                    api.getUserByuserID(toDeleteUserId, token);
                }
            }

            @Override
            public void onFail(String err) {

            }
        });
    }

    /**
     * descirption: 刷新一次
     */
    private void refresh() {
        initData();
        if (findData.size() > 0) {
            handler.post(runnable);
        }
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

            @Override
            public void onDelete(String userID) {
                //先删除人脸
                PostRequestBody postRequestBody = new PostRequestBody(userID);
                api.postDelete(postRequestBody, token, Constants.MODIAN_TOKEN);
                toDeleteUserId = userID;
            }
        });
    }

}
