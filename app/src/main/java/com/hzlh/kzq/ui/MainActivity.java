package com.hzlh.kzq.ui;

import android.content.Intent;
import android.os.Bundle;

import com.hzlh.kzq.R;
import com.hzlh.kzq.utils.UDPUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        UDPUtil.startReadUdpMsg();
    }

    @OnClick(R.id.btn_lianjie)
    private void btn_lianjie() {
        UDPUtil.doWangguan("");
        startActivity(new Intent(this, DevicesActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}