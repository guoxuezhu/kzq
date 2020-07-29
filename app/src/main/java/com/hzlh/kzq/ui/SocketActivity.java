package com.hzlh.kzq.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hzlh.kzq.R;
import com.hzlh.kzq.data.SockInfo;
import com.hzlh.kzq.utils.ELog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class SocketActivity extends BaseActivity {

    @BindView(R.id.tv_socket_name)
    TextView tv_socket_name;
    @BindView(R.id.et_s_ip)
    EditText et_s_ip;
    @BindView(R.id.et_s_port)
    EditText et_s_port;
    @BindView(R.id.et_c_port)
    EditText et_c_port;

    Handler sHandler = new Handler() {
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
    private List<SockInfo> sockInfos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        ButterKnife.bind(this);

        try {
            cUdpSocket = new DatagramSocket(9999);
            isRun = true;
            getMsg();
            sendSOCKinfo();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        sockInfos = new ArrayList<SockInfo>();

    }

    private void initView() {
        if (sockInfos.size() != 0) {
            tv_socket_name.setText(sockInfos.get(0).getName());
            et_s_ip.setText(sockInfos.get(0).getServer());
            et_s_port.setText(sockInfos.get(0).getServerPort() + "");
            et_c_port.setText(sockInfos.get(0).getLocalPort() + "");
        }
    }


    private void ToastShow(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void sendSOCKinfo() {
        // {"PL":["SYS","UART","SOCK"],"JCMD":1,"CID":10003}
        String sendmsg = "{\"PL\":[\"SOCK\"],\"JCMD\":1,\"CID\":10003}";
        ELog.d("======发送消息======" + sendmsg);
        ClientSend(sendmsg.getBytes());
    }

    private void sendRemainSOCKinfo() {
        // {"CID":10003,"JCMD":1,"Remain":1}
        String sendmsg = "{\"CID\":10003,\"JCMD\":1,\"Remain\":1}";
        ELog.d("======发送消息======" + sendmsg);
        ClientSend(sendmsg.getBytes());
    }

    @OnClick(R.id.btn_socksave)
    public void btn_socksave() {


        SockInfo sockInfo = sockInfos.get(0);
        sockInfo.setServer(et_s_ip.getText().toString().trim());
        sockInfo.setServerPort(Integer.valueOf(et_s_port.getText().toString().trim()));
        sockInfo.setLocalPort(Integer.valueOf(et_c_port.getText().toString().trim()));

        Gson gson = new Gson();
        String sockInfojson = gson.toJson(sockInfo);
        String sendmsg = "{\"PL\":{\"SOCK\":" + sockInfojson + "},\"JCMD\":1,\"CID\":10005}";
        ELog.d("======发送消息======" + sendmsg);
        ClientSend(sendmsg.getBytes());


    }


    private void ClientSend(final byte[] msgbyte) {
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
                        String msg = new String(buf, 0, datagramPacket.getLength());
                        ELog.d("=======接收到消息===========" + msg);
                        JSONObject json = new JSONObject(msg);
                        int cid = json.getInt("CID");
                        if (cid == 10004) {
                            JSONObject jsonPL = new JSONObject(json.getString("PL"));
                            String sock = jsonPL.getString("SOCK");
                            if (sock != null) {
                                Gson gson = new Gson();
                                JSONArray sockJsonArray = new JSONArray(sock);
                                SockInfo sockInfo = gson.fromJson(sockJsonArray.get(0).toString(), SockInfo.class);
                                sockInfos.add(sockInfo);
                                ELog.d("=======接收到消息=====sockInfos======" + sockInfos.toString());
                            }

                            if (json.getInt("Remain") == 1) {
                                sendRemainSOCKinfo();
                            } else if (json.getInt("Remain") == 0) {
                                sHandler.sendEmptyMessage(1);
                            }

                        } else if (cid == 10006) {
                            if (json.getInt("RC") == 0) {
                                sHandler.sendEmptyMessage(11);
                            } else {
                                sHandler.sendEmptyMessage(12);
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
