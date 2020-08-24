package com.hzlh.kzq.ui;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hzlh.kzq.R;
import com.hzlh.kzq.utils.ELog;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeviceActivity extends BaseActivity {

    private Timer timer1;
    private Timer timer2;
    private Socket tcpSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btn_mb)
    public void btn_mb() {
        startActivity(new Intent(this, MbtnActivity.class));
    }

















    private void initView() {

        try {
            tcpSocket = new Socket("192.168.1.5", 8899);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void ToastShow(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void ClientSend(final byte[] msgbyte) {
        timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {

                if (tcpSocket == null) {
                    ELog.d("======请先连接设备======");
                    return;
                }
                try {
                    tcpSocket.getOutputStream().write(msgbyte);
                    tcpSocket.getOutputStream().flush();
                    ELog.d("======发送数据成功======");
                } catch (IOException e) {
                    ELog.d("======发送数据IOException======");
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

                byte[] buffer = new byte[1024];
                int len = -1;

                try {
                    InputStream socketInputStream = tcpSocket.getInputStream();

                    while ((len = socketInputStream.read(buffer)) != -1) {
                        String data = new String(buffer, 0, len);
                        ELog.i("==tcp=====收到服务器的数据======" + data);


                    }
                    ELog.i("========客户端断开连接====");
                    socketInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1);

    }


}
