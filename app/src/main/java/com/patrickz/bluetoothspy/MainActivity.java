package com.patrickz.bluetoothspy;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.LinearLayout.LayoutParams;
import android.widget.Toolbar;

import org.json.JSONObject;

//public class MainActivity extends AppCompatActivity
public class MainActivity extends Activity
{
    private final static String LOGTAG = "MainActivity";

    private GradientDrawable roundedCorners(int radius, String color, int strockSize, String strockColor)
    {
        GradientDrawable gdrawable = new GradientDrawable();
        gdrawable.setCornerRadius(radius);
        gdrawable.setColor(Color.parseColor(color));
        gdrawable.setStroke(strockSize, Color.parseColor(strockColor));

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

        linLayout.addView(myToolbar);
        setActionBar(myToolbar);

//        HorizontalScrollView scroll = new HorizontalScrollView(this);
        ScrollView scroll = new ScrollView(this);
        linLayout.addView(scroll);

        final LinearLayout scrollLayout = new LinearLayout(this);

        scrollLayout.setOrientation(LinearLayout.VERTICAL);
        scrollLayout.setLayoutParams(linLayoutParam);
        scrollLayout.setBackgroundColor(Color.parseColor("#232323"));

        int padding = 60;

        final RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(
            LayoutParams.MATCH_PARENT, 200);

        layout.setMargins(padding, padding/2, padding, padding/2);

        final GradientDrawable gd = roundedCorners(40, "#333333", 3, "#3D3D3D");

        scroll.addView(scrollLayout);

        Button button = new Button(this);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_LONG).show();

                for (int inx = 0; inx < BluetoothSnitch.data.length(); inx++)
                {
                    try
                    {
                        String key =  BluetoothSnitch.data.keys().next();

                        TextView textView = new TextView(getApplicationContext());
                        textView.setText(BluetoothSnitch.data.getString(key));

                        textView.setTextColor(Color.parseColor("#ffffff"));
                        textView.setTextSize(20f);
                        textView.setLayoutParams(layout);

                        textView.setGravity(Gravity.CENTER);

                        textView.setBackground(gd);

                        scrollLayout.addView(textView);
                    }
                    catch (Exception exc)
                    {}
                }
            }
        });

        button.setText("Refesh");
        button.setTextColor(Color.parseColor("#ffffff"));
        button.setTextSize(20f);

        button.setLayoutParams(layout);

        button.setGravity(Gravity.CENTER);

        button.setBackground(gd);
        // button.setBackgroundColor(Color.GRAY);

        scrollLayout.addView(button);

//        for(int inx = 0; inx < 20; inx++)
//        {
//            TextView textView = new TextView(this);
//            textView.setText("Number: " + inx);
//
//            // textView.setTypeface(Typeface.MONOSPACE);
//
//            textView.setTextColor(0xFFFFFFFF);
//            textView.setTextSize(20f);
//            textView.setLayoutParams(layout);
//
//            textView.setGravity(Gravity.CENTER);
//
//            textView.setBackground(gd);
//
//            scrollLayout.addView(textView);
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Log.d(LOGTAG, "Hello World!");
        Toast.makeText(this, "Hello World!", Toast.LENGTH_LONG).show();

        createGUI();

//        Intent msgIntent = new Intent(this, MainService.class);
//        this.startService(msgIntent);

        BluetoothSnitch snitch = new BluetoothSnitch(this);
    }
}
