package com.patrickz.bluetoothspy;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import org.json.JSONObject;

public class BluetoothSnitch
{
    public static JSONObject data = new JSONObject();


    private final static String LOGTAG = "MainBluetoothSnitch";

//    private final BroadcastReceiver mReceiver = new BroadcastReceiver()
//    {
//        @Override
//        public void onReceive(Context context, Intent intent)
//        {
//            String action = intent.getAction();
//
//            if (BluetoothDevice.ACTION_FOUND.equals(action))
//            {
//                // Discovery has found a device. Get the BluetoothDevice
//                // object and its info from the Intent.
//                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                String deviceName = device.getName();
//                String deviceHardwareAddress = device.getAddress(); // MAC address
//
//                Log.d(LOGTAG, "deviceName:            " + deviceName);
//                Log.d(LOGTAG, "deviceHardwareAddress: " + deviceHardwareAddress);
//            }
//        }
//    };

    private final BroadcastReceiver mReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();

            Log.d(LOGTAG, action);

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action))
            {
                //discovery starts, we can show progress dialog or perform other tasks
            }

            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
            {
                //discovery finishes, dismis progress dialog
            }

            if (BluetoothDevice.ACTION_FOUND.equals(action))
            {
                //bluetooth device found
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                Log.d(LOGTAG, "deviceName: " + device.getName());
                SimpleJson.put(data, device.getName(), device.getAddress());
            }
        }
    };

    BluetoothSnitch(MainService service)
    {
        Log.d(LOGTAG, "BluetoothSnitch");

//        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//        service.registerReceiver(mReceiver, filter);

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        service.registerReceiver(mReceiver, filter);
        adapter.startDiscovery();
    }

    BluetoothSnitch(MainActivity activity)
    {
        Log.d(LOGTAG, "BluetoothSnitch");

//        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//        service.registerReceiver(mReceiver, filter);

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        activity.registerReceiver(mReceiver, filter);
        adapter.startDiscovery();
    }
}
