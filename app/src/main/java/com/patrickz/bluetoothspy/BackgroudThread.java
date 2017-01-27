package com.patrickz.bluetoothspy;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by patrick on 25/01/17.
 */

public class BackgroudThread extends Thread
{
    private final static String LOGTAG = "MainBackgroudThread";

    private Context context = null;
    private int count = 0;

    public BackgroudThread(Looper looper, Context context)
    {
//        super(looper);

        this.context = context;
    }

    @Override
    public void run()
    {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.

        Log.d(LOGTAG, "Thread Hallo " + count);
        Toast.makeText(context, "Thread Hallo " + count, Toast.LENGTH_LONG).show();
        count++;

        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }
    }
}
