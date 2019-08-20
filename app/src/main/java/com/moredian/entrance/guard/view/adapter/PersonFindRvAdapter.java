package com.moredian.entrance.guard.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class PersonFindRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<GetListByPage.ContentBean.RowsBean> rowsBeans;
    private OnMyItemClickListener myItemClickListener;

    public interface OnMyItemClickListener {
        void onItemClick(int position);
    }

    public void setMyItemClickListener(OnMyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    public PersonFindRvAdapter(Context context, List<GetListByPage.ContentBean.RowsBean> rowsBeans) {
        this.context = context;
        this.rowsBeans = rowsBeans;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_wifiname)
        TextView tvWifiname;
        @BindView(R.id.tv_isfaceinput)
        TextView tvIsfaceinput;

        public ItemViewHolder(@NonNull View itemView) {
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
            String name = rowsBeans.get(getAdapterPosition()).getUser().getName();
            tvWifiname.setText(name);
            if (rowsBeans.get(getAdapterPosition()).getUserFace().getMemberFace() != null) {
                tvIsfaceinput.setVisibility(View.VISIBLE);
            } else {
                tvIsfaceinput.setVisibility(View.GONE);
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return rowsBeans.size() == 0 ? 0 : rowsBeans.size() + 1;
    }

}
