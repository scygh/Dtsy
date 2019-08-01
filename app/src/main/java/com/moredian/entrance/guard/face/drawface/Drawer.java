package com.moredian.entrance.guard.face.drawface;

import android.content.Context;
import android.graphics.Canvas;

/**
 * Created by leon on 12/4/17.
 */

public interface Drawer {

    int DEFAULT_DRAWER_GAP_TIME = 40;

    boolean isEnable();

    void draw(Canvas canvas);

    void invalidate();

    Context getContext();

    int getWidth();

    int getHeight();

    int getDrawGapTime();
}
