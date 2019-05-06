package com.zhhl.concern.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;


public class PaletteUtils {

    public static int rgb(Palette palette) {
        return parse(palette).rgb;
    }

    public static int bodyColor(Palette palette) {
        return parse(palette).bodyColor;
    }

    public static int titleColor(Palette palette) {
        return parse(palette).titleColor;
    }

    public static PaletteObject parse(Palette palette) {
        PaletteObject paletteObject = new PaletteObject();
        Palette.Swatch vibrant = palette.getVibrantSwatch();
        if (vibrant == null) {
            vibrant = palette.getMutedSwatch();
        }
        paletteObject.rgb = vibrant.getRgb();
        paletteObject.bodyColor = vibrant.getBodyTextColor();
        paletteObject.titleColor = vibrant.getTitleTextColor();
        return paletteObject;
    }

    public static Palette parse(Resources resources, int resid) {
        Bitmap bitmap = BitmapFactory.decodeResource(resources, resid);
        return Palette.from(bitmap).generate();
    }

    public static class PaletteObject {
        public int rgb;
        public int bodyColor;
        public int titleColor;


    }
}