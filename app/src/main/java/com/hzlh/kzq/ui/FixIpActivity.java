package com.hzlh.kzq.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.Gson;
import com.hzlh.kzq.R;
import com.hzlh.kzq.data.DeviceInfo;
import com.hzlh.kzq.data.SendREQ;
import com.hzlh.kzq.data.SendREQRemain;
import com.hzlh.kzq.utils.ELog;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
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
    @BindView(R.id.et_jz_ycip)
    EditText et_jz_ycip;


    private Timer timer1;
    private Timer timer2;
    private DatagramSocket cUdpSocket;
    private byte[] bsMAC;
    private boolean isRun = false;
    private ProgressDialog progressDialog;

    Handler ipHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    et_jz_ip.setText(msg.obj.toString());
                    break;
                case 2:
                    et_jz_ycip.setText(msg.obj.toString());
                    break;
                case 3:
                    et_jz_wg.setText(msg.obj.toString());
                    break;
                case 4:
                    et_jz_ym.setText(msg.obj.toString());
                    break;
                case 5:
                    if (progressDialog != null) {
                        progressDialog.hide();
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_ip);
        ButterKnife.bind(this);

        try {
            cUdpSocket = new DatagramSocket(9999);
            isRun = true;
            getMsg();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
    }


    @OnClick(R.id.btn_search)
    public void btn_search() {
//        if (progressDialog != null) {
//            progressDialog.show();
//            progressDialog.setMessage("扫描中......");
//            progressDialog.setCanceledOnTouchOutside(false);
//        }
        Gson gson = new Gson();
//        String sendmsg = gson.toJson(new DeviceInfo(0, "EP_Tool", 0, 48898, "2.0.07e",
//                "Forbidden", "EPort", 1, "0.0.0.0", 0));
        String sendmsg = "{\"Zone\":\"EPort\",\"Version\":\"2.0.07e\",\"ConnectMode\":\"Forbidden\",\"Type\":\"EP_Tool\",\"ServAddr\":\"0.0.0.0\",\"ServEn\":1,\"ServPort\":48898,\"PasswordEn\":0,\"ClientEn\":0,\"Agent\":0}";
        ELog.d("======消息======" + sendmsg);
        ClientSend(sendmsg.getBytes());
//        byte[] msg = new byte[1];
//        ClientSend(msg);
    }

    @OnClick(R.id.btn_read)
    public void btn_read() {
//        if (bsMAC == null && et_jz_ip.getText().toString().isEmpty()) {
//            Toast.makeText(this, "请先扫描到矩阵IP", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (progressDialog != null) {
//            progressDialog.show();
//            progressDialog.setMessage("读取配置中......");
//            progressDialog.setCanceledOnTouchOutside(false);
//        }
//        byte[] msgbyte = new byte[11];
//        System.arraycopy(bsMAC, 0, msgbyte, 0, 6);
//        msgbyte[6] = (byte) Integer.parseInt("0A", 16);
//        msgbyte[7] = (byte) Integer.parseInt("FF", 16);
//        msgbyte[8] = (byte) Integer.parseInt("FF", 16);
//        msgbyte[9] = (byte) Integer.parseInt("FF", 16);
//        msgbyte[10] = (byte) Integer.parseInt("FF", 16);
//        ClientSend(msgbyte);

        Gson gson = new Gson();
        ArrayList<String> pl = new ArrayList<String>();
        pl.add("SYS");
        pl.add("UART");
        pl.add("SOCK");
        String sendmsg = gson.toJson(new SendREQ(1, pl,  10003));

        ELog.d("======消息======" + sendmsg);
        ClientSend(sendmsg.getBytes());


    }


    @OnClick(R.id.btn_fixsave)
    public void btn_fixsave() {
//        if (progressDialog != null) {
//            progressDialog.show();
//            progressDialog.setMessage("配置中......");
//            progressDialog.setCanceledOnTouchOutside(false);
//        }
//        // 配置 初始化....
//        byte[] msgbyte1 = new byte[11];
//        System.arraycopy(bsMAC, 0, msgbyte1, 0, 6);
//        msgbyte1[6] = (byte) Integer.parseInt("0C", 16);
//        msgbyte1[7] = (byte) Integer.parseInt("00", 16);
//        msgbyte1[8] = (byte) Integer.parseInt("FF", 16);
//        msgbyte1[9] = (byte) Integer.parseInt("FF", 16);
//        msgbyte1[10] = (byte) Integer.parseInt("FF", 16);
//        ClientSend(msgbyte1);
//
//
//        //配置 初始化....
//        byte[] msgbyte2 = new byte[11];
//        System.arraycopy(bsMAC, 0, msgbyte2, 0, 6);
//        msgbyte2[6] = (byte) Integer.parseInt("04", 16);
//        msgbyte2[7] = (byte) Integer.parseInt("04", 16);
//        msgbyte2[8] = (byte) Integer.parseInt("FF", 16);
//        msgbyte2[9] = (byte) Integer.parseInt("FF", 16);
//        msgbyte2[10] = (byte) Integer.parseInt("FF", 16);
//        ClientSend(msgbyte2);
//
//        // 修改 矩阵  IP地址
//        byte[] msgbyte3 = new byte[11];
//        System.arraycopy(bsMAC, 0, msgbyte3, 0, 6);
//        msgbyte3[6] = (byte) Integer.parseInt("01", 16);
//
//        String[] strs3 = et_jz_ip.getText().toString().split("\\.");
//        for (int i = 0; i < strs3.length; i++) {
//            msgbyte3[i + 7] = (byte) Integer.parseInt(Integer.toHexString(Integer.parseInt(strs3[i])), 16);
//        }
//        ClientSend(msgbyte3);
//
//        // 修改 远程  IP地址
//        byte[] msgbyte4 = new byte[11];
//        System.arraycopy(bsMAC, 0, msgbyte4, 0, 6);
//        msgbyte4[6] = (byte) Integer.parseInt("02", 16);
//        String[] strs4 = et_jz_ycip.getText().toString().split("\\.");
//        for (int i = 0; i < strs4.length; i++) {
//            msgbyte4[i + 7] = (byte) Integer.parseInt(Integer.toHexString(Integer.parseInt(strs4[i])), 16);
//        }
//        ClientSend(msgbyte4);
//
//        // 修改 网关  IP地址
//        byte[] msgbyte5 = new byte[11];
//        System.arraycopy(bsMAC, 0, msgbyte5, 0, 6);
//        msgbyte5[6] = (byte) Integer.parseInt("03", 16);
//        String[] strs5 = et_jz_wg.getText().toString().split("\\.");
//        for (int i = 0; i < strs5.length; i++) {
//            msgbyte5[i + 7] = (byte) Integer.parseInt(Integer.toHexString(Integer.parseInt(strs5[i])), 16);
//        }
//        ClientSend(msgbyte5);
//
//        // 修改 子网掩码  IP地址
//        byte[] msgbyte6 = new byte[11];
//        System.arraycopy(bsMAC, 0, msgbyte6, 0, 6);
//        msgbyte6[6] = (byte) Integer.parseInt("07", 16);
//        String[] strs6 = et_jz_ym.getText().toString().split("\\.");
//        for (int i = 0; i < strs6.length; i++) {
//            msgbyte6[i + 7] = (byte) Integer.parseInt(Integer.toHexString(Integer.parseInt(strs6[i])), 16);
//        }
//        ClientSend(msgbyte6);
//
//        //配置 完成.
//        byte[] msgbyte7 = new byte[11];
//        System.arraycopy(bsMAC, 0, msgbyte7, 0, 6);
//        msgbyte7[6] = (byte) Integer.parseInt("FF", 16);
//        msgbyte7[7] = (byte) Integer.parseInt("FF", 16);
//        msgbyte7[8] = (byte) Integer.parseInt("FF", 16);
//        msgbyte7[9] = (byte) Integer.parseInt("FF", 16);
//        msgbyte7[10] = (byte) Integer.parseInt("FF", 16);
//        ClientSend(msgbyte7);

        Gson gson = new Gson();
        String sendmsg = gson.toJson(new SendREQRemain(1, 1,  10003));

        ELog.d("======消息======" + sendmsg);
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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ELog.d("=======接收到==IP==消息===========" + datagramPacket.getAddress().toString().substring(1));
                    ELog.d("=======接收到消息长度===========" + datagramPacket.getLength());
                    String msg = new String(buf, 0, datagramPacket.getLength());
                    ELog.d("=======接收到消息=====length======" + msg.length());
                    ELog.d("=======接收到消息===========" + msg);

//
//                    if (datagramPacket.getLength() == 8) {
//                        // len=8    取中间件6个字节  为 mac地址
//                        bsMAC = new byte[6];
//                        System.arraycopy(buf, 1, bsMAC, 0, 6);
//
//                        Message msg = new Message();
//                        msg.obj = datagramPacket.getAddress().toString().substring(1);
//                        msg.what = 1;
//                        ipHandler.sendMessage(msg);
//                        ipHandler.sendEmptyMessage(5);
//                    }
//
//                    if (datagramPacket.getLength() == 256) {
//                        // len=256    取中间件40-43个字节  为 矩阵  IP地址
//                        // len=256    取中间件44-47个字节  为 远程  IP地址
//                        // len=256    取中间件48-51个字节  为 网关  IP地址
//                        // len=256    取中间件52-55个字节  为 子网掩码  IP地址
//
//                        String jzIpStr = Integer.parseInt(Integer.toHexString(buf[40] & 0xFF), 16) + "."
//                                + Integer.parseInt(Integer.toHexString(buf[41] & 0xFF), 16) + "."
//                                + Integer.parseInt(Integer.toHexString(buf[42] & 0xFF), 16) + "."
//                                + Integer.parseInt(Integer.toHexString(buf[43] & 0xFF), 16);
//
//                        Message msg1 = new Message();
//                        msg1.obj = jzIpStr;
//                        msg1.what = 1;
//                        ipHandler.sendMessage(msg1);
//
//                        String ycIpStr = Integer.parseInt(Integer.toHexString(buf[44] & 0xFF), 16) + "."
//                                + Integer.parseInt(Integer.toHexString(buf[45] & 0xFF), 16) + "."
//                                + Integer.parseInt(Integer.toHexString(buf[46] & 0xFF), 16) + "."
//                                + Integer.parseInt(Integer.toHexString(buf[47] & 0xFF), 16);
//
//                        Message msg2 = new Message();
//                        msg2.obj = ycIpStr;
//                        msg2.what = 2;
//                        ipHandler.sendMessage(msg2);
//
//                        String wgIpStr = Integer.parseInt(Integer.toHexString(buf[48] & 0xFF), 16) + "."
//                                + Integer.parseInt(Integer.toHexString(buf[49] & 0xFF), 16) + "."
//                                + Integer.parseInt(Integer.toHexString(buf[50] & 0xFF), 16) + "."
//                                + Integer.parseInt(Integer.toHexString(buf[51] & 0xFF), 16);
//
//                        Message msg3 = new Message();
//                        msg3.obj = wgIpStr;
//                        msg3.what = 3;
//                        ipHandler.sendMessage(msg3);
//
//                        String ymIpStr = Integer.parseInt(Integer.toHexString(buf[52] & 0xFF), 16) + "."
//                                + Integer.parseInt(Integer.toHexString(buf[53] & 0xFF), 16) + "."
//                                + Integer.parseInt(Integer.toHexString(buf[54] & 0xFF), 16) + "."
//                                + Integer.parseInt(Integer.toHexString(buf[55] & 0xFF), 16);
//
//                        Message msg4 = new Message();
//                        msg4.obj = ymIpStr;
//                        msg4.what = 4;
//                        ipHandler.sendMessage(msg4);
//                        ipHandler.sendEmptyMessage(5);
//                    }
//
//                    if (datagramPacket.getLength() == 4) {
//                        // len=4   FF 结束
//                        if (Integer.toHexString(buf[0] & 0xFF).equals("ff")) {
//                            ipHandler.sendEmptyMessage(5);
//                        }
//                        ELog.d("=======接收到消息==========="+Integer.toHexString(buf[0] & 0xFF));
//                    }

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
        if (cUdpSocket != null) {
            cUdpSocket.close();
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
