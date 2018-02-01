package com.nikhilkumar.quakereport;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ActionBar action_bar = getSupportActionBar();
        action_bar.hide();
        final Handler handler = new Handler();
        final Runnable doNextActivity = new Runnable() {
            @Override
            public void run() {
                // Intent to jump to the next activity
                Intent intent= new Intent(StartActivity.this, EarthquakeActivity.class);
                startActivity(intent);
                finish(); // so the splash activity goes away
            }
        };

        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(4000);
                handler.post(doNextActivity);
            }
        }.start();
    }
}
