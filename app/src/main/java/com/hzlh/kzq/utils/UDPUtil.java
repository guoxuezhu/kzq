package com.hzlh.kzq.utils;

import android.os.Handler;

import com.hzlh.kzq.MyApplication;
import com.hzlh.kzq.data.DbDao.WgDatasDao;
import com.hzlh.kzq.data.model.WgDatas;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.List;

public class UDPUtil {

    private static DatagramSocket udpSocket;
    private static Handler mhandler;

    private static void init() {
        try {
            udpSocket = new DatagramSocket(12001);
        } catch (Exception e) {
            udpSocket = null;
            e.printStackTrace();
        }
    }

    public static void doWangguan(String ml) {
        // 5-1-1 5-10-1
        String[] mls = ml.split("-");
        if (mls.length != 3) {
            return;
        }

        String hex = Integer.toHexString(Integer.valueOf(mls[1]));
        if (hex.length() == 1) {
            hex = "0" + hex;
        }
        sendUdpMsg("192.168.0.226", StringToBytes("4C4801A9010000000100" + hex + "0A0D"));
    }

    private static byte[] StringToBytes(String str) {
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

    private static void sendUdpMsg(String ip, byte[] msgbyte) {
        if (udpSocket == null) {
            init();
        }
        try {
            DatagramPacket dp = new DatagramPacket(msgbyte, msgbyte.length);
            dp.setSocketAddress(new InetSocketAddress(ip, 10101));
            udpSocket.send(dp);//发送一条广播信息
        } catch (Exception e) {
            udpSocket = null;
            e.printStackTrace();
        }
    }

    public static void startReadUdpMsg() {
        if (udpSocket == null) {
            init();
        }
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    byte[] receBuf = new byte[1024];
                    try {
                        DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
                        udpSocket.receive(recePacket);
                        String ret = "";
                        for (int j = 0; j < recePacket.getLength(); j++) {
                            String hex = Integer.toHexString(recePacket.getData()[j] & 0xFF);
                            if (hex.length() == 1) {
                                hex = "0" + hex;
                            }
                            ret += hex.toUpperCase();
                        }
                        ELog.i("=======接收数据包===ret==111===" + ret);
                        ELog.i("=======接收数据包===ret==222===" + ret.length());
                        ELog.i("=======接收数据包===ip=====" + recePacket.getAddress().toString().substring(1));
                        ELog.i("=======接收数据包===getLength=====" + recePacket.getLength());
                        WgDatasDao wgDatasDao = MyApplication.getDaoSession().getWgDatasDao();
                        List<WgDatas> wgDatas = wgDatasDao.queryBuilder()
                                .where(WgDatasDao.Properties.Wg_ip.eq(recePacket.getAddress().toString().substring(1)))
                                .list();
                        if (wgDatas.size() != 0) {
                            WgDatas wgData = wgDatas.get(0);
                            wgData.setWg_status(1);
                            wgDatasDao.update(wgData);
                        } else {
                            wgDatasDao.insert(new WgDatas(null, recePacket.getAddress().toString().substring(1), "网关", "", 1));
                        }
                        if (mhandler != null) {
                            mhandler.sendEmptyMessage(1001);
                        }


//                        WuangguanInfoDao wangguandata = MyApplication.getDaoSession().getWuangguanInfoDao();
//                        List<WuangguanInfo> wgData = wangguandata.queryBuilder()
//                                .where(WuangguanInfoDao.Properties.Wg_ip.eq(recePacket.getAddress().toString().substring(1)),
//                                        WuangguanInfoDao.Properties.Wg_status.eq(1))
//                                .list();
//                        if (wgData.size() != 0 && recePacket.getLength() == 22) {
//                            // CC0101 AC 4C48FFAC6802000000 05 0400 02 01 01 0A0DCD
//                            ELog.i("=====接收数据包====ret===1===" + ret.substring(6, 8));
//                            ELog.i("=====接收数据包====ret===2===" + ret.substring(34, 36));
//                            ELog.i("=====接收数据包====ret===3===" + ret.substring(36, 38));
//                            if (ret.substring(6, 8).equals("AC")) {
//                                String wgbtnStatus = "WGBTN;" + ret.substring(34, 36) + ";" + ret.substring(36, 38);
//                                SerialPortUtil.sendMsg1(wgbtnStatus.getBytes());
//                            }
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static void setMainHandler(Handler handler) {
        mhandler = handler;
    }
}
