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
import com.hzlh.kzq.adapter.WgAdapter;
import com.hzlh.kzq.data.DbDao.WgDatasDao;
import com.hzlh.kzq.data.model.WgDatas;
import com.hzlh.kzq.utils.ELog;
import com.hzlh.kzq.utils.UDPUtil;
import com.hzlh.kzq.utils.WgDataDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements WgAdapter.CallBack, WgDataDialog.DialogCallBack {

    @BindView(R.id.wg_recyclerView)
    RecyclerView wg_recyclerView;

    private WgDatasDao wgDatasDao;
    private WgAdapter wgAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1001:
                    wgAdapter.setDatas(wgDatasDao.loadAll());
                    break;
            }
        }
    };
    private WgDataDialog wgmbDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        wgDatasDao = MyApplication.getDaoSession().getWgDatasDao();
//        wgDatasDao.deleteAll();
        for (int i = 0; i < wgDatasDao.loadAll().size(); i++) {
            WgDatas wgData = wgDatasDao.loadAll().get(i);
            wgData.setWg_status(0);
            wgDatasDao.update(wgData);
        }
        initRecyclerView();
        UDPUtil.setMainHandler(mHandler);
        UDPUtil.sendMsg("255.255.255.255", "4C4800B00000000000000A0D", 10101);
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        wg_recyclerView.setLayoutManager(manager);
        wgAdapter = new WgAdapter(this, wgDatasDao.loadAll(), this);
        wg_recyclerView.setAdapter(wgAdapter);
    }

    @Override
    public void onClickWgItem(WgDatas wgDatas) {
        UDPUtil.closeMainHandler();
        ELog.i("=========onClickWgItem======" + wgDatas.toString());
        Intent intent = new Intent(this, WgActivity.class);
        intent.putExtra("wg_ip", wgDatas.wg_ip);
        startActivity(intent);
    }

    @Override
    public void onClickWgSetingItem(WgDatas wgDatas) {
        ELog.i("=========onClickWgSetingItem======" + wgDatas.toString());
        if (wgmbDialog == null) {
            wgmbDialog = new WgDataDialog(this, wgDatas, this);
        }
        if (wgmbDialog != null) {
            wgmbDialog.show();
            wgmbDialog.setCanceledOnTouchOutside(false);
        }

    }

    @Override
    public void dismissDialog() {
        closeDialog();
    }

    private void closeDialog() {
        if (wgmbDialog != null) {
            wgmbDialog.dismiss();
            wgmbDialog = null;
        }
    }

    @Override
    public void fixWgInfoOkDialog(WgDatas wgDatas, String et_wg_name, String et_wg_ip, String et_wg_wl_zwym, String et_wg_wl_wgip, String et_wg_mac, String et_wg_swj_ip, String et_wg_swj_port) {
        try {
            byte[] bytes =et_wg_name.getBytes("gbk");
            String wg_name = "";
            for (int j = 0; j < 20; j++) {
                if (j < bytes.length) {
                    String hex = Integer.toHexString(bytes[j] & 0xFF);
                    if (hex.length() == 1) {
                        hex = "0" + hex;
                    }
                    wg_name += hex.toUpperCase();
                } else {
                    wg_name += "00";
                }
            }
            ELog.i("=======onClickWgSwjItem===wg_name=====" + wg_name);

            String[] wgip = et_wg_ip.split("\\.");
            String wg_ip = "";
            for (int j = 0; j < wgip.length; j++) {
                String hex = Integer.toHexString(Integer.valueOf(wgip[j]));
                if (hex.length() == 1) {
                    hex = "0" + hex;
                }
                wg_ip += hex.toUpperCase();
            }
            ELog.i("=======onClickWgSwjItem===wg_ip=====" + wg_ip);

            String[] wgzwym = et_wg_wl_zwym.split("\\.");
            String wg_wl_zwym = "";
            for (int j = 0; j < wgzwym.length; j++) {
                String hex = Integer.toHexString(Integer.valueOf(wgzwym[j]));
                if (hex.length() == 1) {
                    hex = "0" + hex;
                }
                wg_wl_zwym += hex.toUpperCase();
            }
            ELog.i("=======onClickWgSwjItem===wg_wl_zwym=====" + wg_wl_zwym);

            String[] wg_wgip = et_wg_wl_wgip.split("\\.");
            String wg_wl_wgip = "";
            for (int j = 0; j < wg_wgip.length; j++) {
                String hex = Integer.toHexString(Integer.valueOf(wg_wgip[j]));
                if (hex.length() == 1) {
                    hex = "0" + hex;
                }
                wg_wl_wgip += hex.toUpperCase();
            }
            ELog.i("=======onClickWgSwjItem===wg_wl_wgip=====" + wg_wl_wgip);

            String[] wgmac = et_wg_mac.split("\\.");
            String wg_mac = "";
            for (int j = 0; j < wgmac.length; j++) {
                String hex = wgmac[j];
                if (hex.length() == 1) {
                    hex = "0" + hex;
                }
                wg_mac += hex.toUpperCase();
            }
            ELog.i("=======onClickWgSwjItem===wg_mac=====" + wg_mac);

            String[] wg_swjip = et_wg_swj_ip.split("\\.");
            String wg_swj_ip = "";
            for (int j = 0; j < wg_swjip.length; j++) {
                String hex = Integer.toHexString(Integer.valueOf(wg_swjip[j]));
                if (hex.length() == 1) {
                    hex = "0" + hex;
                }
                wg_swj_ip += hex.toUpperCase();
            }
            ELog.i("=======onClickWgSwjItem===wg_swj_ip=====" + wg_swj_ip);

            String wg_swj_port = Integer.toHexString(Integer.valueOf(et_wg_swj_port)).toUpperCase();
            ELog.i("=======onClickWgSwjItem===wg_swj_port=====" + wg_swj_port);

            UDPUtil.sendMsg(wgDatas.wg_ip, "4C4801A1000000002C00" + wg_name + wg_ip + wg_wl_zwym + wg_wl_wgip +
                    wg_mac + wg_swj_ip + wg_swj_port + "0A0D", 10101);
            closeDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        UDPUtil.stopReadMsg();
        mHandler = null;
        UDPUtil.closeMainHandler();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ELog.i("======MainActivity===onRestart======");
        UDPUtil.setMainHandler(mHandler);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ELog.i("======MainActivity===onRestart======");
//        UDPUtil.closeMainHandler();
    }


}