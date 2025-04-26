package com.example.gridintentapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private Switch themeSwitch;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "app_prefs";
    private static final String DARK_MODE_KEY = "dark_mode";

    String[] labels = {"Browser", "Call", "Maps", "Camera", "Email",
            "SMS", "Settings", "Clock", "Calculator",
            "YouTube", "Calendar"};
    int[] images = { R.drawable.ic_browser, R.drawable.ic_call, R.drawable.ic_map,
            R.drawable.ic_camera, R.drawable.ic_email,
            R.drawable.ic_sms, R.drawable.ic_settings, R.drawable.ic_clock,
            R.drawable.ic_calculator, R.drawable.ic_youtube, R.drawable.ic_calendar };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Apply saved theme
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean(DARK_MODE_KEY, false);
        AppCompatDelegate.setDefaultNightMode(
                isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind toolbar and set as action bar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Dark mode toggle
        themeSwitch = findViewById(R.id.themeSwitch);
        themeSwitch.setChecked(isDarkMode);
        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sharedPreferences.edit()
                    .putBoolean(DARK_MODE_KEY, isChecked)
                    .apply();
            AppCompatDelegate.setDefaultNightMode(
                    isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
            );
            recreate();
        });

        // Grid setup
        GridView gridView = findViewById(R.id.gridView);
        CustomAdapter adapter = new CustomAdapter(this, labels, images);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((adapterView, view, position, id) -> {

            switch (position) {
                case 0: // Browser
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")));
                    break;
                case 1: // Call
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1234567890")));
                    break;
                case 2: // Maps
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Nearby+Parks"));
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                    break;
                case 3: // Camera
                    Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivity(cameraIntent);
                    break;
                case 4: // Email
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
                    startActivity(emailIntent);
                    break;
                case 5: // SMS
                    Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:1234567890"));
                    smsIntent.putExtra("sms_body", "Hello from Grid App!");
                    startActivity(smsIntent);
                    break;
                case 6: // Settings
                    startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                    break;
                case 7: // Clock
                    startActivity(new Intent(AlarmClock.ACTION_SHOW_ALARMS));
                    break;
                case 8: // Calculator
                    Intent calcIntent = getPackageManager().getLaunchIntentForPackage("com.android.calculator2");
                    if (calcIntent != null) {
                        startActivity(calcIntent);
                    }
                    break;
                case 9: // YouTube
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com")));
                    break;
                case 10: // Calendar
                    Intent calendarIntent = new Intent(Intent.ACTION_MAIN);
                    calendarIntent.addCategory(Intent.CATEGORY_APP_CALENDAR);
                    startActivity(calendarIntent);
                    break;
            }

        });
    }

    public class CustomAdapter extends BaseAdapter {
        Context context;
        String[] labels;
        int[] images;
        LayoutInflater inflater;

        public CustomAdapter(Context context, String[] labels, int[] images) {
            this.context = context;
            this.labels = labels;
            this.images = images;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return labels.length;
        }

        @Override
        public Object getItem(int i) {
            return labels[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            view = inflater.inflate(R.layout.item_grid, null);
            TextView label = view.findViewById(R.id.gridText);
            ImageView icon = view.findViewById(R.id.gridImage);

            label.setText(labels[i]);
            icon.setImageResource(images[i]);

            // Animation part
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in_scale);
            view.startAnimation(animation);

            return view;
        }

    }
}
