package com.hzlh.kzq.ui;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.hzlh.kzq.R;
import com.hzlh.kzq.utils.ELog;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.et_wg_ip)
    EditText et_wg_ip;


    private DatagramSocket cUdpSocket;
    private Timer timer1;
//    private boolean isRun = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        try {
            cUdpSocket = new DatagramSocket(10101);
//            isRun = true;
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    @OnCheckedChanged(R.id.btn_cl)
    public void btn_cl(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            ClientSend(StringToBytes("4C4801A9010000000100010A0D"));
        } else {
            ClientSend(StringToBytes("4C4801A9010000000100020A0D"));
        }
    }

    @OnCheckedChanged(R.id.btn_hbd)
    public void btn_hbd(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            ClientSend(StringToBytes("4C4801A9010000000100030A0D"));
        } else {
            ClientSend(StringToBytes("4C4801A9010000000100040A0D"));
        }
    }


    @OnCheckedChanged(R.id.btn_jsd)
    public void btn_jsd(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            ClientSend(StringToBytes("4C4801A9010000000100050A0D"));
        } else {
            ClientSend(StringToBytes("4C4801A9010000000100060A0D"));
        }
    }

//    @OnCheckedChanged(R.id.checkbox_jsd)
//    public void checkbox_jsd(CompoundButton buttonView, boolean isChecked) {
//        if (isChecked) {
//            ELog.d("======isChecked===11===" + isChecked);
//        } else {
//            ELog.d("======isChecked===22===" + isChecked);
//        }
//    }

    @OnCheckedChanged(R.id.btn_cj)
    public void btn_cj(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            ClientSend(StringToBytes("4C4801A9010000000100070A0D"));
        } else {
            ClientSend(StringToBytes("4C4801A9010000000100080A0D"));
        }
    }


    public static byte[] StringToBytes(String str) {
        if (str.length() % 2 == 1) {   //是奇数
            return null;
        }
        try {
            byte[] bytes = new byte[str.length() / 2];
            for (int i = 0; i < str.length(); i = i + 2) {
                bytes[i / 2] = (byte) Integer.parseInt(str.substring(i, i + 2), 16);
            }
            return bytes;
        } catch (Exception e) {
            return null;
        }

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
                dp.setSocketAddress(new InetSocketAddress(et_wg_ip.getText().toString(), 10101));
                try {
                    cUdpSocket.send(dp);//发送一条广播信息
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 1);
    }

    private void stopRun() {
//        isRun = false;
        if (cUdpSocket != null && !cUdpSocket.isClosed()) {
            cUdpSocket.close();
            cUdpSocket.disconnect();
        }
        if (timer1 != null) {
            timer1.cancel();
        }
//        if (timer2 != null) {
//            timer2.cancel();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopRun();
    }


}