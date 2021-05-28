package com.hzlh.kzq.ui;

import android.os.Bundle;

import com.hzlh.kzq.R;

import butterknife.ButterKnife;

public class DeviceInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);
        ButterKnife.bind(this);


    }



}