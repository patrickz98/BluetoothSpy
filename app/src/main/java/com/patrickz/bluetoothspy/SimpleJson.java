package com.patrickz.bluetoothspy;

import org.json.JSONException;
import org.json.JSONObject;

public class SimpleJson
{
    public static void put(JSONObject json, String key, Object obj)
    {
        try
        {
            json.put(key, obj);
        }
        catch (JSONException exc)
        {
            exc.printStackTrace();
        }
    }

    public static String toString(JSONObject json, int pretty)
    {
        try
        {
            return json.toString(pretty);
        }
        catch (JSONException exc)
        {
            exc.printStackTrace();
        }

        return null;
    }
}
