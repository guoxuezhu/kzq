package com.hzlh.kzq.data.model;

public class DevicesData {

    public Long device_id;

    public String device_name;

    public String device_type;

    public String device_status;

    @Override
    public String toString() {
        return "DevicesData{" +
                "device_id=" + device_id +
                ", device_name='" + device_name + '\'' +
                ", device_type='" + device_type + '\'' +
                ", device_status='" + device_status + '\'' +
                '}';
    }
}