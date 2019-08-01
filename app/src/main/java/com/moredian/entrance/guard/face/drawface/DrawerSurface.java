package com.moredian.entrance.guard.face.drawface;

import android.content.Context;

/**
 * Created by leon on 12/6/17.
 */

public interface DrawerSurface {
    Context getContext();

    void requestInvalidate();

    int getSurfaceWidth();

    int getSurfaceHeight();
}
