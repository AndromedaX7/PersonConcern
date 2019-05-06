package com.zhhl.concern.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by miao on 2018/9/4.
 */
public class BitmapUtil {

    public static Bitmap chamferBitmap(Bitmap src, float index) {
        int width = src.getWidth();
        int height = src.getHeight();
        int radius = Math.min(width, height);
        Bitmap dst = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.RGB_565);
        Rect rect = new Rect(0, 0, src.getWidth(), src.getHeight());
        RectF rectf = new RectF(rect);
        Canvas canvas = new Canvas(dst);
        canvas.drawARGB(0, 0, 0, 0);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
//        Paint dstPaint =new Paint();
//        dstPaint.setAntiAlias(true);
//        dstPaint.setColor(Color.WHITE);
//


        canvas.drawRoundRect(rectf, index, index, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));//srcAtop
        canvas.drawBitmap(src, rect, rect, paint);

        return dst;
    }
}
