package com.hzlh.kzq.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class ChangjingDatas {

    @Id
    public Long cj_id;

    public String cj_name;

    @Generated(hash = 1223109937)
    public ChangjingDatas(Long cj_id, String cj_name) {
        this.cj_id = cj_id;
        this.cj_name = cj_name;
    }

    @Generated(hash = 948519541)
    public ChangjingDatas() {
    }

    @Override
    public String toString() {
        return "ChangjingDatas{" +
                "cj_id=" + cj_id +
                ", cj_name='" + cj_name + '\'' +
                '}';
    }

    public Long getCj_id() {
        return this.cj_id;
    }

    public void setCj_id(Long cj_id) {
        this.cj_id = cj_id;
    }

    public String getCj_name() {
        return this.cj_name;
    }

    public void setCj_name(String cj_name) {
        this.cj_name = cj_name;
    }
}