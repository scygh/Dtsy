package com.moredian.entrance.guard.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.entity.GetListByPage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description ：NetSettingRvAdapter
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/29 08:46
 */
public class PersonManageRvAdapter extends RecyclerView.Adapter<PersonManageRvAdapter.ViewHolder> {
    private static final String TAG = "NetSettingRvAdapter";
    private Context context;
    List<GetListByPage.ContentBean.RowsBean> rowsBeans;
    private OnMyItemClickListener myItemClickListener;

    public interface OnMyItemClickListener {
        void onItemClick(int position);
    }

    public void setMyItemClickListener(OnMyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    public PersonManageRvAdapter(Context context, List<GetListByPage.ContentBean.RowsBean> rowsBeans) {
        this.context = context;
        this.rowsBeans = rowsBeans;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_wifiname)
        TextView tvWifiname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
        public void bind() {
            String name = rowsBeans.get(getAdapterPosition()).getName();
            tvWifiname.setText(name);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.person_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return rowsBeans.size();
    }
}
