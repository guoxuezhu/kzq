package com.hzlh.kzq.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.hzlh.kzq.R;
import com.hzlh.kzq.utils.ELog;
import com.hzlh.kzq.utils.UDPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

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

    @BindView(R.id.rbtn_kzq_open)
    RadioButton rbtn_kzq_open;
    @BindView(R.id.rbtn_kzq_close)
    RadioButton rbtn_kzq_close;


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
        device_id = this.getIntent().getLongExtra("device_id", 0) + "";
        value_1 = this.getIntent().getStringExtra("value_1");
        value_2 = this.getIntent().getStringExtra("value_2");
        value_3 = this.getIntent().getStringExtra("value_3");
        value_4 = this.getIntent().getStringExtra("value_4");
        value_5 = this.getIntent().getStringExtra("value_5");
//        value_6 = this.getIntent().getStringExtra("value_6");
//        value_7 = this.getIntent().getStringExtra("value_7");
//        value_8 = this.getIntent().getStringExtra("value_8");
        initView();
    }

    private void initView() {
        ELog.i("=======value_1=======" + value_1);
        if (value_1.equals("1")) {
            rbtn_kzq_open.setChecked(true);
        } else {
            rbtn_kzq_close.setChecked(true);
        }
        tv_dianya.setText(value_2);
        tv_dianliu.setText(value_3);
        tv_gonglv.setText(value_4);
        tv_dianliang.setText(value_5);
    }

    @OnClick(R.id.rbtn_kzq_open)
    public void rbtn_kzq_open() {
        ELog.i("=======device_id=======" + device_id);
        String id = Integer.toHexString(Integer.valueOf(device_id));
        if (id.length() == 1) {
            id = "0" + id;
        }
        ELog.i("=======id=======" + id);
        UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + id + "01" + "03" + "01" + "0A0D", 10101);

    }

    @OnClick(R.id.rbtn_kzq_close)
    public void rbtn_kzq_close() {
        ELog.i("=======device_id=======" + device_id);
        String id = Integer.toHexString(Integer.valueOf(device_id));
        if (id.length() == 1) {
            id = "0" + id;
        }
        ELog.i("=======id=======" + id);
        UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + id + "01" + "03" + "00" + "0A0D", 10101);

    }


    @OnClick(R.id.wgbtn_switch_status)
    public void wgbtn_switch_status() {
        ELog.i("=======device_id=======" + device_id);
        String id = Integer.toHexString(Integer.valueOf(device_id));
        if (id.length() == 1) {
            id = "0" + id;
        }
        ELog.i("=======id=======" + id);
        if (wgbtn_switch_status.isChecked()) {
            UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + id + "01" + "03" + "01" + "0A0D", 10101);
        } else {
            UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + id + "01" + "03" + "00" + "0A0D", 10101);
        }
    }

//    @OnCheckedChanged(R.id.wgbtn_switch_status)
//    public void onkzqCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
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