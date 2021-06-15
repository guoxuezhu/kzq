package com.hzlh.kzq.ui;

import android.content.Intent;
import android.os.Bundle;

import com.hzlh.kzq.R;
import com.hzlh.kzq.utils.ELog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WgActivity extends BaseActivity {

    private String wg_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wg);
        ButterKnife.bind(this);

        wg_ip = this.getIntent().getStringExtra("wg_ip");

    }

    @OnClick(R.id.device_list_btn)
    public void device_list_btn() {
        ELog.i("=========device_list_btn======" + wg_ip);
        Intent intent = new Intent(this, DevicesActivity.class);
        intent.putExtra("wg_ip", wg_ip);
        startActivity(intent);
    }

    @OnClick(R.id.cj_list_btn)
    public void cj_list_btn() {
        ELog.i("=========cj_list_btn======" + wg_ip);
        Intent intent = new Intent(this, ChangjingActivity.class);
        intent.putExtra("wg_ip", wg_ip);
        startActivity(intent);
    }
}