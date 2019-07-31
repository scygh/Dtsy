package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.R;
import android_serialport_api.SerialPortUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MachinesettingActivity extends AppCompatActivity {

    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.machinesetting_machine_number)
    EditText machinesettingMachineNumber;
    @BindView(R.id.machinesetting_port)
    EditText machinesettingPort;
    @BindView(R.id.machinesetting_baudrate)
    EditText machinesettingBaudrate;
    @BindView(R.id.machinesetting_sure)
    Button machinesettingSure;
    @BindView(R.id.machinesetting_cancle)
    Button machinesettingCancle;

    public static Intent getMachinesettingActivityIntent(Context context) {
        Intent intent = new Intent(context, MachinesettingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machinesetting);
        ButterKnife.bind(this);
        pageName.setText("机器设置");
        machinesettingMachineNumber.setText(SPUtils.getInstance().getString(Constants.MACHINE_NUMBER,"unknnow"));
        machinesettingPort.setText(SPUtils.getInstance().getString(Constants.MACHINE_PORT,"unknnow"));
        machinesettingBaudrate.setText(SPUtils.getInstance().getString(Constants.MACHINE_BAUDRTE,"unknnow"));
    }

    @OnClick({R.id.machinesetting_sure, R.id.machinesetting_cancle,R.id.Manualconsumption_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.machinesetting_sure:
                SerialPortUtils serialPortUtils = new SerialPortUtils();
                String machineNumber = machinesettingMachineNumber.getText().toString();
                String machinePort = machinesettingPort.getText().toString();
                String machineBaudrate = machinesettingBaudrate.getText().toString();
                SPUtils.getInstance().put(Constants.MACHINE_NUMBER,machineNumber);
                SPUtils.getInstance().put(Constants.MACHINE_PORT,machinePort);
                SPUtils.getInstance().put(Constants.MACHINE_BAUDRTE,machineBaudrate);
                ToastUtils.showShort("保存成功");
                break;
            case R.id.machinesetting_cancle:
                finish();
                break;
            case R.id.Manualconsumption_back:
                finish();
                break;
        }
    }
}
