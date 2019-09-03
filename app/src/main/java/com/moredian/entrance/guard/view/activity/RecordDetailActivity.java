package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetDepositPage;
import com.moredian.entrance.guard.entity.GetExpensePage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordDetailActivity extends BaseActivity {

    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.ard_money)
    TextView ardMoney;
    @BindView(R.id.ard_success_tip)
    TextView ardSuccessTip;
    @BindView(R.id.ard_username)
    TextView ardUsername;
    @BindView(R.id.ard_cardnumber)
    TextView ardCardnumber;
    @BindView(R.id.ard_paycount)
    TextView ardPaycount;
    @BindView(R.id.ard_balance)
    TextView ardBalance;
    @BindView(R.id.ard_pattern)
    TextView ardPattern;
    @BindView(R.id.ard_TradeDateTime)
    TextView ardTradeDateTime;
    @BindView(R.id.paycount_rl)
    RelativeLayout paycountRl;
    @BindView(R.id.pattern_rl)
    RelativeLayout patternRl;

    public static Intent getRecordDetailActivityIntent(Context context, GetExpensePage.ContentBean.RowsBean rowsBean) {
        Intent intent = new Intent(context, RecordDetailActivity.class);
        intent.putExtra(Constants.INTENT_CONSUMERECORD, rowsBean);
        return intent;
    }

    public static Intent getRecordDetailActivityIntent(Context context, GetDepositPage.ContentBean.RowsBean rowsBean) {
        Intent intent = new Intent(context, RecordDetailActivity.class);
        intent.putExtra(Constants.INTENT_DEPOSITRECORD, rowsBean);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.activity_record_detail;
    }

    @Override
    public void initView() {
        pageName.setText("账单详情");
        ManualconsumptionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        GetExpensePage.ContentBean.RowsBean rowsBean = getIntent().getParcelableExtra(Constants.INTENT_CONSUMERECORD);
        GetDepositPage.ContentBean.RowsBean deposit = getIntent().getParcelableExtra(Constants.INTENT_DEPOSITRECORD);
        if (rowsBean != null) {
            ardMoney.setText(rowsBean.getAmount() + "");
            ardUsername.setText(rowsBean.getName());
            ardCardnumber.setText(rowsBean.getNumber() + "");
            ardPaycount.setText(rowsBean.getPayCount() + "");
            ardBalance.setText(rowsBean.getBalance() + "");
            ardPattern.setText(rowsBean.getPatternName());
            ardTradeDateTime.setText(rowsBean.getTradeDateTime());
        } else if (deposit != null) {
            ardMoney.setText(deposit.getAmount() + "");
            ardUsername.setText(deposit.getName());
            ardCardnumber.setText(deposit.getNumber() + "");
            ardBalance.setText(deposit.getAfterBalance() + "");
            ardTradeDateTime.setText(deposit.getTradeDateTime());
            paycountRl.setVisibility(View.GONE);
            patternRl.setVisibility(View.GONE);
        }
    }

}
