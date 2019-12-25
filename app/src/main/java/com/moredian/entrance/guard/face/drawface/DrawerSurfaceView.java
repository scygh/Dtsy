package com.moredian.entrance.guard.face.drawface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.hardware.Camera;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjmantou on 2017/9/14.
 */

public class DrawerSurfaceView extends SurfaceView implements SurfaceHolder.Callback, DrawerSurface, Handler.Callback {

    private static final int MSG_UPDATE = 0;
    private static final int MSG_DELAY_UPDATE = 1;

    private HandlerThread mDrawThread;
    private Handler mDrawHandler;

    private SurfaceHolder mSurface;
    private FaceMaskDrawer faceMaskDrawer;

    private int mSurfaceWidth;
    private int mSurfaceHeight;

    private final List<Drawer> mDrawers = new ArrayList<>(4);

    public DrawerSurfaceView(Context context) {
        this(context, null);
    }

    public DrawerSurfaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        faceMaskDrawer = new FaceMaskDrawer(this);
        mDrawers.add(faceMaskDrawer);
        getHolder().addCallback(this);

        setZOrderOnTop(true);
        setZOrderMediaOverlay(true);
        getHolder().setFormat(PixelFormat.TRANSPARENT);
    }

    @Override
    public void requestInvalidate() {
        notifyDraw();
    }

    @Override
    public int getSurfaceHeight() {
        return mSurfaceHeight;
    }

    @Override
    public int getSurfaceWidth() {
        return mSurfaceWidth;
    }

    public void setFaces(Camera.Face[] faces) {
        if(faceMaskDrawer.setFaces(faces)) {
            notifyDraw();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mSurface = holder;
        mDrawThread = new HandlerThread("drawer_th");
        mDrawThread.setPriority(Thread.NORM_PRIORITY + 1);
        mDrawThread.start();
        mDrawHandler = new Handler(mDrawThread.getLooper(), this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mSurfaceWidth = width;
        mSurfaceHeight = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(mDrawThread != null) {
            mDrawThread.quit();
        }
        mDrawHandler = null;
        mDrawThread = null;
        mSurface = null;
    }

    @Override
    public boolean handleMessage(Message msg) {
        final SurfaceHolder holder = mSurface;
        if(holder == null) {
            return false;
        }
        boolean hasNext = false;
        Canvas canvas =  null;
        int delay = 0;

        try {
            canvas = holder.lockCanvas();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            for(Drawer drawer : mDrawers) {
                if(drawer.isEnable()) {
                    drawer.draw(canvas);

                    if(drawer.isEnable()) {
                        int time = drawer.getDrawGapTime();
                        if(delay == 0 || time < delay) {
                            delay = time;
                        }
                        hasNext = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(canvas != null) {
                try {
                    holder.unlockCanvasAndPost(canvas);
                } catch (Exception e) {
                    e.printStackTrace();                }

            }
        }

        if (hasNext) {
            notifyDraw(delay);
        }
        return true;
    }

    private void notifyDraw() {
        notifyDraw(0);
    }

    private void notifyDraw(int delay) {
        if(mDrawHandler != null) {
            if(delay <= 0) {
                mDrawHandler.removeMessages(MSG_DELAY_UPDATE);
                mDrawHandler.sendEmptyMessage(MSG_UPDATE);
            } else {
                mDrawHandler.sendEmptyMessageDelayed(MSG_DELAY_UPDATE, delay);
            }
        }
    }
}
