package com.hzlh.kzq.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class WgDatas {

    @Id(autoincrement = true)
    public Long wg_id;

    public String wg_ip;

    public String wg_name;

    public String wg_swj_ip;

    public int wg_status;

    @Generated(hash = 2101897123)
    public WgDatas(Long wg_id, String wg_ip, String wg_name, String wg_swj_ip,
            int wg_status) {
        this.wg_id = wg_id;
        this.wg_ip = wg_ip;
        this.wg_name = wg_name;
        this.wg_swj_ip = wg_swj_ip;
        this.wg_status = wg_status;
    }

    @Generated(hash = 213821487)
    public WgDatas() {
    }

    @Override
    public String toString() {
        return "WgDatas{" +
                "wg_id=" + wg_id +
                ", wg_ip='" + wg_ip + '\'' +
                ", wg_name='" + wg_name + '\'' +
                ", wg_swj_ip='" + wg_swj_ip + '\'' +
                ", wg_status=" + wg_status +
                '}';
    }

    public Long getWg_id() {
        return this.wg_id;
    }

    public void setWg_id(Long wg_id) {
        this.wg_id = wg_id;
    }

    public String getWg_ip() {
        return this.wg_ip;
    }

    public void setWg_ip(String wg_ip) {
        this.wg_ip = wg_ip;
    }

    public String getWg_name() {
        return this.wg_name;
    }

    public void setWg_name(String wg_name) {
        this.wg_name = wg_name;
    }

    public String getWg_swj_ip() {
        return this.wg_swj_ip;
    }

    public void setWg_swj_ip(String wg_swj_ip) {
        this.wg_swj_ip = wg_swj_ip;
    }

    public int getWg_status() {
        return this.wg_status;
    }

    public void setWg_status(int wg_status) {
        this.wg_status = wg_status;
    }
}