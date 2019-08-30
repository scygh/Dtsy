package com.moredian.entrance.guard.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
public class PersonManageRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private Context context;
    List<GetListByPage.ContentBean.RowsBean> rowsBeans;
    private OnMyItemClickListener myItemClickListener;

    public interface OnMyItemClickListener {
        void onItemClick(String userid);
    }

    public void setMyItemClickListener(OnMyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    public PersonManageRvAdapter(Context context, List<GetListByPage.ContentBean.RowsBean> rowsBeans) {
        this.context = context;
        this.rowsBeans = rowsBeans;
    }

    public class FootViewHolder extends RecyclerView.ViewHolder {
        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_wifiname)
        TextView tvUsername;
        @BindView(R.id.tv_user_state)
        TextView tvUserstate;
        @BindView(R.id.tv_isfaceinput)
        TextView tvIsfaceinput;
        @BindView(R.id.rl)
        RelativeLayout relativeLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myItemClickListener.onItemClick(rowsBeans.get(getAdapterPosition()).getUser().getId());
                }
            });
        }

        public void bind() {
            String name = rowsBeans.get(getAdapterPosition()).getUser().getName();
            tvUsername.setText(name);
            int state = rowsBeans.get(getAdapterPosition()).getUser().getState();
            if (state == 3) {
                tvUserstate.setText("已销户");
            } else {
                tvUserstate.setText("正常");
            }
            if (rowsBeans.get(getAdapterPosition()).getUserFace().getMemberFace()!= null) {
                tvIsfaceinput.setVisibility(View.VISIBLE);
            } else {
                tvIsfaceinput.setVisibility(View.GONE);
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.person_item, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_foot, parent,
                    false);
            return new FootViewHolder(view);
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
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }
}
