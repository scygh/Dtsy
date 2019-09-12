package com.moredian.entrance.guard.view.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.app.MainApplication;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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


    public static Intent getMainActivityIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = manager.getMemoryClass();
        int maxHeapSize = manager.getLargeMemoryClass();
        Log.d("memorya", "onCreate: " + heapSize + ":" + maxHeapSize);//192:384
    }

    @OnClick({R.id.main_ll1, R.id.main_ll2, R.id.main_ll3, R.id.main_ll4, R.id.main_ll5, R.id.main_ll6, R.id.main_ll7, R.id.main_ll8, R.id.main_ll9, R.id.main_ll10, R.id.main_ll11})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainApplication.getSerialPortUtils().closeSerialPort();
    }

}
