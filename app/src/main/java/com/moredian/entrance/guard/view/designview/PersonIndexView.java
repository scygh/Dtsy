package com.moredian.entrance.guard.view.designview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.moredian.entrance.guard.R;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/8/22 14:30
 */
public class PersonIndexView extends View {

    private String[] indexArr = {"A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z", "#"};

    private Paint paint;
    private Paint Circlepaint;
    private int width;//控件宽度
    private float latticeHeight;//将控件等分每个部分的高度
    private int lastIndex = -1;//上一次点击字母记录的索引

    public PersonIndexView(Context context) {
        super(context);
        init();
    }

    public PersonIndexView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PersonIndexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //初始化画笔参数
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);//设置抗锯齿
        Circlepaint = new Paint(Paint.ANTI_ALIAS_FLAG);//设置抗锯齿
        paint.setTextSize(20);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.GRAY);
        Circlepaint.setColor(Color.WHITE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        latticeHeight = getMeasuredHeight() * 1f / indexArr.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < indexArr.length; i++) {
            float x = width / 2;
            float y = latticeHeight / 2 + getTextHeight(indexArr[i]) / 2 + i * latticeHeight;
            paint.setColor(lastIndex == i ? getResources().getColor(R.color.colorPrimaryDark) : Color.GRAY);
            Circlepaint.setColor(lastIndex == i ? getResources().getColor(R.color.colorPrimary) : Color.WHITE);
            canvas.drawCircle(x, y, 13.0f, Circlepaint);
            canvas.drawText(indexArr[i], x, y + 7, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (listener != null) {
                    listener.onActionDown();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                int index = (int) (y / latticeHeight);
                if (lastIndex != index) {
                    if (index >= 0 && index < indexArr.length) {
                        if (listener != null) {
                            listener.onTouchIndex(indexArr[index]);
                        }
                    }
                }
                lastIndex = index;
                break;
            case MotionEvent.ACTION_UP:
                lastIndex = -1;
                break;
        }
        invalidate();
        return true;
    }


    /**
     * 获取文本的高度
     *
     * @param text 需要获取高度的文本
     * @return 文本的高度
     */
    private int getTextHeight(String text) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();
    }

    //回调监听，把点击的字母暴露给使用者

    private onTouchIndexListener listener;

    public void setOnTouchIndexListener(onTouchIndexListener listener) {
        this.listener = listener;
    }

    public interface onTouchIndexListener {
        void onTouchIndex(String text);

        void onActionDown();
    }
}
