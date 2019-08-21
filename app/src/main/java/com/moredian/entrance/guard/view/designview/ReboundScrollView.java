package com.moredian.entrance.guard.view.designview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;


public class ReboundScrollView extends ScrollView {

    @SuppressWarnings("unused")
    private static final String TAG = "ReboundScrollView";
    //移动的延迟距离因子,手如果滑动100 ，实际滑动60
    private static final float MOVE_FACTOR = 0.6f;
    //动画执行的时间，也就是恢复正常的时间
    private static final int ANIM_TIME = 400;
    //ScrollView的唯一子view
    private View contentView;
    //手指触摸起始y坐标
    private float startY;
    //用来保存原始位置
    private Rect originalRect = new Rect();
    //表示是否正在移动
    private boolean isMoved = false;

    public ReboundScrollView(Context context) {
        super(context);
    }

    public ReboundScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReboundScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * descirption: 当我们的XML布局被加载完后，就会回调onFinshInfalte这个方法，在这个方法中我们可以初始化控件和数据。
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            contentView = getChildAt(0);
        }
    }

    /**
     * descirption: 在测量完成之后，布局的时候调用此方法，去保存原始位置
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (contentView == null)
            return;
        //保存原始位置
        originalRect.set(contentView.getLeft(), contentView.getTop(), contentView.getRight(), contentView.getBottom());
    }


    /**
     * descirption: 事件分发的方法
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (contentView == null) {
            return super.dispatchTouchEvent(ev);
        }
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: ACTION_DOWN");
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent: ACTION_MOVE");
                if (!isCanPullDown() && !isCanPullUp()) {
                    startY = ev.getY();
                    break;
                }
                float nowY = ev.getY();
                int deltaY = (int) (nowY - startY);
                boolean shouldMove = (isCanPullDown() && deltaY > 0)
                        || (isCanPullUp() && deltaY < 0)
                        || (isCanPullDown() && isCanPullUp());
                if (shouldMove) {
                    int offset = (int) (deltaY * MOVE_FACTOR);
                    contentView.layout(originalRect.left, originalRect.top + offset, originalRect.right,
                            originalRect.bottom + offset);
                    isMoved = true;
                }
                break;
            case MotionEvent.ACTION_UP: //松手就恢复原来的位置\
                Log.d(TAG, "dispatchTouchEvent: ACTION_UP");
                if (!isMoved)
                    break;
                TranslateAnimation anim = new TranslateAnimation(0, 0, contentView.getTop(), originalRect.top);
                anim.setDuration(ANIM_TIME);
                anim.setInterpolator(new DecelerateInterpolator());
                contentView.startAnimation(anim);
                contentView.layout(originalRect.left, originalRect.top, originalRect.right, originalRect.bottom);
                isMoved = false;
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isCanPullDown() {
        return getScrollY() == 0 || contentView.getHeight() <= getHeight() + getScrollY();
    }

    private boolean isCanPullUp() {
        return contentView.getHeight() <= getHeight() + getScrollY();
    }
}