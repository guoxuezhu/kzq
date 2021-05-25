package com.hzlh.kzq.ui;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzlh.kzq.R;
import com.hzlh.kzq.utils.ELog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DevicesActivity extends BaseActivity {

    @BindView(R.id.device_recyclerView)
    RecyclerView device_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        device_recyclerView.setLayoutManager(manager);
//        icCardAdapter = new DevicesAdapter(this, icCardDao.loadAll(), this);
//        device_recyclerView.setAdapter(icCardAdapter);
//        ELog.i("===========icCardDao.loadAll()============");
    }


}