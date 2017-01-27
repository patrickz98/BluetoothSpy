package com.patrickz.bluetoothspy;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MainService extends Service
{
    private final static String LOGTAG = "MainService";
    private boolean mRunning;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mRunning = false;

//        BluetoothSnitch snitch = new BluetoothSnitch(this);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
        Log.d(LOGTAG, "onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        if (mRunning) return super.onStartCommand(intent, flags, startId);

        mRunning = true;

        Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
        Log.d(LOGTAG, "onStart");

        Tracker location = new Tracker(this.getBaseContext());

//        testThread();

        return super.onStartCommand(intent, flags, startId);
    }

    private void testThread()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int count = 0;
                while (true)
                {
                    Log.d(LOGTAG, "test: " + count);
                    count++;

                    try
                    {
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e)
                    {
                        // Restore interrupt status.
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();
    }
}