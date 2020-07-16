package com.hzlh.kzq.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;


public class BaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

}
