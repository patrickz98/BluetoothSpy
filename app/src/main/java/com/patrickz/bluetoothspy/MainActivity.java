package com.patrickz.bluetoothspy;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.LinearLayout.LayoutParams;
import android.widget.Toolbar;

import org.json.simple.JSONObject;

//import org.json.JSONObject;

//public class MainActivity extends AppCompatActivity
public class MainActivity extends Activity
{
    public final static String LOGMARKER = "PZLog-";
    private final static String LOGTAG = LOGMARKER + "MainActivity";
    private LinearLayout scrollLayout;
    private RelativeLayout.LayoutParams layout;

    private void refesh()
    {
        scrollLayout.removeAllViews();

//        for (String key:  BluetoothSnitch.data.keySet())
//        {
//            TextView textView = new TextView(getApplicationContext());
//            textView.setText(BluetoothSnitch.data.getString(key));
//
//            textView.setTextColor(Color.parseColor("#ffffff"));
//            textView.setTextSize(20f);
//            textView.setLayoutParams(layout);
//
//            textView.setGravity(Gravity.CENTER);
//
//            textView.setBackground(roundedCorners(40, "#333333", 3, "#3D3D3D"));
//
//            scrollLayout.addView(textView);
//        }

        for (String key: Tracker.json.keySet())
        {
            TextView textView = new TextView(getApplicationContext());

            String result = String.format("%s: %-11s", key, "" + Tracker.json.get(key));

//            textView.setText(key + ": " + Tracker.json.get(key));
            textView.setText(result);
//            textView.setText(result + ": " + ("" + Tracker.json.get(key)).length());

            textView.setTextColor(Color.parseColor("#ffffff"));
            textView.setTextSize(20f);
            textView.setLayoutParams(layout);

            textView.setGravity(Gravity.CENTER);

            textView.setBackground(roundedCorners(40, "#333333", 3, "#3D3D3D"));

            scrollLayout.addView(textView);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();

        refesh();

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add("Scan");

        return super.onCreateOptionsMenu(menu);
    }

    private GradientDrawable roundedCorners(int radius, String color, int strockSize, String strockColor)
    {
        GradientDrawable gdrawable = new GradientDrawable();
        gdrawable.setCornerRadius(radius);
        gdrawable.setColor(Color.parseColor(color));
        gdrawable.setStroke(strockSize, Color.parseColor(strockColor));

        JSONObject test = new JSONObject();

        test.put("asg", "sdf");

        return gdrawable;
    }

    private void createGUI()
    {
        LinearLayout linLayout = new LinearLayout(this);

        linLayout.setOrientation(LinearLayout.VERTICAL);
        linLayout.setBackgroundColor(Color.parseColor("#232323"));

        LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        this.setContentView(linLayout, linLayoutParam);

        Toolbar myToolbar = new Toolbar(this);
        myToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        myToolbar.setBackgroundColor(Color.parseColor("#7C25F8"));

        Menu menu = myToolbar.getMenu();
        menu.add("Add Test");

        linLayout.addView(myToolbar);
        setActionBar(myToolbar);

//        HorizontalScrollView scroll = new HorizontalScrollView(this);
        ScrollView scroll = new ScrollView(this);
        linLayout.addView(scroll);

        scrollLayout = new LinearLayout(this);
        scrollLayout.setOrientation(LinearLayout.VERTICAL);
        scrollLayout.setLayoutParams(linLayoutParam);
        scrollLayout.setBackgroundColor(Color.parseColor("#232323"));

        int padding = 60;

        layout = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 200);
        layout.setMargins(padding, padding/2, padding, padding/2);

        scroll.addView(scrollLayout);
    }

    private Handler mHandler = new Handler();

    // This gets executed in a non-UI thread:
    public void receiveMyMessage()
    {
        mHandler.post(new Runnable()
        {
            @Override
            public void run()
            {
                refesh();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Log.d(LOGTAG, "Hello World!");
        Toast.makeText(this, "Hello World!", Toast.LENGTH_LONG).show();

        createGUI();

        Intent msgIntent = new Intent(getApplicationContext(), MainService.class);
        this.startService(msgIntent);

//        BluetoothSnitch snitch = new BluetoothSnitch(this);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int count = 0;
                while (true)
                {
                    Log.d(LOGTAG, "Refesh: " + count);
                    count++;

                    receiveMyMessage();
                    Simple.sleep(1000);
                }
            }
        }).start();
    }
}
