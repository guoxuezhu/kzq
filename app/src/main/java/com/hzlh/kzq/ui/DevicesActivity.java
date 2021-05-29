package com.hzlh.kzq.ui;

import android.content.Intent;
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
import com.hzlh.kzq.utils.ELog;
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
    private String wg_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        ButterKnife.bind(this);

        devicesDataDao = MyApplication.getDaoSession().getDevicesDataDao();
        devicesDataDao.deleteAll();
        UDPUtil.setDeviceHandler(deviceHandler);
        initView();
        wg_ip = this.getIntent().getStringExtra("wg_ip");
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
        ELog.i("=======devicesData====" + devicesData.toString());
        if (devicesData.device_type.equals("1")) {  // 4键 控制面板
//            if (wgmbDialog == null) {
//                wgmbDialog = new WgmbDialog(this, null, this);
//            }
//            if (wgmbDialog != null) {
//                wgmbDialog.show();
//                wgmbDialog.setCanceledOnTouchOutside(false);
//            }
        } else if (devicesData.device_type.equals("2")) {  // 控制器
            Intent intent = new Intent(this, DeviceInfoActivity.class);
            intent.putExtra("wg_ip", wg_ip);
            intent.putExtra("device_id", devicesData.device_id);
            intent.putExtra("value_1", devicesData.value_1);
            intent.putExtra("value_2", devicesData.value_2);
            intent.putExtra("value_3", devicesData.value_3);
            intent.putExtra("value_4", devicesData.value_4);
            intent.putExtra("value_5", devicesData.value_5);
//            intent.putExtra("value_6", devicesData.value_6);
//            intent.putExtra("value_7", devicesData.value_7);
//            intent.putExtra("value_8", devicesData.value_8);
            startActivity(intent);
        } else if (devicesData.device_type.equals("3")) {  // 串口透传
            Intent intent = new Intent(this, DeviceTouchuanActivity.class);
            intent.putExtra("wg_ip", wg_ip);
            intent.putExtra("device_id", devicesData.device_id);
            intent.putExtra("value_1", devicesData.value_1);
            startActivity(intent);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        UDPUtil.setDeviceHandler(deviceHandler);
        ELog.i("=======onRestart=======");
    }

    @Override
    protected void onStop() {
        super.onStop();
        UDPUtil.closeDeviceHandler();
        ELog.i("=======onStop=======");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        UDPUtil.closeDeviceHandler();
        deviceHandler = null;
    }
}