package com.patrickz.bluetoothspy;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by patrick on 26/01/17.
 */

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
}