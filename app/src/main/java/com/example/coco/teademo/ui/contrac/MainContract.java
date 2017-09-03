package com.example.coco.teademo.ui.contrac;

import android.app.Activity;

import com.example.coco.teademo.base.BasePresenter;
import com.tencent.map.geolocation.TencentLocation;

/**
 * Created by coco on 2017/8/29.
 */

public interface MainContract {
    interface MainView{
        //定义逻辑
        void setActionBar(Activity activity);
        void setStatusBar(Activity activity);
        void setLocationText(String address);
    }
    interface MainPresenter extends BasePresenter{
        void getLocation(TencentLocation location);
        void cancleLocation();
    }

}
