package com.example.gridintentapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);

        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus++;
                handler.post(() -> {
                    progressBar.setProgress(progressStatus);
                    progressText.setText("Loading " + progressStatus + "%");
                });
                try { Thread.sleep(30); } catch (InterruptedException e) { }
            }
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }).start();
    }
}