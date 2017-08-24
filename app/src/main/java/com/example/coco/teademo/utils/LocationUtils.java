package com.example.coco.teademo.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by coco on 2017/8/15.
 */

public class LocationUtils{
    private static final String TAG = "LocationUtils";

    public static HashMap<String, String> getLL(final Activity activity) {
        LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = manager.getAllProviders();
        for (String str :
                providers) {
            Log.e(TAG, "getLL: " + str);
        }
        Criteria criteria = new Criteria();
        String bestProvider = manager.getBestProvider(criteria, false);
        Log.e(TAG, "getLL: best" + bestProvider);

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        manager.requestLocationUpdates(bestProvider, 0, 0.5f, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                Log.e(TAG, "listener "+latitude+"--"+longitude );
                Toast.makeText(activity, "listener "+latitude+"--"+longitude, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });

        return null;
    }

}
