package com.hzlh.kzq.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzlh.kzq.MyApplication;
import com.hzlh.kzq.R;
import com.hzlh.kzq.adapter.ChangjingAdapter;
import com.hzlh.kzq.data.DbDao.ChangjingDatasDao;
import com.hzlh.kzq.data.model.ChangjingDatas;
import com.hzlh.kzq.utils.UDPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangjingActivity extends BaseActivity implements ChangjingAdapter.CallBack {

    @BindView(R.id.changjing_recyclerView)
    RecyclerView changjing_recyclerView;

    private ChangjingDatasDao changjingDatasDao;
    private String wg_ip;
    private ChangjingAdapter changjingAdapter;

    private Handler changjingHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1003:
                    changjingAdapter.setDatas(changjingDatasDao.loadAll());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changjing);
        ButterKnife.bind(this);

        changjingDatasDao = MyApplication.getDaoSession().getChangjingDatasDao();
        changjingDatasDao.deleteAll();
        UDPUtil.setChangjingHandler(changjingHandler);
        initView();
        wg_ip = this.getIntent().getStringExtra("wg_ip");
        UDPUtil.sendMsg(wg_ip, "4C4801A7030000000100030A0D");

    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        changjing_recyclerView.setLayoutManager(manager);
        changjingAdapter = new ChangjingAdapter(this, changjingDatasDao.loadAll(), this);
        changjing_recyclerView.setAdapter(changjingAdapter);
    }

    @Override
    public void onClickChangjingItem(ChangjingDatas changjingData) {
        String cjid = Integer.toHexString(Integer.valueOf(changjingData.cj_id + ""));
        if (cjid.length() == 1) {
            cjid = "0" + cjid;
        }
        UDPUtil.sendMsg(wg_ip, "4C4801A9010000000100" + cjid + "0A0D");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        UDPUtil.closeChangjingHandler();
        changjingHandler = null;
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}