package com.hzlh.kzq.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzlh.kzq.MyApplication;
import com.hzlh.kzq.R;
import com.hzlh.kzq.adapter.DevicesAdapter;
import com.hzlh.kzq.data.DbDao.DevicesDataDao;
import com.hzlh.kzq.data.model.DevicesData;
import com.hzlh.kzq.utils.UDPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DevicesActivity extends BaseActivity implements DevicesAdapter.CallBack {

    @BindView(R.id.device_recyclerView)
    RecyclerView device_recyclerView;

    private DevicesDataDao devicesDataDao;
    private DevicesAdapter devicesAdapter;

    private Handler deviceHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1002:
                    devicesAdapter.setDatas(devicesDataDao.loadAll());
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        ButterKnife.bind(this);

        devicesDataDao = MyApplication.getDaoSession().getDevicesDataDao();
        devicesDataDao.deleteAll();
        UDPUtil.setDeviceHandler(deviceHandler);
        initView();
        String wg_ip = this.getIntent().getStringExtra("wg_ip");
        UDPUtil.sendMsg(wg_ip, "4C4800A20000000000000A0D");
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        device_recyclerView.setLayoutManager(manager);
        devicesAdapter = new DevicesAdapter(this, devicesDataDao.loadAll(), this);
        device_recyclerView.setAdapter(devicesAdapter);
    }

    @Override
    public void onClickDeviceItem(DevicesData devicesData) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        UDPUtil.closeDeviceHandler();
        deviceHandler = null;
    }
}