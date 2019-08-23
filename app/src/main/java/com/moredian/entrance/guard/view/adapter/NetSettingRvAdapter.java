package com.moredian.entrance.guard.view.adapter;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.utils.WifiUtil;
import com.moredian.entrance.guard.view.activity.NetSettingActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description ：NetSettingRvAdapter
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/29 08:46
 */
public class NetSettingRvAdapter extends RecyclerView.Adapter<NetSettingRvAdapter.ViewHolder> {
    private static final String TAG = "NetSettingRvAdapter";
    private Context context;
    private List<ScanResult> results;
    private OnMyItemClickListener myItemClickListener;

    public interface OnMyItemClickListener {
        void onItemClick(int position);
        boolean onLongItemClick(int position);
    }

    public void setMyItemClickListener(OnMyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    public NetSettingRvAdapter(Context context, List<ScanResult> results) {
        this.context = context;
        this.results = results;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_wifiname)
        TextView tvWifiname;
        @BindView(R.id.tv_wifistatus)
        TextView tvWifistatus;
        @BindView(R.id.iv_wifi_rssi)
        ImageView ivWifiRssi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return myItemClickListener.onLongItemClick(getAdapterPosition());
                }
            });
        }

        /**
         * descirption:
         * BSSID:访问点的地址。
         * SSID :网络名称。
         * capabilities:描述了身份验证、密钥管理和访问点支持的加密方案。
         */
        public void bind() {
            ScanResult scanResult = results.get(getAdapterPosition());
            String currentWifiName = WifiUtil.getCurrentWifiSSID(context);
            if (scanResult.SSID != null) {
                tvWifiname.setText(scanResult.SSID);
                Log.d(TAG, "bind: " + currentWifiName + "\"" + scanResult.SSID + "\"");
                if (currentWifiName.equals("\"" + scanResult.SSID + "\"")) {
                    tvWifiname.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    tvWifistatus.setText("已连接");
                } else {
                    tvWifiname.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    if (NetSettingActivity.isWifiSave(scanResult.SSID) != null) {
                        tvWifistatus.setText("已保存");
                    } else {
                        tvWifistatus.setText("mac地址:" + results.get(getAdapterPosition()).BSSID +"信号强度:"+ scanResult.level);
                    }
                }
                int rssi = Math.abs(scanResult.level);
                if (rssi <= 50) {
                    ivWifiRssi.setImageResource(R.mipmap.wifi_rssi_4);
                } else if (rssi > 50 && rssi <= 65) {
                    ivWifiRssi.setImageResource(R.mipmap.wifi_rssi_3);
                } else if (rssi > 65 && rssi <= 80) {
                    ivWifiRssi.setImageResource(R.mipmap.wifi_rssi_2);
                } else if (rssi > 80) {
                    ivWifiRssi.setImageResource(R.mipmap.wifi_rssi_1);
                }
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wifi_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
