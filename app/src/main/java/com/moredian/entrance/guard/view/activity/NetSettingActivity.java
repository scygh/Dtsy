package com.moredian.entrance.guard.view.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.moredian.entrance.guard.Constants;
import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.entity.AccessPoint;
import com.moredian.entrance.guard.util.WifiUtil;
import com.moredian.entrance.guard.view.adapter.NetSettingRvAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.net.wifi.WifiManager.EXTRA_SUPPLICANT_ERROR;

public class NetSettingActivity extends AppCompatActivity {

    private static final String TAG = "NetSettingActivity";
    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.net_setting_recyclerview)
    RecyclerView netSettingRecyclerview;
    @BindView(R.id.net_setting_switch)
    Switch netSettingSwitch;
    private WifiManager mWifiManager;
    private List<ScanResult> results;
    private NetSettingRvAdapter adapter;
    private WifiBroadCastReceiver wifiBroadCastReceiver;
    private static List<WifiConfiguration> mWifiConfigurations;

    public static Intent getNetSettingActivityIntent(Context context) {
        Intent intent = new Intent(context, NetSettingActivity.class);
        return intent;
    }

    Handler handler = new Handler();
    /**
     * descirption: 定时刷新任务
     */
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            results.clear();
            if (WifiUtil.getScanResult(mWifiManager).size() > 0) {
                results.addAll(WifiUtil.getScanResult(mWifiManager));
                adapter.notifyDataSetChanged();
                Log.d(TAG, "run: 刷新一次" + results.get(0).SSID);
                handler.postDelayed(this, 1000 * 10);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_setting);
        ButterKnife.bind(this);
        pageName.setText("网络设置");
        initWifi();
        initReceiver();
        netSettingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if(!mWifiManager.isWifiEnabled()){
                        mWifiManager.setWifiEnabled(true);
                        handler.postDelayed(runnable,5000);
                    }
                } else {
                    if(mWifiManager.isWifiEnabled()){
                        mWifiManager.setWifiEnabled(false);
                        results.clear();
                        adapter.notifyDataSetChanged();
                        handler.removeCallbacks(runnable);
                    }
                }
            }
        });
    }

    public void initReceiver() {
        wifiBroadCastReceiver = new WifiBroadCastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        filter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        if (wifiBroadCastReceiver != null) {
            registerReceiver(wifiBroadCastReceiver, filter);
        }
    }

    /**
     * descirption: 初始化wifi,请求位置权限
     */
    public void initWifi() {
        mWifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        requestPermission();
    }

    /**
     * descirption: request ACCESS_FINE_LOCATION
     */
    public void requestPermission() {
        if (ContextCompat.checkSelfPermission(NetSettingActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NetSettingActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Constants.LOCATION_REQUEST);
        } else {
            Toast.makeText(this, "权限允许", Toast.LENGTH_SHORT).show();
            openGPSandWIFISetting();
        }
    }

    /**
     * descirption: 检查GPS是否开启
     */
    private boolean checkGpsIsOpen() {
        boolean isOpen;
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        isOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isOpen;
    }

    /**
     * descirption: 检查GPS是否开启,如果开启，则扫描wifi，如果没有，弹出对话框提示开启
     */
    private void openGPSandWIFISetting() {
        if (checkGpsIsOpen()) {
            Toast.makeText(this, "开始扫描", Toast.LENGTH_SHORT).show();
            startScanwifi();
        } else {
            new AlertDialog.Builder(this).setTitle("提示")
                    .setMessage("需要打开GPS")
                    //  取消选项
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(NetSettingActivity.this, "close", Toast.LENGTH_SHORT).show();
                            // 关闭dialog
                            dialogInterface.dismiss();
                        }
                    })
                    //  确认选项
                    .setPositiveButton("前往", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //跳转到手机原生设置页面
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, Constants.LOCATION_REQUEST_2);
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
    }

    /**
     * descirption: 如果wifi没有开启，则开启wifi,如果开启了，就开始扫描附近wifi
     */
    private void startScanwifi() {
        //请求开启wifi
        boolean isOpen = mWifiManager.setWifiEnabled(true);
        if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {
            ToastUtils.showShort("正在开启wifi");
        }
        if (isOpen) {
            results = WifiUtil.getScanResult(mWifiManager);
        }
        mWifiConfigurations = mWifiManager.getConfiguredNetworks();
        //开启扫描后直接获取是空，所以需要延迟获取
        if (results.size() == 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startScanwifi();
                }
            }, 5000);
        } else {
            netSettingSwitch.setChecked(true);
            initRecyclerView();
            handler.postDelayed(runnable, 1000 * 10);
        }
    }

    /**
     * descirption: 初始化recyclerView
     */
    public void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        netSettingRecyclerview.setLayoutManager(linearLayoutManager);
        netSettingRecyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new NetSettingRvAdapter(NetSettingActivity.this, results);
        netSettingRecyclerview.setAdapter(adapter);
        adapter.setMyItemClickListener(new NetSettingRvAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //弹出对话框
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(NetSettingActivity.this);
                alertDialog.setTitle("连接到wifi:" + results.get(position).SSID);
                View view = View.inflate(NetSettingActivity.this, R.layout.wifi_alert_view, null);
                EditText et = view.findViewById(R.id.wifi_alert_view_et);
                alertDialog.setView(view);
                //  取消选项
                alertDialog.setNegativeButton("断开", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(NetSettingActivity.this, "close", Toast.LENGTH_SHORT).show();
                        mWifiManager.disconnect();
                    }
                });
                //  确认选项
                alertDialog.setPositiveButton("连接", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AccessPoint accessPoint = new AccessPoint(results.get(position).SSID, results.get(position).capabilities, et.getText().toString());
                        WifiConfiguration wifiConfiguration = createConfiguration(accessPoint);
                        //如果你设置的wifi是设备已经存储过的，那么这个networkId会返回小于0的值。
                        int networkId = mWifiManager.addNetwork(wifiConfiguration);
                        Log.d(TAG, "onClick: " + results.get(position).SSID + results.get(position).capabilities + et.getText().toString() + "networkid" + networkId);
                        mWifiManager.enableNetwork(networkId, true);
                    }
                });
                AlertDialog dialog = alertDialog.create();
                dialog.setView(view, 30, 50, 30, 50);
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    public void onShow(DialogInterface dialog) {
                        handler.removeCallbacks(runnable);
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
                    }
                });
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        handler.postDelayed(runnable,5000);
                    }
                });
                dialog.show();
            }
        });
    }

    /**
     * descirption: 判断wifi是否已保存
     */
    public static WifiConfiguration isWifiSave(String SSID) {
        if (mWifiConfigurations != null) {
            for (WifiConfiguration existingConfig : mWifiConfigurations) {
                if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
                    return existingConfig;
                }
            }
        }
        return null;
    }

    /**
     * descirption: 创建要连接的WifiConfiguration
     */
    public WifiConfiguration createConfiguration(AccessPoint ap) {
        String SSID = ap.getSsid();
        String password = ap.getPassword();
        String encryptionType = ap.getEncryptionType();
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + SSID + "\"";
        //判断当前连接的wifi保存了密码，清除wifi保存信息
        WifiConfiguration tempConfig = isWifiSave(SSID);
        if (tempConfig != null) {
            mWifiManager.removeNetwork(tempConfig.networkId);
            mWifiManager.saveConfiguration();
            Log.d(TAG, "createConfiguration: 清除wifi保存信息");
        }
        if (encryptionType.contains("WEP")) {
            Log.d(TAG, "createConfiguration: wep");
            /**
             * special handling according to password length is a must for wep
             */
            int i = password.length();
            if (((i == 10 || (i == 26) || (i == 58))) && (password.matches("[0-9A-Fa-f]*"))) {
                config.wepKeys[0] = password;
            } else {
                config.wepKeys[0] = "\"" + password + "\"";
            }
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        } else if (encryptionType.contains("WPA")) {
            Log.d(TAG, "createConfiguration: wpa");
            config.preSharedKey = "\"" + password + "\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            config.status = WifiConfiguration.Status.ENABLED;
        } else {
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        }
        return config;
    }

    @OnClick({R.id.Manualconsumption_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                finish();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.LOCATION_REQUEST) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "权限允许", Toast.LENGTH_SHORT).show();
                    openGPSandWIFISetting();
                } else {
                    Toast.makeText(this, "你拒绝了权限请求", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.LOCATION_REQUEST_2) {
            startScanwifi();
        }
    }

    class WifiBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                //当扫描到结果后
                Log.d(TAG, "onReceive: 广播收到信息，您已扫描到结果");
            } else if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
                //wifi连接网络状态
                NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                NetworkInfo.DetailedState state = info.getDetailedState();
                if (state == state.SCANNING) {
                    ToastUtils.showShort("正在扫描");
                } else if (state == state.AUTHENTICATING) {
                    ToastUtils.showShort("正在验证身份信息");
                } else if (state == state.OBTAINING_IPADDR) {
                    ToastUtils.showShort("正在获取IP地址");
                } else if (state == state.CONNECTING) {
                    ToastUtils.showShort("正在连接");
                } else if (state == state.CONNECTED) {
                    ToastUtils.showShort("建立连接");
                    adapter.notifyDataSetChanged();
                } else if (state == state.DISCONNECTING) {
                    ToastUtils.showShort("正在断开连接");
                } else if (state == state.DISCONNECTED) {
                    ToastUtils.showShort("已断开连接");
                    adapter.notifyDataSetChanged();
                } else if (state == state.FAILED) {
                    ToastUtils.showShort("连接失败");
                    adapter.notifyDataSetChanged();
                }
            } else if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
                ////这个监听wifi的打开与关闭，与wifi的连接无关
                int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1);
                switch (wifiState) {
                    case WifiManager.WIFI_STATE_DISABLED:
                        Log.d("WIFI状态", "wifiState:WIFI_STATE_DISABLED");
                        break;
                    case WifiManager.WIFI_STATE_DISABLING:
                        Log.d("WIFI状态", "wifiState:WIFI_STATE_DISABLING");
                        break;
                    case WifiManager.WIFI_STATE_ENABLED:
                        Log.d("WIFI状态", "wifiState:WIFI_STATE_ENABLED");
                        break;
                    case WifiManager.WIFI_STATE_ENABLING:
                        Log.d("WIFI状态", "wifiState:WIFI_STATE_ENABLING");
                        break;
                    case WifiManager.WIFI_STATE_UNKNOWN:
                        Log.d("WIFI状态", "wifiState:WIFI_STATE_UNKNOWN");
                        break;
                }
            } else if (intent.getAction().equals(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION)) {
                SupplicantState state = intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE);
                int error = intent.getIntExtra(EXTRA_SUPPLICANT_ERROR, 0);
                if (error == WifiManager.STATUS_NETWORK_SUGGESTIONS_ERROR_ADD_DUPLICATE) {
                    ToastUtils.showShort("密码有误");
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(wifiBroadCastReceiver);
        mWifiConfigurations.clear();
        results.clear();
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }
}
