package com.demo.mytimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTimer;
    private int sec = 0;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTimer = findViewById(R.id.textViewTimer);
        if (savedInstanceState != null) {
            sec = savedInstanceState.getInt("sec");
            isRunning = savedInstanceState.getBoolean("isRunning");
        }
        runTime();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isRunning", isRunning);
        outState.putInt("sec", sec);
    }

    public void onClickStart(View view) {
        isRunning = true;
    }

    public void onClickStop(View view) {
        isRunning = false;
    }

    public void onClickReset(View view) {
        isRunning = false;
        sec = 0;
    }
    private void runTime() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = sec / 3600;
                int minutes = (sec % 3600) / 60;
                int seconds = sec % 60;
                String time = String.format(Locale.getDefault(),"%02d:%02d:%02d", hours, minutes, seconds);
                textViewTimer.setText(time);
                if (isRunning) {
                    sec++;
                }
                handler.postDelayed(this, 1);
            }
        });
    }
}