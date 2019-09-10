package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.app.MainApplication;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.view.adapter.SpinnerAdapter;

import android_serialport_api.SerialPortFinder;
import android_serialport_api.SerialPortUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MachinesettingActivity extends BaseActivity {

    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.machinesetting_machine_number)
    EditText machinesettingMachineNumber;
    @BindView(R.id.machinesetting_port)
    Spinner machinesettingPort;
    @BindView(R.id.machinesetting_baudrate)
    EditText machinesettingBaudrate;
    @BindView(R.id.persondetail_sure)
    Button persondetailSure;
    @BindView(R.id.persondetail_cancle)
    Button persondetailCancle;

    public static Intent getMachinesettingActivityIntent(Context context) {
        Intent intent = new Intent(context, MachinesettingActivity.class);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.activity_machinesetting;
    }

    @Override
    public void initView() {
        pageName.setText("机器设置");
        SerialPortFinder finder = new SerialPortFinder();
        String[] attr = finder.getAllDevicesPath();
        machinesettingPort.setAdapter(new SpinnerAdapter(this, attr));
        machinesettingMachineNumber.setText(SPUtils.getInstance().getString(Constants.MACHINE_NUMBER, "001"));
        machinesettingBaudrate.setText(SPUtils.getInstance().getString(Constants.MACHINE_BAUDRTE, "115200"));
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.persondetail_sure, R.id.persondetail_cancle, R.id.Manualconsumption_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.persondetail_sure:
                String machineNumber = machinesettingMachineNumber.getText().toString();
                String machinePort = (String) machinesettingPort.getSelectedItem();
                String machineBaudrate = machinesettingBaudrate.getText().toString();
                SPUtils.getInstance().put(Constants.MACHINE_NUMBER, machineNumber);
                SPUtils.getInstance().put(Constants.MACHINE_PORT, machinePort);
                SPUtils.getInstance().put(Constants.MACHINE_BAUDRTE, machineBaudrate);
                ToastUtils.showShort("保存成功");
                MainApplication.getSerialPortUtils().closeSerialPort();
                MainApplication.getSerialPortUtils().openSerialPort(machinePort, Integer.parseInt(machineBaudrate));
                break;
            case R.id.persondetail_cancle:
                finish();
                break;
            case R.id.Manualconsumption_back:
                finish();
                break;
        }
    }
}
