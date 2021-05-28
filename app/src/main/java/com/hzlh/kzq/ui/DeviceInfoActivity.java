package com.hzlh.kzq.ui;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.hzlh.kzq.R;
import com.hzlh.kzq.utils.ELog;
import com.hzlh.kzq.utils.UDPUtil;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class DeviceInfoActivity extends BaseActivity {

    @BindView(R.id.tv_dianya)
    TextView tv_dianya;
    @BindView(R.id.tv_dianliu)
    TextView tv_dianliu;
    @BindView(R.id.tv_gonglv)
    TextView tv_gonglv;
    @BindView(R.id.tv_dianliang)
    TextView tv_dianliang;

    @BindView(R.id.wgbtn_switch_status)
    Switch wgbtn_switch_status;

    private String wg_ip;
    private String device_id;
    private String value_1;
    private String value_2;
    private String value_3;
    private String value_4;
    private String value_5;
//    private String value_6;
//    private String value_7;
//    private String value_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);
        ButterKnife.bind(this);

        wg_ip = this.getIntent().getStringExtra("wg_ip");
        device_id = this.getIntent().getStringExtra("device_id");
        value_1 = this.getIntent().getStringExtra("value_1");
        value_2 = this.getIntent().getStringExtra("value_2");
        value_3 = this.getIntent().getStringExtra("value_3");
        value_4 = this.getIntent().getStringExtra("value_4");
        value_5 = this.getIntent().getStringExtra("value_5");
//        value_6 = this.getIntent().getStringExtra("value_6");
//        value_7 = this.getIntent().getStringExtra("value_7");
//        value_8 = this.getIntent().getStringExtra("value_8");
//        devicesData = this.getIntent().getSerializableExtra("devicesData");
//        ELog.i("======DeviceInfoActivity====devicesData====" + devicesData.toString());
        initView();
    }

    private void initView() {
        if (value_1.equals("1")) {
            wgbtn_switch_status.setChecked(true);
        } else {
            wgbtn_switch_status.setChecked(false);
        }
        tv_dianya.setText(value_2);
        tv_dianliu.setText(value_3);
        tv_gonglv.setText(value_4);
        tv_dianliang.setText(value_5);
    }

    @OnCheckedChanged(R.id.wgbtn_switch_status)
    public void onkzqCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        ELog.i("=======isChecked=======" + isChecked);
        ELog.i("=======device_id=======" + device_id);
        if (device_id.length() == 1) {
            device_id = "0" + device_id;
        }
        if (isChecked) {
            UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + device_id + "01" + "03" + "01" + "0A0D");
        } else {
            UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + device_id + "01" + "03" + "00" + "0A0D");
        }
    }

}