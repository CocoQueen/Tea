package com.example.coco.teademo.ui.contrac;

import com.example.coco.teademo.ui.activity.MainActivity;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by coco on 2017/8/29.
 */

public class MainPresenterImpl implements MainContract.MainPresenter {
    public MainContract.MainView view;
    private MainActivity activity;
    private TencentLocationManager locationManager;
    private TencentLocationListener listener;

    public MainPresenterImpl(MainContract.MainView view) {
        this.view = view;
        activity = (MainActivity) view;
    }

    @Override
    public void getLocation(TencentLocation location) {
        listener = new TencentLocationListener() {
            @Override
            public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
                double longitude = tencentLocation.getLongitude();
                double latitude = tencentLocation.getLatitude();
                view.setLocationText(tencentLocation.getName());
                Map<Double, Double> map = new HashMap<>();
                map.put(latitude, longitude);
                EventBus.getDefault().post(map);
                cancleLocation();
            }

            @Override
            public void onStatusUpdate(String s, int i, String s1) {

            }
        };
        TencentLocationRequest request = TencentLocationRequest.create();
        locationManager = TencentLocationManager.getInstance(activity);
        locationManager.requestLocationUpdates(request, listener);

    }

    @Override
    public void cancleLocation() {
        locationManager.removeUpdates(listener);
    }
}
