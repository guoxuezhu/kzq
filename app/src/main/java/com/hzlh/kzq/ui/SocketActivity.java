package com.hzlh.kzq.ui;

import android.os.Bundle;

import com.hzlh.kzq.R;

import butterknife.ButterKnife;

public class SocketActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        ButterKnife.bind(this);

    }
}
