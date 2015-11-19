package com.greenlionsoft.pollution.madrid.tools;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

public final class ScreenUtil {


    //As we support old devices we need to define here
    //all density constants
    public static final int LDPI = 120;
    public static final int MDPI = 160;
    public static final int HDPI = 240;
    public static final int XHDPI = 320;
    public static final int XXHDPI = 480;
    public static final int XXXHDPI = 640;
    public static final int TVDPI = 213;

    /**
     * Hidden constructor for utility Class
     */
    private ScreenUtil() {
    }


    /**
     * @param context
     * @return int value
     */
    public static int getScreenDensity(Context context) {

        return context.getResources().getDisplayMetrics().densityDpi;
    }

    /**
     * Universal function to get screen Width in Pixels
     *
     * @param context
     * @return int pixels
     */
    @SuppressWarnings("deprecation")
    public static int getScreenWidthInPixels(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            //New way
            Point size = new Point();
            display.getSize(size);
            return size.x;

        } else {
            //old deprecated way
            return display.getWidth();
        }
    }

    /**
     * Universal function to get screen Height in Pixels
     *
     * @param context
     * @return int pixels
     */
    @SuppressWarnings("deprecation")
    public static int getScreenHeightInPixels(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            //New way
            Point size = new Point();
            display.getSize(size);
            return size.y;

        } else {
            //old deprecated way
            return display.getHeight();
        }
    }

    public static int pixelsToDips(Context context, int pixels) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pixels / scale + 0.5f);
    }

    public static int dipsToPixels(Context context, int dips) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dips, context.getResources().getDisplayMetrics());
    }

}
