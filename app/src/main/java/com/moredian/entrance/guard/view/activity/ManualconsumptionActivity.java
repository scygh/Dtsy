package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.moredian.entrance.guard.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManualconsumptionActivity extends AppCompatActivity {

    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.Manualconsumption_name)
    TextView ManualconsumptionName;
    @BindView(R.id.Manualconsumption_balance)
    TextView ManualconsumptionBalance;
    @BindView(R.id.Manualconsumption_keyboard_enter_money)
    TextView ManualconsumptionKeyboardEnterMoney;
    @BindView(R.id.Manualconsumption_usesdozensmallnotes)
    Button ManualconsumptionUsesdozensmallnotes;
    @BindView(R.id.page_name)
    TextView pageName;

    public static Intent getManualconsumptionActivityIntent(Context context) {
        Intent intent = new Intent(context, ManualconsumptionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manualconsumption);
        ButterKnife.bind(this);
        pageName.setText("手动扣款");
    }

    @OnClick({R.id.Manualconsumption_back, R.id.Manualconsumption_usesdozensmallnotes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.Manualconsumption_usesdozensmallnotes:
                break;
        }
    }
}
