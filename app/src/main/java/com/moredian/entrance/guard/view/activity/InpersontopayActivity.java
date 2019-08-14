package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.moredian.entrance.guard.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InpersontopayActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inpersontopay);
        ButterKnife.bind(this);
    }

    @OnClick({ R.id.Manualconsumption_keyboard_enter_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_keyboard_enter_money:
                finish();
                break;
        }
    }
}
