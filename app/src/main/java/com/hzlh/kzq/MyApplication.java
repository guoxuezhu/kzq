package com.hzlh.kzq;

import android.app.Application;

import com.hzlh.kzq.data.DbDao.DaoMaster;
import com.hzlh.kzq.data.DbDao.DaoSession;

public class MyApplication extends Application {

    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initDatas();
    }

    private void initDatas() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "wgkzq.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
