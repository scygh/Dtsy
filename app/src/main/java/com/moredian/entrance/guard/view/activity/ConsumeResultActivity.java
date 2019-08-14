package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.QRCodeExpense;
import com.moredian.entrance.guard.entity.SimpleExpense;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsumeResultActivity extends AppCompatActivity {

    @BindView(R.id.cs_over)
    TextView csOver;
    @BindView(R.id.cs_amount_big)
    TextView csAmountBig;
    @BindView(R.id.cs_consume_kind)
    TextView csConsumeKind;
    @BindView(R.id.cs_balance)
    TextView csBalance;
    @BindView(R.id.cs_amount_small)
    TextView csAmountSmall;
    @BindView(R.id.cs_date)
    TextView csDate;
    @BindView(R.id.cs_consume_pattern)
    TextView csConsumePattern;
    @BindView(R.id.cs_consume_result)
    TextView csConsumeResult;
    @BindView(R.id.cs_amount_big_ll)
    LinearLayout csAmountBigLl;
    @BindView(R.id.cs_balance_rl)
    RelativeLayout csBalanceRl;
    @BindView(R.id.cs_ll_detail)
    LinearLayout csLlDetail;
    private Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            finish();
        }
    };

    public static Intent getConsumeSuccessActivityIntent(Context context, SimpleExpense.ContentBean contentBean, String kind) {
        Intent intent = new Intent(context, ConsumeResultActivity.class);
        intent.putExtra(Constants.INTENT_CONSUME_SKSUCCESS, contentBean);
        intent.putExtra(Constants.INTENT_CONSUME_KIND, kind);
        return intent;
    }

    public static Intent getQRConsumeSuccessActivityIntent(Context context, QRCodeExpense.ContentBean.DetailsBean detailsBean, String kind) {
        Intent intent = new Intent(context, ConsumeResultActivity.class);
        intent.putExtra(Constants.INTENT_CONSUME_QRSUCCESS, detailsBean);
        intent.putExtra(Constants.INTENT_CONSUME_KIND, kind);
        return intent;
    }

    public static Intent getConsumeFailActivityIntent(Context context) {
        Intent intent = new Intent(context, ConsumeResultActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume_success);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        csConsumeKind.setText(getIntent().getStringExtra(Constants.INTENT_CONSUME_KIND));
        SimpleExpense.ContentBean contentBean = getIntent().getParcelableExtra(Constants.INTENT_CONSUME_SKSUCCESS);
        QRCodeExpense.ContentBean.DetailsBean detailsBean = getIntent().getParcelableExtra(Constants.INTENT_CONSUME_QRSUCCESS);
        if (detailsBean != null) {
            csConsumeResult.setText("支付成功");
            csAmountBig.setText(detailsBean.getAmount() + "");
            csAmountSmall.setText(detailsBean.getAmount() + "");
            csBalanceRl.setVisibility(View.GONE);
            csDate.setText(detailsBean.getCreateTime());
            int partern = detailsBean.getPattern();
            if (partern == 1) {
                csConsumePattern.setText("手动消费");
            } else if (partern == 2) {
                csConsumePattern.setText("自动消费");
            } else if (partern == 3) {
                csConsumePattern.setText("定值消费");
            } else if (partern == 4) {
                csConsumePattern.setText("商品消费");
            } else if (partern == 5) {
                csConsumePattern.setText("机器充值");
            } else if (partern == 6) {
                csConsumePattern.setText("机器退款");
            } else if (partern == 7) {
                csConsumePattern.setText("订餐模式");
            }
        } else if (contentBean != null) {
            csConsumeResult.setText("支付成功");
            csAmountBig.setText(contentBean.getAmount() + "");
            csAmountSmall.setText(contentBean.getAmount() + "");
            csBalance.setText(contentBean.getBalance() + "");
            csDate.setText(contentBean.getCreateTime());
            int partern = contentBean.getPattern();
            if (partern == 1) {
                csConsumePattern.setText("手动消费");
            } else if (partern == 2) {
                csConsumePattern.setText("自动消费");
            } else if (partern == 3) {
                csConsumePattern.setText("定值消费");
            } else if (partern == 4) {
                csConsumePattern.setText("商品消费");
            } else if (partern == 5) {
                csConsumePattern.setText("机器充值");
            } else if (partern == 6) {
                csConsumePattern.setText("机器退款");
            } else if (partern == 7) {
                csConsumePattern.setText("订餐模式");
            }
        } else {
            csConsumeResult.setText("支付失败");
            csAmountBigLl.setVisibility(View.GONE);
            csLlDetail.setVisibility(View.GONE);
        }

        handler.postDelayed(runnable, 10000);
    }

    @OnClick({R.id.cs_over})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cs_over:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
