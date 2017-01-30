package com.patrickz.bluetoothspy;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class Simple
{
    public static void showOkDialog(Context context, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(message);
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                //do things
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
