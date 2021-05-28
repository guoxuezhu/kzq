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
import com.hzlh.kzq.utils.ELog;
import com.hzlh.kzq.utils.UDPUtil;
import com.hzlh.kzq.utils.WgDataDialog;
import com.hzlh.kzq.utils.WgmbDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DevicesActivity extends BaseActivity implements DevicesAdapter.CallBack, WgDataDialog.DialogCallBack, WgmbDialog.DialogCallBack {

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
    private WgDataDialog wgDataDialog;
    private WgmbDialog wgmbDialog;
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
            if (wgmbDialog == null) {
                wgmbDialog = new WgmbDialog(this, null, this);
            }
            if (wgmbDialog != null) {
                wgmbDialog.show();
                wgmbDialog.setCanceledOnTouchOutside(false);
            }
        } else if (devicesData.device_type.equals("2")) {  // 控制器
            if (wgDataDialog == null) {
                wgDataDialog = new WgDataDialog(this, null, this);
            }
            if (wgDataDialog != null) {
                wgDataDialog.show();
                wgDataDialog.setCanceledOnTouchOutside(false);
            }
        } else if (devicesData.device_type.equals("3")) {  // 串口透传

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        UDPUtil.closeDeviceHandler();
        deviceHandler = null;
    }

    @Override
    public void dismissDialog() {
        if (wgDataDialog != null) {
            wgDataDialog.dismiss();
            wgDataDialog = null;
        }
    }

    @Override
    public void wgmbDismissDialog() {
        if (wgmbDialog != null) {
            wgmbDialog.dismiss();
            wgmbDialog = null;
        }
    }

    @Override
    public void wgmbOnClick(int position, int status) {
        UDPUtil.sendMsg(wg_ip, "4C4800A20000000000000A0D");
    }
}