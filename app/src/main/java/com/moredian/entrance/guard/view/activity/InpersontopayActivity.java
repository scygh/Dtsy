package com.moredian.entrance.guard.view.activity;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moredian.entrance.guard.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InpersontopayActivity extends BaseActivity {

    @BindView(R.id.Manualconsumption_back)
    ImageView ManualconsumptionBack;
    @BindView(R.id.page_name)
    TextView pageName;
    @BindView(R.id.root)
    LinearLayout root;
    private int x;
    private int y;


    public static Intent getInpersontopayActivityIntent(Context context, LinearLayout mainLl3) {
        Intent intent = new Intent(context, InpersontopayActivity.class);
        int[] vLocation = new int[2];
        mainLl3.getLocationOnScreen(vLocation);
        int centerX = vLocation[0] + mainLl3.getMeasuredWidth() / 2;
        int centerY = vLocation[1] + mainLl3.getMeasuredHeight() / 2;
        intent.putExtra("x", centerX);
        intent.putExtra("y", centerY);
        return intent;
    }

    @Override
    public int layoutView() {
        return R.layout.activity_inpersontopay;
    }

    @Override
    public void initView() {
        pageName.setText("时段定额");
    }

    @Override
    public void initData() {
        x = getIntent().getIntExtra("x", 0);
        y = getIntent().getIntExtra("y", 0);

        root.post(new Runnable() {
            @Override
            public void run() {
                createCircleReveal(x, y).start();
            }
        });
    }

    @TargetApi(21)
    private Animator createCircleReveal(int x, int y) {
        float radius = (float) Math.hypot(root.getHeight(), root.getWidth());
        Animator animator = ViewAnimationUtils.createCircularReveal(root, x, y, 0, radius);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        return animator;
    }

    @TargetApi(21)
    private Animator closeCircleReveal(int x, int y) {
        float radius = (float) Math.hypot(root.getHeight(), root.getWidth());
        Animator animator = ViewAnimationUtils.createCircularReveal(root, x, y, radius,0);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        return animator;
    }

    @OnClick({R.id.Manualconsumption_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Manualconsumption_back:
                //closeCircleReveal(x,y).start();
                finish();
                break;
        }
    }

}
