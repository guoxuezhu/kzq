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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements WgAdapter.CallBack {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        wgDatasDao = MyApplication.getDaoSession().getWgDatasDao();
        initRecyclerView();
        UDPUtil.setMainHandler(mHandler);
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
        mHandler = null;
        ELog.i("=========onClickWgItem======" + wgDatas.toString());
        Intent intent = new Intent(this, DevicesActivity.class);
        intent.putExtra("wg_ip", wgDatas.wg_ip);
        startActivity(intent);
    }

    @OnClick(R.id.btn_lianjie)
    public void btn_lianjie() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UDPUtil.stopReadMsg();
    }
}