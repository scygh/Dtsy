package com.moredian.entrance.guard.view.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.app.MainApplication;
import com.moredian.entrance.guard.constant.Constants;
import com.moredian.entrance.guard.entity.GetDeviceNumList;
import com.moredian.entrance.guard.entity.PostsetDevicePattern;
import com.moredian.entrance.guard.http.Api;
import com.moredian.entrance.guard.view.adapter.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import android_serialport_api.SerialPortFinder;
import butterknife.BindView;
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
    @BindView(R.id.machinesetting_device_pattern)
    Spinner machinesettingDevicePattern;
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
        //设置串口和波特率的显示
        SerialPortFinder finder = new SerialPortFinder();
        String[] attr = finder.getAllDevicesPath();
        machinesettingPort.setAdapter(new SpinnerAdapter(this, attr));
        machinesettingBaudrate.setText(SPUtils.getInstance().getString(Constants.MACHINE_BAUDRTE, Constants.BAUDRATE));
        String port = SPUtils.getInstance().getString(Constants.MACHINE_PORT, Constants.SERIALPORT);
        for (int j = 0; j < attr.length; j++) {
            if (attr[j].equals(port)) {
                machinesettingPort.setSelection(j);
                break;
            }
        }
        //消费模式
        String[] devicePattern = getResources().getStringArray(R.array.devicepattern);
        machinesettingDevicePattern.setAdapter(new SpinnerAdapter(this, devicePattern));
        String pattern = SPUtils.getInstance().getString(Constants.DEVICE_PATTERN);
        for (int k = 0; k < devicePattern.length; k++) {
            if (devicePattern[k].equals(pattern)) {
                machinesettingDevicePattern.setSelection(k);
                break;
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


    @OnClick({R.id.persondetail_sure, R.id.Manualconsumption_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.persondetail_sure:
                String machineNumber = (String) machinesettingMachineNumber.getSelectedItem();
                String machinePort = (String) machinesettingPort.getSelectedItem();
                String machineBaudrate = machinesettingBaudrate.getText().toString();
                String devicepattern = (String) machinesettingDevicePattern.getSelectedItem();
                SPUtils.getInstance().put(Constants.MACHINE_NUMBER, machineNumber);
                SPUtils.getInstance().put(Constants.MACHINE_PORT, machinePort);
                SPUtils.getInstance().put(Constants.MACHINE_BAUDRTE, machineBaudrate);
                SPUtils.getInstance().put(Constants.DEVICE_PATTERN, devicepattern);
                String pattern = "";
                if (devicepattern.equals("手动消费")) {
                    pattern = "1";
                } else if (devicepattern.equals("自动消费")) {
                    pattern = "2";
                } else if (devicepattern.equals("定值消费")) {
                    pattern = "3";
                }
                PostsetDevicePattern postsetDevicePattern = new PostsetDevicePattern("0", pattern);
                api.setDevicePattern(postsetDevicePattern, Integer.parseInt(deviceId), token);
                MainApplication.getSerialPortUtils().closeSerialPort();
                MainApplication.getSerialPortUtils().openSerialPort(machinePort, Integer.parseInt(machineBaudrate));
                finish();
                break;
            case R.id.Manualconsumption_back:
                finish();
                break;
        }
    }
}
