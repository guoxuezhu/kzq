package com.hzlh.kzq.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


import com.google.gson.Gson;
import com.hzlh.kzq.R;
import com.hzlh.kzq.data.SYSconfig;
import com.hzlh.kzq.utils.ELog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FixIpActivity extends BaseActivity {


    @BindView(R.id.et_jz_ip)
    EditText et_jz_ip;
    @BindView(R.id.et_jz_ym)
    EditText et_jz_ym;
    @BindView(R.id.et_jz_wg)
    EditText et_jz_wg;
    @BindView(R.id.et_jz_dns)
    EditText et_jz_dns;

    @BindView(R.id.rbtn_dhcp_ok)
    RadioButton rbtn_dhcp_ok;
    @BindView(R.id.rbtn_dhcp_no)
    RadioButton rbtn_dhcp_no;


    Handler ipHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    initView();
                    break;
                case 11:
                    ToastShow("修改成功");
                    break;
                case 12:
                    ToastShow("修改失败");
                    break;
            }
        }
    };


    private Timer timer1;
    private Timer timer2;
    private DatagramSocket cUdpSocket;
    private boolean isRun = false;
    private SYSconfig sySconfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_ip);
        ButterKnife.bind(this);

        try {
            cUdpSocket = new DatagramSocket(9999);
            isRun = true;
            getMsg();
            sendIPinfo();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        et_jz_ip.setText(sySconfig.getIpAddr());
        et_jz_ym.setText(sySconfig.getMask());
        et_jz_wg.setText(sySconfig.getGateWay());
        et_jz_dns.setText(sySconfig.getDns());
        if (sySconfig.getDhcpEn() == 0) {// 1动态   0静态
            rbtn_dhcp_no.setChecked(true);
        } else {
            rbtn_dhcp_ok.setChecked(true);
        }
    }

    private void ToastShow(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void sendIPinfo() {
        // {"PL":["SYS","UART","SOCK"],"JCMD":1,"CID":10003}
        String sendmsg = "{\"PL\":[\"SYS\"],\"JCMD\":1,\"CID\":10003}";
        ELog.d("======发送消息======" + sendmsg);
        ClientSend(sendmsg.getBytes());
    }

    @OnClick(R.id.btn_fixsave)
    public void btn_fixsave() {
        // {"PL":{"SYS":sySconfig},"JCMD":1,"CID":10005}
        // {"CID":10003,"JCMD":1,"Remain":1}
        sySconfig.setIpAddr(et_jz_ip.getText().toString().trim());
        sySconfig.setMask(et_jz_ym.getText().toString().trim());
        sySconfig.setGateWay(et_jz_wg.getText().toString().trim());
        sySconfig.setDns(et_jz_dns.getText().toString().trim());
        if (rbtn_dhcp_no.isChecked()) {
            sySconfig.setDhcpEn(0);
        } else {
            sySconfig.setDhcpEn(1);
        }
        Gson gson = new Gson();
        String sySconfigjson = gson.toJson(sySconfig);
        String sendmsg = "{\"PL\":{\"SYS\":" + sySconfigjson + "},\"JCMD\":1,\"CID\":10005}";
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
        if (timer2 == null) {
            timer2 = new Timer();
        }
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                //准备空的数据包用于存放数据。
                byte[] buf = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length); // 1024

                while (isRun) {
                    try {
                        cUdpSocket.receive(datagramPacket);
                        String msg = new String(buf, 0, datagramPacket.getLength());
                        ELog.d("=======接收到消息===========" + msg);
                        JSONObject json = new JSONObject(msg);
                        int cid = json.getInt("CID");
                        if (cid == 10004) {
                            JSONObject jsonPL = new JSONObject(json.getString("PL"));
                            String sys = jsonPL.getString("SYS");
                            if (sys != null) {
                                Gson gson = new Gson();
                                sySconfig = gson.fromJson(sys, SYSconfig.class);
                                ELog.d("=======接收到消息=====sySconfig======" + sySconfig.toString());
                                ipHandler.sendEmptyMessage(1);
                            }
                        } else if (cid == 10006) {
                            if (json.getInt("RC") == 0) {
                                ipHandler.sendEmptyMessage(11);
                            } else {
                                ipHandler.sendEmptyMessage(12);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
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
    public void onBackPressed() {
        finish();
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
        stopRun();
    }
}
