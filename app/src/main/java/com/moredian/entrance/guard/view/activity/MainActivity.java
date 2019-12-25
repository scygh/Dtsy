package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moredian.entrance.guard.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_ll1)
    LinearLayout mainLl1;
    @BindView(R.id.main_ll2)
    LinearLayout mainLl2;
    @BindView(R.id.main_ll3)
    LinearLayout mainLl3;
    @BindView(R.id.main_ll4)
    LinearLayout mainLl4;
    @BindView(R.id.main_ll5)
    LinearLayout mainLl5;
    @BindView(R.id.main_ll6)
    LinearLayout mainLl6;
    @BindView(R.id.main_ll7)
    LinearLayout mainLl7;
    @BindView(R.id.main_ll8)
    LinearLayout mainLl8;
    @BindView(R.id.main_ll9)
    LinearLayout mainLl9;
    @BindView(R.id.main_ll10)
    LinearLayout mainLl10;
    @BindView(R.id.main_ll11)
    LinearLayout mainLl11;
    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;

    public static Intent getMainActivityIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        pageName.setText("我的菜单");
    }

    @Override
    public int layoutView() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.Manualconsumption_back, R.id.main_ll1, R.id.main_ll2, R.id.main_ll3, R.id.main_ll4, R.id.main_ll5, R.id.main_ll6, R.id.main_ll7, R.id.main_ll8, R.id.main_ll9, R.id.main_ll10, R.id.main_ll11})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                startActivity(DsyActivity.getDsyActivityIntent(this));
                finish();
                break;
            case R.id.main_ll1:
                startActivity(ManualconsumptionActivity.getManualconsumptionActivityIntent(this));
                break;
            case R.id.main_ll2:
                startActivity(AutomaticconsumptionActivity.getAutomaticconsumptionActivityIntent(this));
                break;
            case R.id.main_ll3:
                startActivity(InpersontopayActivity.getInpersontopayActivityIntent(this, mainLl3));
                break;
            case R.id.main_ll4:
                startActivity(PersonsManageActivity.getPersonsManageActivityIntent(this));
                break;
            case R.id.main_ll5:
                startActivity(VoucherCenterActivity.getVoucherCenterActivityIntent(this));
                break;
            case R.id.main_ll6:
                startActivity(ConsumeRecordActivity.getConsumeRecordActivityIntent(this));
                break;
            case R.id.main_ll7:
                startActivity(DepositRecordActivity.getDepositRecordActivityIntent(this));
                break;
            case R.id.main_ll8:
                startActivity(MachinesettingActivity.getMachinesettingActivityIntent(this));
                break;
            case R.id.main_ll9:
                startActivity(NetSettingActivity.getNetSettingActivityIntent(this));
                break;
            case R.id.main_ll10:
                startActivity(PersonalSettingActivity.getPersonalSettingActivityIntent(this));
                break;
            case R.id.main_ll11:
                startActivity(ChartActivity.getChartActivityIntent(this));
                break;
        }
    }

    /**
     * descirption: 自动会finish
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(DsyActivity.getDsyActivityIntent(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
