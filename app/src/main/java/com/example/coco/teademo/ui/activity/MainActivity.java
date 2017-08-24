package com.example.coco.teademo.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.HandlerThread;
import android.widget.Toast;

import com.example.coco.teademo.R;
import com.example.coco.teademo.utils.LocationUtils;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

public class MainActivity extends Activity implements TencentLocationListener {
    private static final String TAG = "MainActivity";
    private TencentLocationRequest request;
    private TencentLocationManager manager;
    HandlerThread thread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLocation();
        LocationUtils.getLL(this);
    }




    private void initLocation() {
        request = TencentLocationRequest.create();
        request.setInterval(5000);
        request.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_NAME);
        request.setAllowCache(true);
        manager = TencentLocationManager.getInstance(this);

    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        if (TencentLocation.ERROR_OK == i) {
            // 定位成功
            String name = tencentLocation.getName();
            String address = tencentLocation.getAddress();
            Toast.makeText(this, "" + name + ">>>>" + address, Toast.LENGTH_SHORT).show();

        } else {
            // 定位失败
        }

    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {
        Toast.makeText(this, "开启了/关闭了gps", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };

            if (checkSelfPermission(permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, 0);
            }
        } else {
            int error = manager.requestLocationUpdates(request, this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        manager.removeUpdates(this);
    }
}
