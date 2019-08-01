package com.moredian.entrance.guard.face.drawface;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.Camera;

/**
 * Created by leon on 12/18/17.
 */

public abstract class BaseFaceDrawer {

    public BaseFaceDrawer(Context context) {

    }

    public void updateFace(Camera.Face[] faces) {}

    public abstract void drawFace(Canvas canvas, Camera.Face[] faces);
}
