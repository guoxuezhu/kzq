package com.hzlh.kzq.ui;

import android.content.Intent;
import android.os.Bundle;

import com.hzlh.kzq.R;
import com.hzlh.kzq.utils.ELog;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MbtnActivity extends BaseActivity {

    private Socket tcpSocket;
    private Timer timer1;
    private Timer timer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbtn);
        ButterKnife.bind(this);



        socketInit();

    }

    private void socketInit() {
        timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    tcpSocket = new Socket("192.168.1.113", 8899);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 1);
    }


    @OnClick(R.id.btn_mbcl_0)
    public void btn_mbcl_0() {
        String sendmsg = "{\"cmd\":\"wc\",\"am\":\"0x02\",\"addr\":\"0x81f0\",\"epid\":\"1\",\"action\":\"0\",\"pct\":\"50\",\"seq\":\"2\",\"fcs\":\"E41EFF78A4A1BD21F0BDF01FDDF4C264\"}";
        ELog.d("======发送消息======" + sendmsg);
        ClientSend(sendmsg.getBytes());
    }

    @OnClick(R.id.btn_mbcl_1)
    public void btn_mbcl_1() {
        String sendmsg = "{\"cmd\":\"wc\",\"am\":\"0x02\",\"addr\":\"0x81f0\",\"epid\":\"1\",\"action\":\"1\",\"pct\":\"50\",\"seq\":\"2\",\"fcs\":\"E41EFF78A4A1BD21F0BDF01FDDF4C264\"}";
        ELog.d("======发送消息======" + sendmsg);
        ClientSend(sendmsg.getBytes());
    }

    @OnClick(R.id.btn_mbcl_2)
    public void btn_mbcl_2() {
        String sendmsg = "{\"cmd\":\"wc\",\"am\":\"0x02\",\"addr\":\"0x81f0\",\"epid\":\"1\",\"action\":\"2\",\"pct\":\"50\",\"seq\":\"2\",\"fcs\":\"E41EFF78A4A1BD21F0BDF01FDDF4C264\"}";
        ELog.d("======发送消息======" + sendmsg);
        ClientSend(sendmsg.getBytes());
    }

    @OnClick(R.id.btn_mb_2)
    public void btn_mb_2() {
        String mbaddr = "0xdc61";
        btnSendMsg(mbaddr);
    }

    private void btnSendMsg(String mbaddr) {
        String sendmsg = "{\"cmd\":\"onoff\",\"am\":\"0x02\",\"addr\":\"" + mbaddr + "\",\"epid\":\"2\",\"action\":\"2\",\"seq\":\"2\",\"fcs\":\"E41EFF78A4A1BD21F0BDF01FDDF4C264\"}";
        ELog.d("======发送消息======" + sendmsg);
        ClientSend(sendmsg.getBytes());
    }

    @OnClick(R.id.btn_mb_3)
    public void btn_mb_3() {
        String sendmsg = "{\"cmd\":\"onoff\",\"am\":\"0x02\",\"addr\":\"0x17a2\",\"epid\":\"1\",\"action\":\"2\",\"seq\":\"2\",\"fcs\":\"E41EFF78A4A1BD21F0BDF01FDDF4C264\"}";
        ELog.d("======发送消息======" + sendmsg);
        ClientSend(sendmsg.getBytes());
    }

    @OnClick(R.id.btn_mb_4)
    public void btn_mb_4() {
        String sendmsg = "{\"cmd\":\"onoff\",\"am\":\"0x02\",\"addr\":\"0xdc61\",\"epid\":\"4\",\"action\":\"2\",\"seq\":\"2\",\"fcs\":\"E41EFF78A4A1BD21F0BDF01FDDF4C264\"}";
        ELog.d("======发送消息======" + sendmsg);
        ClientSend(sendmsg.getBytes());
    }


    private void ClientSend(final byte[] msgbyte) {
        if (timer1 == null) {
            timer1 = new Timer();
        }
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