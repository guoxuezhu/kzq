package com.hzlh.kzq.ui;

import android.os.Bundle;

import com.hzlh.kzq.R;
import com.hzlh.kzq.utils.ELog;
import com.hzlh.kzq.utils.UDPUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DianfsActivity extends BaseActivity {

    private String wg_ip;
    private String device_id;
    private String value_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianfs);
        ButterKnife.bind(this);

        wg_ip = this.getIntent().getStringExtra("wg_ip");
        device_id = this.getIntent().getLongExtra("device_id", 0) + "";

    }


    @OnClick(R.id.rbtn_dfs_close)
    public void rbtn_dfs_close() {
        ELog.i("=======device_id=======" + device_id);
        String id = Integer.toHexString(Integer.valueOf(device_id));
        if (id.length() == 1) {
            id = "0" + id;
        }
        ELog.i("=======id=======" + id);
        UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + id + "01" + "03" + "01" + "0A0D");
    }

    @OnClick(R.id.rbtn_dfs_fs_1)
    public void rbtn_dfs_fs_1() {
        ELog.i("=======device_id=======" + device_id);
        String id = Integer.toHexString(Integer.valueOf(device_id));
        if (id.length() == 1) {
            id = "0" + id;
        }
        ELog.i("=======id=======" + id);
        UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + id + "02" + "03" + "01" + "0A0D");
    }

    @OnClick(R.id.rbtn_dfs_fs_2)
    public void rbtn_dfs_fs_2() {
        ELog.i("=======device_id=======" + device_id);
        String id = Integer.toHexString(Integer.valueOf(device_id));
        if (id.length() == 1) {
            id = "0" + id;
        }
        ELog.i("=======id=======" + id);
        UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + id + "03" + "03" + "01" + "0A0D");
    }

    @OnClick(R.id.rbtn_dfs_fs_3)
    public void rbtn_dfs_fs_3() {
        ELog.i("=======device_id=======" + device_id);
        String id = Integer.toHexString(Integer.valueOf(device_id));
        if (id.length() == 1) {
            id = "0" + id;
        }
        ELog.i("=======id=======" + id);
        UDPUtil.sendMsg(wg_ip, "4C4801A4000000000400" + id + "04" + "03" + "01" + "0A0D");
    }

}