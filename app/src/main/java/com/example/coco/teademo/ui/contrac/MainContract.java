package com.example.coco.teademo.ui.contrac;

import android.app.Activity;

import com.example.coco.teademo.base.BasePresenter;

/**
 * Created by coco on 2017/8/29.
 */

public interface MainContract {
    interface MainView{
        void setActionBar(Activity activity);
        void setStatusBar(Activity activity);
        void setLocationText(String address);
    }
    interface MainPresenter extends BasePresenter{
        void setLocation();
    }

}
