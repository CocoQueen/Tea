package com.example.coco.teademo.utils;

import android.content.Context;

/**
 * Created by coco on 2017/8/29.
 */

public class MeasureUtils {
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = context.getResources().getDimensionPixelSize(resId);
        }
        return result;
    }
}
