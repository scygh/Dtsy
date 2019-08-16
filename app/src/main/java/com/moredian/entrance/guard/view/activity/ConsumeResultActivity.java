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
import com.moredian.entrance.guard.entity.FaceExpense;
import com.moredian.entrance.guard.entity.QRCodeExpense;
import com.moredian.entrance.guard.entity.SimpleExpense;
import com.moredian.entrance.guard.face.drawface.FaceDefaultDrawer;

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

    public static Intent getConsumeSuccessActivityIntent(Context context, SimpleExpense.ContentBean contentBean) {
        Intent intent = new Intent(context, ConsumeResultActivity.class);
        intent.putExtra(Constants.INTENT_CONSUME_SPSUCCESS, contentBean);
        return intent;
    }

    public static Intent getQRConsumeSuccessActivityIntent(Context context, QRCodeExpense.ContentBean contentBean) {
        Intent intent = new Intent(context, ConsumeResultActivity.class);
        intent.putExtra(Constants.INTENT_CONSUME_QRSUCCESS, contentBean);
        return intent;
    }

    public static Intent getFaceConsumeSuccessActivityIntent(Context context, FaceExpense.ContentBean contentBean) {
        Intent intent = new Intent(context, ConsumeResultActivity.class);
        intent.putExtra(Constants.INTENT_CONSUME_FACESUCCESS, contentBean);
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
        QRCodeExpense.ContentBean qrcontentbean = getIntent().getParcelableExtra(Constants.INTENT_CONSUME_QRSUCCESS);
        SimpleExpense.ContentBean secontentbean = getIntent().getParcelableExtra(Constants.INTENT_CONSUME_SPSUCCESS);
        FaceExpense.ContentBean facecontentbean = getIntent().getParcelableExtra(Constants.INTENT_CONSUME_FACESUCCESS);
        if (secontentbean != null) {
            if (secontentbean.getExpenseDetail() != null) {
                csConsumeResult.setText("支付成功");
                csConsumeKind.setText("刷卡支付");
                csAmountBig.setText(secontentbean.getExpenseDetail().getAmount() + "");
                csAmountSmall.setText(secontentbean.getExpenseDetail().getAmount() + "");
                csBalance.setText(secontentbean.getExpenseDetail().getBalance() + "");
                csDate.setText(secontentbean.getExpenseDetail().getCreateTime());
                int partern = secontentbean.getExpenseDetail().getPattern();
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
            }
        }
        if (qrcontentbean != null) {
            csConsumeResult.setText("支付成功");
            csAmountBig.setText(qrcontentbean.getThirdPartyExpense().getAmount() + "");
            csAmountSmall.setText(qrcontentbean.getThirdPartyExpense().getAmount() + "");
            csBalanceRl.setVisibility(View.GONE);
            csDate.setText(qrcontentbean.getThirdPartyExpense().getCreateTime());
            int partern = qrcontentbean.getThirdPartyExpense().getPattern();
            int qrtype = qrcontentbean.getQRType();
            if (qrtype == 1) {
                csConsumeKind.setText("微信二维码支付");
            } else if (qrtype == 2) {
                csConsumeKind.setText("支付宝二维码支付");
            } else if (qrtype == 3) {
                csConsumeKind.setText("会员码支付");
            } else if (qrtype == 4) {
                csConsumeKind.setText("取餐码支付");
            }
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
        }
        if (facecontentbean != null) {
            csConsumeResult.setText("支付成功");
            csAmountBig.setText(facecontentbean.getExpenseDetail().getAmount() + "");
            csAmountSmall.setText(facecontentbean.getExpenseDetail().getAmount() + "");
            csBalance.setText(facecontentbean.getExpenseDetail().getBalance() + "");
            csDate.setText(facecontentbean.getExpenseDetail().getCreateTime());
            int partern = facecontentbean.getExpenseDetail().getPattern();
            csConsumeKind.setText("人脸支付");
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
        }
        if (secontentbean == null && qrcontentbean == null && facecontentbean == null) {
            csConsumeResult.setText("支付失败");
            csConsumeResult.setTextColor(getResources().getColor(R.color.color_f00));
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
