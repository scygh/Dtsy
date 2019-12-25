package com.moredian.entrance.guard.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.entity.GetExpensePage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description ：NetSettingRvAdapter
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/7/29 08:46
 */
public class ConsumeRecordRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_OVER = 2;
    private Context context;
    List<GetExpensePage.ContentBean.RowsBean> rowsBeans;
    private OnMyItemClickListener myItemClickListener;

    public interface OnMyItemClickListener {
        void onItemClick(int position);
    }

    public void setMyItemClickListener(OnMyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    public ConsumeRecordRvAdapter(Context context, List<GetExpensePage.ContentBean.RowsBean> rowsBeans) {
        this.context = context;
        this.rowsBeans = rowsBeans;
    }

    public class FootViewHolder extends RecyclerView.ViewHolder {
        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.record_date)
        TextView recordDate;
        @BindView(R.id.record_money)
        TextView recordMoney;
        @BindView(R.id.record_des)
        TextView recordDes;
        @BindView(R.id.record_detail)
        RelativeLayout recordDetail;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            recordDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }

        public void bind() {
            recordDate.setText(rowsBeans.get(getAdapterPosition()).getTradeDateTime());
            recordMoney.setText("+" + String.valueOf(rowsBeans.get(getAdapterPosition()).getAmount()));
            recordDes.setText( rowsBeans.get(getAdapterPosition()).getDescription());
        }
    }

    public class OverViewHolder extends RecyclerView.ViewHolder {
        public OverViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.expense_page_item, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_foot, parent, false);
            return new FootViewHolder(view);
        }else if (viewType == TYPE_OVER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_over, parent, false);
            return new OverViewHolder(view);
        }
        return null;
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

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            if (getItemCount() <= 4) {
                return TYPE_OVER;
            } else {
                return TYPE_FOOTER;
            }
        } else {
            return TYPE_ITEM;
        }
    }
}
