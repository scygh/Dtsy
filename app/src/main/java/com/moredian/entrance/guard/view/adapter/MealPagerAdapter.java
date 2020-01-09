package com.moredian.entrance.guard.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    @BindView(R.id.meal_PreferentialAmount)
    TextView mealPreferentialAmount;
    @BindView(R.id.meal_MealQuota)
    TextView mealMealQuota;
    @BindView(R.id.meal_MealTimes)
    TextView mealMealTimes;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.ll_3)
    LinearLayout ll3;
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
        mealName.setText(data.get(position).getName());
        if (data.get(position).getName().equals("早餐")) {
            mealIcon.setImageResource(R.mipmap.zaocan);
            rl.setBackgroundColor(context.getResources().getColor(R.color.color_fde3a8));
        } else if (data.get(position).getName().equals("中餐")) {
            mealIcon.setImageResource(R.mipmap.wucan);
            rl.setBackgroundColor(context.getResources().getColor(R.color.color_f6d76d));
        } else if (data.get(position).getName().equals("晚餐")) {
            mealIcon.setImageResource(R.mipmap.wancan);
            rl.setBackgroundColor(context.getResources().getColor(R.color.color_f27835));
        } else if (data.get(position).getName().equals("夜宵")) {
            mealIcon.setImageResource(R.mipmap.yexiao);
            rl.setBackgroundColor(context.getResources().getColor(R.color.color_4b77be));
        }

        mealStartTime.setText(data.get(position).getBeginTime());
        mealEndTime.setText(data.get(position).getEndTime());
        container.addView(view);
        view.setTag(R.id.tag_pos, position);
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

    private int mCurrentPosition;

    @Override
    public int getItemPosition(@NonNull Object object) {
        View view = (View) object;
        Object o = view.getTag(R.id.tag_pos);
        if (o != null) {
            final int curPos = (Integer) o;
            //只刷新当前页面
            if (mCurrentPosition != curPos) {
                return POSITION_UNCHANGED;
            }
        }
        return POSITION_NONE;
    }

    public void setCurrentPosition(int mCurrentPosition) {
        this.mCurrentPosition = mCurrentPosition;
    }
}

