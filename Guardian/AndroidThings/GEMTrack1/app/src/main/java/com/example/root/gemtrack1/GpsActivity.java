package com.example.root.gemtrack1;

/**
 * Created by root on 3/7/17.
 */

import android.app.Activity;
import android.location.LocationManager;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.contrib.driver.gps.NmeaGpsDriver;

import java.io.IOException;

public class GpsActivity extends Activity {
    private static final String TAG = "GpsActivity";

    Location location;
    double latitude; // latitude
    double longitude; // longitude

    // flag for GPS status
    public boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    // The minimum distance to change Updates in meters
    private static final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = (1/10); // 1/10 th of a minute = 6 seconds

    public static final int UART_BAUD = 9600;
    public static final float ACCURACY = 2.5f; // From GPS datasheet

    private LocationManager mLocationManager;
    private NmeaGpsDriver mGpsDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // getting GPS status
        isGPSEnabled = mLocationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.v("isGPSEnabled", "=" + isGPSEnabled);

        // getting network status
        isNetworkEnabled = mLocationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Log.v("isNetworkEnabled", "=" + isNetworkEnabled);


        // We need permission to get location updates
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // A problem occurred auto-granting the permission
            Log.d(TAG, "No permission");
            return;
        }
        else{
            Log.d(TAG, "GPS Activity-permission allowed");
        }

        if (!isGPSEnabled == false || !isNetworkEnabled == false) {
            if (isNetworkEnabled) {
                location=null;
                mLocationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, mLocationListener);
                Log.d("Network", "Network");
                if (mLocationManager != null) {
                    location = mLocationManager
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        Log.d(TAG, "Latitude"+ String.valueOf(latitude));
                        Log.d(TAG, "Longitude"+ String.valueOf(latitude));
                    }
                }
            }
            if (isGPSEnabled){
                try {
                    // Register the GPS driver
                    Log.d(TAG,"GPS registration began...1");
                    mGpsDriver = new NmeaGpsDriver(this, "UART0", UART_BAUD, ACCURACY);
                    mGpsDriver.register();
                    Log.d(TAG,"GPS registration began...2");
                    // Register for location updates
                    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            0, 0, mLocationListener);
                    if (mLocationManager != null) {
                        location = mLocationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                        Log.d(TAG, "Latitude"+ Double.toString(latitude));
                        Log.d(TAG, "Longitude"+ Double.toString(latitude));
                    }
                } catch (IOException e) {
                    Log.w(TAG, "Unable to open GPS UART", e);
                }
            }

        }
        else{
            Log.d(TAG, "ERROR-CAN'T ACCESS NETWORK OR GPS ");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Verify permission was granted
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "No permission");
            return;
        }

        if (mGpsDriver != null) {
            // Unregister components
            mGpsDriver.unregister();
            mLocationManager.removeUpdates(mLocationListener);

            try {
                mGpsDriver.close();
            } catch (IOException e) {
                Log.w(TAG, "Unable to close GPS driver", e);
            }
        }
    }

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.v(TAG, "Location update: " + location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) { }

        @Override
        public void onProviderEnabled(String provider) { }

        @Override
        public void onProviderDisabled(String provider) { }
    };
}
