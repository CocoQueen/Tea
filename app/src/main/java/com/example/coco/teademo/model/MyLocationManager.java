package com.example.coco.teademo.model;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

/**
 * Created by coco on 2017/8/29.
 * 与腾讯地图定位有关
 */

public class MyLocationManager {
    private static MyLocationManager locationManager;
    public static Activity act;
    private static final String TAG = "LocationManager";
    private TencentLocationRequest request;
    private TencentLocationManager manager;

    public MyLocationManager(Activity activity) {
        this.act = activity;
    }

    public static MyLocationManager getInstance(Activity activity) {
        if (locationManager == null) {
            locationManager = new MyLocationManager(activity);
        }
        return locationManager;
    }

    public static class LocationListener implements TencentLocationListener {

        @Override
        public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
            if (TencentLocation.ERROR_OK == i) {
                // 定位成功
                String name = tencentLocation.getName();
                String address = tencentLocation.getAddress();
                Log.e(TAG, "onLocationChanged: " + name + "--" + address);

            } else {
                // 定位失败
            }
        }


        @Override
        public void onStatusUpdate(String s, int i, String s1) {
            Log.e(TAG, "onStatusUpdate: gps开启了||关闭了");
        }
    }

    private void initLocation() {
        request = TencentLocationRequest.create();
        request.setInterval(5000);
        request.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_NAME);
        request.setAllowCache(true);
        manager = TencentLocationManager.getInstance(act);

    }

    private void locate() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };

            if (act.checkSelfPermission(permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                act.requestPermissions(permissions, 0);
            }
        } else {
            int error = manager.requestLocationUpdates(request, new LocationListener());
        }
    }
}
