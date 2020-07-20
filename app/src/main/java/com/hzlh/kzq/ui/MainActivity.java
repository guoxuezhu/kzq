package com.hzlh.kzq.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.hzlh.kzq.R;
import com.hzlh.kzq.adapter.DeviceAdapter;
import com.hzlh.kzq.data.DeviceInfo;
import com.hzlh.kzq.utils.ELog;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements DeviceAdapter.CallBack {

    @BindView(R.id.device_recyclerview)
    RecyclerView device_recyclerview;


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    deviceAdapter.setData(deviceInfos);
                    break;
            }
        }
    };


    private DatagramSocket cUdpSocket;
    private Timer timer1, timer2;
    private boolean isRun = false;
    private DeviceAdapter deviceAdapter;
    private List<DeviceInfo> deviceInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        try {
            cUdpSocket = new DatagramSocket(9999);
            isRun = true;
            getMsg();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        deviceInfos = new ArrayList<DeviceInfo>();
        initRcyclerView();
    }


    private void initRcyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        device_recyclerview.setLayoutManager(manager);
        deviceAdapter = new DeviceAdapter(this, deviceInfos, this);
        device_recyclerview.setAdapter(deviceAdapter);
    }


    @OnClick(R.id.img_search)
    public void img_search() {
        String sendmsg = "{\"Zone\":\"EPort\",\"Version\":\"2.0.07e\",\"ConnectMode\":\"Forbidden\",\"Type\":\"EP_Tool\",\"ServAddr\":\"0.0.0.0\",\"ServEn\":1,\"ServPort\":48898,\"PasswordEn\":0,\"ClientEn\":0,\"Agent\":0}";
//        String sendmsg = gson.toJson(new DeviceInfo(0, "EP_Tool", 0, 48898, "2.0.07e",
//                "Forbidden", "EPort", 1, "0.0.0.0", 0));
        ELog.d("======发送消息======" + sendmsg);
        ClientSend(sendmsg.getBytes());
    }


    private void ClientSend(final byte[] msgbyte) {
        if (timer1 != null) {
            timer1.cancel();
            timer1 = null;
        }
        timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                DatagramPacket dp = new DatagramPacket(msgbyte, msgbyte.length);
                dp.setSocketAddress(new InetSocketAddress("255.255.255.255", 48899));
                try {
                    cUdpSocket.send(dp);//发送一条广播信息
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 1);
    }


    private void getMsg() {

        timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                //准备空的数据包用于存放数据。
                byte[] buf = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length); // 1024

                while (isRun) {
                    try {
                        cUdpSocket.receive(datagramPacket);
                        ELog.d("=======接收到==IP==消息===========" + datagramPacket.getAddress().toString().substring(1));
                        String msg = new String(buf, 0, datagramPacket.getLength());
                        ELog.d("=======接收到消息===========" + msg);
                        Gson gson = new Gson();
                        DeviceInfo deviceInfo = gson.fromJson(msg, DeviceInfo.class);
                        deviceInfo.setAddressIP(datagramPacket.getAddress().toString().substring(1));
                        ELog.d("=======接收到消息===deviceInfo========" + deviceInfo.toString());
                        deviceInfos.clear();
                        deviceInfos.add(deviceInfo);
                        mHandler.sendEmptyMessage(1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

            }
        }, 1);

    }

    @Override
    public void onClickItemWlan(DeviceInfo item) {
        stopRun();
        startActivity(new Intent(this, FixIpActivity.class));
    }

    @Override
    public void onClickItemTcp(DeviceInfo item) {
        stopRun();
        startActivity(new Intent(this, SocketActivity.class));
    }

    private void stopRun() {
        isRun = false;
        if (cUdpSocket != null && !cUdpSocket.isClosed()) {
            cUdpSocket.close();
            cUdpSocket.disconnect();
        }
        if (timer1 != null) {
            timer1.cancel();
        }
        if (timer2 != null) {
            timer2.cancel();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
