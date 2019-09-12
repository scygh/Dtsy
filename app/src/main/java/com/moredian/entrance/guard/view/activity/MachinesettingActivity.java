package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.app.MainApplication;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetDeviceNumList;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.view.adapter.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

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
    Spinner machinesettingMachineNumber;
    @BindView(R.id.machinesetting_port)
    Spinner machinesettingPort;
    @BindView(R.id.machinesetting_baudrate)
    EditText machinesettingBaudrate;
    @BindView(R.id.persondetail_sure)
    Button persondetailSure;
    @BindView(R.id.persondetail_cancle)
    Button persondetailCancle;
    private List<String> deviceNums = new ArrayList<>();

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
        machinesettingBaudrate.setText(SPUtils.getInstance().getString(Constants.MACHINE_BAUDRTE, "115200"));
        String port = SPUtils.getInstance().getString(Constants.MACHINE_PORT, "/dev/ttyMT2");
        for (int j = 0; j < attr.length; j++) {
            if (attr[j].equals(port)) {
                machinesettingPort.setSelection(j);
            }
        }
    }

    @Override
    public void initData() {
        api.getDeviceNumList(token);
        api.setGetResponseListener(new Api.GetResponseListener() {
            @Override
            public void onRespnse(Object o) {
                if (o instanceof GetDeviceNumList) {
                    deviceNums.clear();
                    for (int i = 0; i < ((GetDeviceNumList) o).getContent().size(); i++) {
                        deviceNums.add(((GetDeviceNumList) o).getContent().get(i).getID() + "");
                    }
                    String[] arr = deviceNums.toArray(new String[((GetDeviceNumList) o).getContent().size()]);
                    machinesettingMachineNumber.setAdapter(new SpinnerAdapter(MachinesettingActivity.this, arr));
                    String devicenum = SPUtils.getInstance().getString(Constants.MACHINE_NUMBER, "1");
                    for (int j = 0; j < deviceNums.size(); j++) {
                        if (deviceNums.get(j).equals(devicenum)) {
                            machinesettingMachineNumber.setSelection(j);
                        }
                    }
                }
            }

            @Override
            public void onFail(String err) {

            }
        });
    }


    @OnClick({R.id.persondetail_sure, R.id.persondetail_cancle, R.id.Manualconsumption_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.persondetail_sure:
                String machineNumber = (String) machinesettingMachineNumber.getSelectedItem();
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
