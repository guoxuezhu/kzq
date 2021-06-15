package com.hzlh.kzq.ui;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Switch;

import com.hzlh.kzq.R;
import com.hzlh.kzq.utils.ELog;
import com.hzlh.kzq.utils.UDPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeviceTouchuanActivity extends BaseActivity {

    @BindView(R.id.wgbtn_switch_status_3)
    Switch wgbtn_switch_status_3;

    @BindView(R.id.rbtn_kzq_tc_open)
    RadioButton rbtn_kzq_tc_open;
    @BindView(R.id.rbtn_kzq_tc_close)
    RadioButton rbtn_kzq_tc_close;

    private String wg_ip;
    private String device_id;
    private String value_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_touchuan);
        ButterKnife.bind(this);

        wg_ip = this.getIntent().getStringExtra("wg_ip");
        device_id = this.getIntent().getLongExtra("device_id", 0) + "";
        value_1 = this.getIntent().getStringExtra("value_1");

        if (value_1.equals("1")) {
            rbtn_kzq_tc_open.setChecked(true);
        } else {
            rbtn_kzq_tc_close.setChecked(false);
        }
    }

    @OnClick(R.id.rbtn_kzq_tc_open)
    public void rbtn_kzq_tc_open() {
        ELog.i("=======device_id=======" + device_id);
        String id = Integer.toHexString(Integer.valueOf(device_id));
        if (id.length() == 1) {
            id = "0" + id;
        }
        ELog.i("=======id=======" + id);
        UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + id + "01" + "03" + "01" + "0A0D", 10101);
    }

    @OnClick(R.id.rbtn_kzq_tc_close)
    public void rbtn_kzq_tc_close() {
        ELog.i("=======device_id=======" + device_id);
        String id = Integer.toHexString(Integer.valueOf(device_id));
        if (id.length() == 1) {
            id = "0" + id;
        }
        ELog.i("=======id=======" + id);
        UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + id + "01" + "03" + "00" + "0A0D", 10101);
    }

    @OnClick(R.id.wgbtn_switch_status_3)
    public void wgbtn_switch_status_3() {
        ELog.i("=======device_id=======" + device_id);
        String id = Integer.toHexString(Integer.valueOf(device_id));
        if (id.length() == 1) {
            id = "0" + id;
        }
        ELog.i("=======id=======" + id);
        if (wgbtn_switch_status_3.isChecked()) {
            UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + id + "01" + "03" + "01" + "0A0D", 10101);
        } else {
            UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + id + "01" + "03" + "00" + "0A0D", 10101);
        }
    }

//    @OnCheckedChanged(R.id.wgbtn_switch_status_3)
//    public void ontckzqCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//        ELog.i("=======isChecked=======" + isChecked);
//        ELog.i("=======device_id=======" + device_id);
//        String id = Integer.toHexString(Integer.valueOf(device_id));
//        if (id.length() == 1) {
//            id = "0" + id;
//        }
//        ELog.i("=======id=======" + id);
//        if (isChecked) {
//            UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + id + "01" + "03" + "01" + "0A0D");
//        } else {
//            UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + id + "01" + "03" + "00" + "0A0D");
//        }
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}