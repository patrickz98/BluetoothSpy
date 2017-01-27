package com.patrickz.bluetoothspy;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import org.json.simple.JSONObject;

import static android.content.Context.LOCATION_SERVICE;

public class Tracker
{
    private final static String LOGTAG = "MainLocation";
    private Context context;

    public static JSONObject json = new JSONObject();

    public Tracker(Context context)
    {
        this.context = context;

        locate();
    }

    private void buildJson(Location location)
    {
        double latitude  = location.getLatitude();
        double longitude = location.getLongitude();

//        Log.d(LOGTAG, "Latitude:  " + latitude);
//        Log.d(LOGTAG, "Longitude: " + longitude);

        json.put("Latitude",  latitude);
        json.put("Longitude", longitude);

        json.put("Accuracy", location.getAccuracy());
        json.put("Speed", location.getSpeed());
        json.put("Time", location.getTime());

        Log.d(LOGTAG, json.toString(2));
    }

    private void locate()
    {
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener()
        {
            @Override
            public void onLocationChanged(Location location)
            {
                // Called when a new location is found by the network location provider.

                buildJson(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras)
            {
                Log.d(LOGTAG, "onStatusChanged --> provider: " + provider);
            }

            @Override
            public void onProviderEnabled(String provider)
            {
                Log.d(LOGTAG, "onProviderEnabled --> provider: " + provider);
            }

            @Override
            public void onProviderDisabled(String provider)
            {
                Log.d(LOGTAG, "onProviderDisabled --> provider: " + provider);
            }
        };

        try
        {
            // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
        catch (SecurityException exc)
        {
            Simple.showOkDialog(context, "Location permission not granted.");
        }
    }
}
