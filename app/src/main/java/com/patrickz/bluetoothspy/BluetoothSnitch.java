package com.patrickz.bluetoothspy;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.ParcelUuid;
import android.util.Log;

import org.json.simple.JSONObject;

public class BluetoothSnitch
{
    public static JSONObject data = new JSONObject();
    public static boolean scanAtive = false;

    private Context context;
    private final static String LOGTAG = "MainBluetoothSnitch";

    private final BroadcastReceiver mReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();

            // Log.d(LOGTAG, action);

            if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED))
            {
                scanAtive = true;
            }

            if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED))
            {
                scanAtive = false;
            }

            if (action.equals(BluetoothDevice.ACTION_FOUND))
            {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

//                ParcelUuid[] uuid = device.getUuids();

                Log.d(LOGTAG, "deviceName: " + device.getName());
                data.put(device.getName(), device.getAddress());
            }
        }
    };

    public void start()
    {
        Log.d(LOGTAG, "start scan");

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        context.registerReceiver(mReceiver, filter);
        adapter.startDiscovery();
    }

    BluetoothSnitch(Context context)
    {
        Log.d(LOGTAG, "BluetoothSnitch");
        this.context = context;
    }
}
