package com.hzlh.kzq.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class WgDatas {

    @Id(autoincrement = true)
    public Long wg_id;

    public String wg_name;

    public String wg_ip;

    public String wg_wl_zwym;

    public String wg_wl_wgip;

    public String wg_mac;

    public String wg_swj_ip;

    public String wg_swj_port;

    public int wg_status;

    @Generated(hash = 307249564)
    public WgDatas(Long wg_id, String wg_name, String wg_ip, String wg_wl_zwym,
            String wg_wl_wgip, String wg_mac, String wg_swj_ip, String wg_swj_port,
            int wg_status) {
        this.wg_id = wg_id;
        this.wg_name = wg_name;
        this.wg_ip = wg_ip;
        this.wg_wl_zwym = wg_wl_zwym;
        this.wg_wl_wgip = wg_wl_wgip;
        this.wg_mac = wg_mac;
        this.wg_swj_ip = wg_swj_ip;
        this.wg_swj_port = wg_swj_port;
        this.wg_status = wg_status;
    }

    @Generated(hash = 213821487)
    public WgDatas() {
    }

    @Override
    public String toString() {
        return "WgDatas{" +
                "wg_id=" + wg_id +
                ", wg_name='" + wg_name + '\'' +
                ", wg_ip='" + wg_ip + '\'' +
                ", wg_wl_zwym='" + wg_wl_zwym + '\'' +
                ", wg_wl_wgip='" + wg_wl_wgip + '\'' +
                ", wg_mac='" + wg_mac + '\'' +
                ", wg_swj_ip='" + wg_swj_ip + '\'' +
                ", wg_swj_port='" + wg_swj_port + '\'' +
                ", wg_status=" + wg_status +
                '}';
    }

    public Long getWg_id() {
        return this.wg_id;
    }

    public void setWg_id(Long wg_id) {
        this.wg_id = wg_id;
    }

    public String getWg_name() {
        return this.wg_name;
    }

    public void setWg_name(String wg_name) {
        this.wg_name = wg_name;
    }

    public String getWg_ip() {
        return this.wg_ip;
    }

    public void setWg_ip(String wg_ip) {
        this.wg_ip = wg_ip;
    }

    public String getWg_wl_zwym() {
        return this.wg_wl_zwym;
    }

    public void setWg_wl_zwym(String wg_wl_zwym) {
        this.wg_wl_zwym = wg_wl_zwym;
    }

    public String getWg_wl_wgip() {
        return this.wg_wl_wgip;
    }

    public void setWg_wl_wgip(String wg_wl_wgip) {
        this.wg_wl_wgip = wg_wl_wgip;
    }

    public String getWg_mac() {
        return this.wg_mac;
    }

    public void setWg_mac(String wg_mac) {
        this.wg_mac = wg_mac;
    }

    public String getWg_swj_ip() {
        return this.wg_swj_ip;
    }

    public void setWg_swj_ip(String wg_swj_ip) {
        this.wg_swj_ip = wg_swj_ip;
    }

    public String getWg_swj_port() {
        return this.wg_swj_port;
    }

    public void setWg_swj_port(String wg_swj_port) {
        this.wg_swj_port = wg_swj_port;
    }

    public int getWg_status() {
        return this.wg_status;
    }

    public void setWg_status(int wg_status) {
        this.wg_status = wg_status;
    }
}