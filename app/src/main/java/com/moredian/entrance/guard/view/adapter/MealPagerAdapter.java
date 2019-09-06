package com.moredian.entrance.guard.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.entity.GetMealList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/9/4 10:59
 */
public class MealPagerAdapter extends PagerAdapter {

    @BindView(R.id.meal_name)
    TextView mealName;
    @BindView(R.id.meal_icon)
    ImageView mealIcon;
    @BindView(R.id.meal_startTime)
    TextView mealStartTime;
    @BindView(R.id.meal_endTime)
    TextView mealEndTime;
    @BindView(R.id.meal_price)
    TextView mealPrice;
    @BindView(R.id.rl)
    RelativeLayout rl;
    private List<GetMealList.ContentBean> data;
    private Context context;

    public MealPagerAdapter(List<GetMealList.ContentBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_cardview_layout, null);
        ButterKnife.bind(this, view);
        mealName.setText(data.get(position).getMeal().getName());
        mealPrice.setText(data.get(position).getDeviceMeal().getMealAmount() + "");
        if (data.get(position).getMeal().getName().equals("早餐")) {
            mealIcon.setImageResource(R.mipmap.zaocan);
            rl.setBackgroundColor(context.getResources().getColor(R.color.color_fde3a8));
        } else if (data.get(position).getMeal().getName().equals("中餐")) {
            mealIcon.setImageResource(R.mipmap.wucan);
            rl.setBackgroundColor(context.getResources().getColor(R.color.color_f6d76d));
        } else if (data.get(position).getMeal().getName().equals("晚餐")) {
            mealIcon.setImageResource(R.mipmap.wancan);
            rl.setBackgroundColor(context.getResources().getColor(R.color.color_f27835));
        } else if (data.get(position).getMeal().getName().equals("夜宵")) {
            mealIcon.setImageResource(R.mipmap.yexiao);
            rl.setBackgroundColor(context.getResources().getColor(R.color.color_4b77be));
        }
        mealStartTime.setText(data.get(position).getMeal().getBeginTime());
        mealEndTime.setText(data.get(position).getMeal().getEndTime());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}

