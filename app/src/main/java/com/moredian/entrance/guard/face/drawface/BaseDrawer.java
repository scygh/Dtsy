package com.moredian.entrance.guard.face.drawface;

import android.content.Context;

/**
 * Created by leon on 12/6/17.
 */

public abstract class BaseDrawer implements Drawer {

    private final DrawerSurface mDrawerSurface;

    public BaseDrawer(DrawerSurface drawerSurface) {
        mDrawerSurface = drawerSurface;
    }

    @Override
    public void invalidate() {
        if(mDrawerSurface != null) {
            mDrawerSurface.requestInvalidate();
        }
    }

    @Override
    public Context getContext() {
        if(mDrawerSurface != null) {
            return mDrawerSurface.getContext();
        }
        return null;
    }

    @Override
    public int getWidth() {
        if(mDrawerSurface != null) {
            return mDrawerSurface.getSurfaceWidth();
        }
        return 0;
    }

    @Override
    public int getHeight() {
        if(mDrawerSurface != null) {
            return mDrawerSurface.getSurfaceHeight();
        }
        return 0;
    }

    @Override
    public int getDrawGapTime() {
        return DEFAULT_DRAWER_GAP_TIME;
    }
}
