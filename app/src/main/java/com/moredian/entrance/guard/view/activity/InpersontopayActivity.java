package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.moredian.entrance.guard.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InpersontopayActivity extends BaseActivity {

    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.Manualconsumption_keyboard_enter_money)
    TextView ManualconsumptionKeyboardEnterMoney;

    public static Intent getInpersontopayActivityIntent(Context context) {
        Intent intent = new Intent(context, InpersontopayActivity.class);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.activity_inpersontopay;
    }

    @Override
    public void initView() {
        pageName.setText("时段定额");
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.Manualconsumption_back, R.id.page_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
        }
    }
}
