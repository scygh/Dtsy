package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.app.MainApplication;
import com.moredian.entrance.guard.constant.Constants;

import android_serialport_api.ChangeTool;
import android_serialport_api.SerialPortUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SerialPortUtils";

    @BindView(R.id.logout_btn)
    Button logoutBtn;
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
        MainApplication.getSerialPortUtils().setOnDataReceiveListener(new SerialPortUtils.OnDataReceiveListener() {
            @Override
            public void onDataReceive(byte[] buffer, int size) {
                Log.d(TAG, "onDataReceive: " + ChangeTool.ByteArrToHex(buffer, 0, size));
            }
        });
    }

    public void send(View view) {
        MainApplication.getSerialPortUtils().sendSerialPort(Constants.BUZZING);
    }

    @OnClick({R.id.main_ll1, R.id.main_ll2, R.id.main_ll3, R.id.main_ll4, R.id.main_ll5, R.id.main_ll6,R.id.main_ll7, R.id.logout_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_ll1:
                startActivity(ManualconsumptionActivity.getManualconsumptionActivityIntent(this));
                break;
            case R.id.main_ll2:
                startActivity(AutomaticconsumptionActivity.getAutomaticconsumptionActivityIntent(this));
                break;
            case R.id.main_ll3:
                startActivity(InpersontopayActivity.getInpersontopayActivityIntent(this));
                break;
            case R.id.main_ll4:
                startActivity(PersonsManageActivity.getPersonsManageActivityIntent(this));
                break;
            case R.id.main_ll5:
                startActivity(MachinesettingActivity.getMachinesettingActivityIntent(this));
                break;
            case R.id.main_ll6:
                startActivity(NetSettingActivity.getNetSettingActivityIntent(this));
                break;
            case R.id.main_ll7:
                startActivity(PersonalSettingActivity.getPersonalSettingActivityIntent(this));
                break;
            case R.id.logout_btn:
                startActivity(LoginActivity.getLoginActivityIntent(this));
                SPUtils.getInstance().put(Constants.ISLOGIN, false);
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainApplication.getSerialPortUtils().closeSerialPort();
    }

}
