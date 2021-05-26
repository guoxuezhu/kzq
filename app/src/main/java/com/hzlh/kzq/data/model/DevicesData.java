package com.hzlh.kzq.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DevicesData {

    @Id
    public Long device_id;

    public String device_name;

    public String device_type;

    public String device_status;

    public String value_1;
    public String value_2;
    public String value_3;
    public String value_4;
    public String value_5;
    public String value_6;
    public String value_7;
    public String value_8;

    @Generated(hash = 1684681815)
    public DevicesData(Long device_id, String device_name, String device_type,
            String device_status, String value_1, String value_2, String value_3,
            String value_4, String value_5, String value_6, String value_7,
            String value_8) {
        this.device_id = device_id;
        this.device_name = device_name;
        this.device_type = device_type;
        this.device_status = device_status;
        this.value_1 = value_1;
        this.value_2 = value_2;
        this.value_3 = value_3;
        this.value_4 = value_4;
        this.value_5 = value_5;
        this.value_6 = value_6;
        this.value_7 = value_7;
        this.value_8 = value_8;
    }

    @Generated(hash = 604222447)
    public DevicesData() {
    }

    @Override
    public String toString() {
        return "DevicesData{" +
                "device_id=" + device_id +
                ", device_name='" + device_name + '\'' +
                ", device_type='" + device_type + '\'' +
                ", device_status='" + device_status + '\'' +
                ", value_1='" + value_1 + '\'' +
                ", value_2='" + value_2 + '\'' +
                ", value_3='" + value_3 + '\'' +
                ", value_4='" + value_4 + '\'' +
                ", value_5='" + value_5 + '\'' +
                ", value_6='" + value_6 + '\'' +
                ", value_7='" + value_7 + '\'' +
                ", value_8='" + value_8 + '\'' +
                '}';
    }

    public Long getDevice_id() {
        return this.device_id;
    }

    public void setDevice_id(Long device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return this.device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_type() {
        return this.device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_status() {
        return this.device_status;
    }

    public void setDevice_status(String device_status) {
        this.device_status = device_status;
    }

    public String getValue_1() {
        return this.value_1;
    }

    public void setValue_1(String value_1) {
        this.value_1 = value_1;
    }

    public String getValue_2() {
        return this.value_2;
    }

    public void setValue_2(String value_2) {
        this.value_2 = value_2;
    }

    public String getValue_3() {
        return this.value_3;
    }

    public void setValue_3(String value_3) {
        this.value_3 = value_3;
    }

    public String getValue_4() {
        return this.value_4;
    }

    public void setValue_4(String value_4) {
        this.value_4 = value_4;
    }

    public String getValue_5() {
        return this.value_5;
    }

    public void setValue_5(String value_5) {
        this.value_5 = value_5;
    }

    public String getValue_6() {
        return this.value_6;
    }

    public void setValue_6(String value_6) {
        this.value_6 = value_6;
    }

    public String getValue_7() {
        return this.value_7;
    }

    public void setValue_7(String value_7) {
        this.value_7 = value_7;
    }

    public String getValue_8() {
        return this.value_8;
    }

    public void setValue_8(String value_8) {
        this.value_8 = value_8;
    }
}