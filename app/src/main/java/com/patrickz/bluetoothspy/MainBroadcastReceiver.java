package com.patrickz.bluetoothspy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by patrick on 25/01/17.
 */

public class MainBroadcastReceiver extends BroadcastReceiver
{
    private final static String LOGTAG = MainActivity.LOGMARKER + "BroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent arg1)
    {
        Intent intent = new Intent(context, MainService.class);
        context.startService(intent);
        Log.d(LOGTAG, "started");
    }
}