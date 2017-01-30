package com.patrickz.bluetoothspy;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TableRow;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Map;

public class DataManager
{
    private static final String LOGTAG = MainActivity.LOGMARKER + "DataManager";
    private static final String PREFERENCE_BLUETOOTH = "bluetoothData";

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    public DataManager(Context context)
    {
        settings = context.getSharedPreferences(PREFERENCE_BLUETOOTH, Context.MODE_PRIVATE);
        editor   = settings.edit();
    }

    public void add(JSONObject data)
    {
        Log.d(LOGTAG, "add(" + data.toString() + ")");

        for (String macAdr: data.keySet())
        {
            String device = data.getString(macAdr);

            JSONObject json = new JSONObject(settings.getString(macAdr, "{\"geo\":[]}"));

            json.put("macAdr", macAdr);
            json.put("deviceName", device);

            // if (json.has("geo")) Log.d(LOGTAG, "Has geo");
            JSONArray geo = (JSONArray) json.get("geo");
            // JSONArray geo = new JSONArray();

            geo.put(Tracker.json);
            json.put("geo", geo);

            editor.putString(macAdr, json.toString());
            editor.apply();
        }
    }

    public void printAll()
    {
        Map<String, ?> map = settings.getAll();

        for (String key: map.keySet())
        {
            Log.d(LOGTAG, (String) map.get(key));
        }
    }
}
