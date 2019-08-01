package com.moredian.entrance.guard.face.drawface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;

import com.moredian.entrance.guard.R;
import com.moredian.entrance.guard.face.CameraView;


/**
 * Created by leon on 12/18/17.
 */

public class FaceDefaultDrawer extends BaseFaceDrawer {

    private final RectF mFaceRect = new RectF();
    private Paint mFacePaint = new Paint();
    private Bitmap mFaceBitmap;

    public FaceDefaultDrawer(Context context) {
        super(context);
        mFaceBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sign);
    }

    private Matrix mMatrix = new Matrix();
    public static void prepareMatrix(Matrix matrix, boolean mirror, int displayOrientation,
                                     int viewWidth, int viewHeight) {
        matrix.setScale(mirror ? -1 : 1, 1);
        matrix.postRotate(displayOrientation);
        matrix.postScale(viewWidth / 2000f, viewHeight / 2000f);
        matrix.postTranslate(viewWidth /2f , viewHeight /2f);
    }

    @Override
    public void drawFace(final Canvas canvas, final Camera.Face[] faces) {
        if (faces != null && faces.length > 0) {
            int width = CameraView.mVideoPreviewWidth;
            int height = CameraView.mVideoPreviewHeight;
            prepareMatrix(mMatrix, false, 0, width, height);
            mMatrix.postRotate(0);

            for (Camera.Face face : faces) {
                Rect rect = face.rect;
                mFaceRect.set(rect);
                mMatrix.mapRect(mFaceRect);
                canvas.drawBitmap(mFaceBitmap,null, mFaceRect, mFacePaint);
            }
        }
    }
}
