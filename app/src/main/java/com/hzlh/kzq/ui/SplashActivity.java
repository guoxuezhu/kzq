package com.hzlh.kzq.ui;

import android.content.Intent;
import android.os.Bundle;

import com.hzlh.kzq.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {


    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
                timer.cancel();
            }
        }, 1500);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
