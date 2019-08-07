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

public class AutomaticconsumptionActivity extends AppCompatActivity {

    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.automaticcnsumption_keyboard_enter_money)
    TextView automaticcnsumptionKeyboardEnterMoney;
    @BindView(R.id.persondetail_sure)
    Button persondetailSure;
    @BindView(R.id.persondetail_cancle)
    Button persondetailCancle;


    public static Intent getAutomaticconsumptionActivityIntent(Context context) {
        Intent intent = new Intent(context, AutomaticconsumptionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automaticconsumption);
        ButterKnife.bind(this);
        pageName.setText("自动扣款");
    }

    @OnClick({R.id.Manualconsumption_back, R.id.persondetail_sure, R.id.persondetail_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
            case R.id.persondetail_sure:
                break;
            case R.id.persondetail_cancle:
                break;
        }
    }
}
