package com.hzlh.kzq.utils;

import android.os.Handler;

import com.hzlh.kzq.MyApplication;
import com.hzlh.kzq.data.DbDao.ChangjingDatasDao;
import com.hzlh.kzq.data.DbDao.DevicesDataDao;
import com.hzlh.kzq.data.DbDao.WgDatasDao;
import com.hzlh.kzq.data.model.ChangjingDatas;
import com.hzlh.kzq.data.model.DevicesData;
import com.hzlh.kzq.data.model.WgDatas;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

public class UDPUtil {

    private static DatagramSocket udpSocket;
    private static Handler mhandler, deviceHandler, changjingHandler;
    private static boolean isRun = false;

    private static void init() {
        try {
            udpSocket = new DatagramSocket(12001);
            isRun = true;
        } catch (Exception e) {
            udpSocket = null;
            e.printStackTrace();
        }
    }

    public static void sendMsg(String wg_ip, String ml, int port) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                sendUdpMsg(wg_ip, StringToBytes(ml), port);
            }
        }.start();
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

    private static void sendUdpMsg(String ip, byte[] msgbyte, int port) {
        if (udpSocket == null) {
            init();
        }
        try {
            DatagramPacket dp = new DatagramPacket(msgbyte, msgbyte.length);
            dp.setSocketAddress(new InetSocketAddress(ip, port));
            udpSocket.send(dp);//发送一条广播信息
            ELog.i("=======发送一条广播信息========");
        } catch (Exception e) {
//            udpSocket = null;
//            isRun = false;
            ELog.i("=======发送一条广播信息===Exception=====");
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
                while (isRun) {
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
                        String msgType = Integer.toHexString(recePacket.getData()[3] & 0xFF);
                        ELog.i("=======接收数据包===msgType=====" + msgType);
                        if (msgType.equals("b0") || msgType.equals("a1")) {
                            WgDatasDao wgDatasDao = MyApplication.getDaoSession().getWgDatasDao();
                            String wg_name = "";
                            try {
                                wg_name = new String(recePacket.getData(), 14, 20, "GBK").trim();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            ELog.i("=======接收数据包===wg_name=======" + wg_name);
                            String wg_ip = Integer.parseInt(Integer.toHexString(recePacket.getData()[34] & 0xFF), 16) + "."
                                    + Integer.parseInt(Integer.toHexString(recePacket.getData()[35] & 0xFF), 16) + "."
                                    + Integer.parseInt(Integer.toHexString(recePacket.getData()[36] & 0xFF), 16) + "."
                                    + Integer.parseInt(Integer.toHexString(recePacket.getData()[37] & 0xFF), 16);
                            ELog.i("=======接收数据包===wg_ip=======" + wg_ip);

                            String wg_wl_zwym = Integer.parseInt(Integer.toHexString(recePacket.getData()[38] & 0xFF), 16) + "."
                                    + Integer.parseInt(Integer.toHexString(recePacket.getData()[39] & 0xFF), 16) + "."
                                    + Integer.parseInt(Integer.toHexString(recePacket.getData()[40] & 0xFF), 16) + "."
                                    + Integer.parseInt(Integer.toHexString(recePacket.getData()[41] & 0xFF), 16);
                            ELog.i("=======接收数据包===wg_wl_zwym=======" + wg_wl_zwym);
                            String wg_wl_wgip = Integer.parseInt(Integer.toHexString(recePacket.getData()[42] & 0xFF), 16) + "."
                                    + Integer.parseInt(Integer.toHexString(recePacket.getData()[43] & 0xFF), 16) + "."
                                    + Integer.parseInt(Integer.toHexString(recePacket.getData()[44] & 0xFF), 16) + "."
                                    + Integer.parseInt(Integer.toHexString(recePacket.getData()[45] & 0xFF), 16);
                            ELog.i("=======接收数据包===wg_wl_wgip=======" + wg_wl_wgip);
                            String wg_mac = Integer.toHexString(recePacket.getData()[46] & 0xFF) + "."
                                    + Integer.toHexString(recePacket.getData()[47] & 0xFF) + "."
                                    + Integer.toHexString(recePacket.getData()[48] & 0xFF) + "."
                                    + Integer.toHexString(recePacket.getData()[49] & 0xFF) + "."
                                    + Integer.toHexString(recePacket.getData()[50] & 0xFF) + "."
                                    + Integer.toHexString(recePacket.getData()[51] & 0xFF);
                            ELog.i("=======接收数据包===wg_mac=======" + wg_mac);
                            String wg_swj_ip = Integer.parseInt(Integer.toHexString(recePacket.getData()[52] & 0xFF), 16) + "."
                                    + Integer.parseInt(Integer.toHexString(recePacket.getData()[53] & 0xFF), 16) + "."
                                    + Integer.parseInt(Integer.toHexString(recePacket.getData()[54] & 0xFF), 16) + "."
                                    + Integer.parseInt(Integer.toHexString(recePacket.getData()[55] & 0xFF), 16);
                            ELog.i("=======接收数据包===wg_swj_ip=======" + wg_swj_ip);
                            String hexPort = Integer.toHexString(recePacket.getData()[56] & 0xFF) + Integer.toHexString(recePacket.getData()[57] & 0xFF);
                            int wg_swj_port = Integer.parseInt(hexPort, 16);
                            ELog.i("=======接收数据包===wg_swj_port=======" + wg_swj_port);
                            List<WgDatas> wgDatas = wgDatasDao.queryBuilder()
                                    .where(WgDatasDao.Properties.Wg_ip.eq(wg_ip))
                                    .list();
                            if (wgDatas.size() != 0) {
                                wgDatasDao.update(new WgDatas(wgDatas.get(0).wg_id, wg_name, wg_ip, wg_wl_zwym,
                                        wg_wl_wgip, wg_mac, wg_swj_ip, wg_swj_port + "", 1));
                            } else {
                                wgDatasDao.insert(new WgDatas(null, wg_name, wg_ip, wg_wl_zwym,
                                        wg_wl_wgip, wg_mac, wg_swj_ip, wg_swj_port + "", 1));
                            }
                            if (mhandler != null) {
                                mhandler.sendEmptyMessage(1001);
                            }
                        }
//                        if (mhandler != null && msgType.equals("a0")) {
//                            WgDatasDao wgDatasDao = MyApplication.getDaoSession().getWgDatasDao();
//                            List<WgDatas> wgDatas = wgDatasDao.queryBuilder()
//                                    .where(WgDatasDao.Properties.Wg_ip.eq(recePacket.getAddress().toString().substring(1)))
//                                    .list();
//                            if (wgDatas.size() != 0) {
//                                WgDatas wgData = wgDatas.get(0);
//                                wgData.setWg_status(1);
//                                wgDatasDao.update(wgData);
//                            } else {
////                                wgDatasDao.insert(new WgDatas(null, recePacket.getAddress().toString().substring(1), "网关", "", 1));
//                            }
//                            mhandler.sendEmptyMessage(1001);
//                        }
                        if (deviceHandler != null && msgType.equals("a2")) {
                            String device_numer = Integer.toHexString(recePacket.getData()[14] & 0xFF);
                            ELog.i("=======接收数据包===device_numer=====" + device_numer);
                            DevicesDataDao devicesDataDao = MyApplication.getDaoSession().getDevicesDataDao();
                            devicesDataDao.deleteAll();
                            int count = Integer.valueOf(device_numer);
                            for (int i = 0; i < count; i++) {
                                String device_type = Integer.toHexString(recePacket.getData()[i * 66 + 15] & 0xFF);
                                String device_id = Integer.toHexString(recePacket.getData()[i * 66 + 17] & 0xFF);
                                ELog.i("=======接收数据包===device_type==111===" + device_type);
                                ELog.i("=======接收数据包===device_id===222====" + device_id);
                                String device_name = "";
                                try {
                                    device_name = new String(recePacket.getData(), i * 66 + 21, 20, "GBK").trim();
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                ELog.i("=======接收数据包===device_name===333====" + device_name);

                                if (device_type.equals("1")) {
                                    devicesDataDao.insert(new DevicesData(Long.parseLong(device_id, 16), device_name, device_type, "1",
                                            Integer.toHexString(recePacket.getData()[i * 66 + 52] & 0xFF), Integer.toHexString(recePacket.getData()[i * 66 + 56] & 0xFF),
                                            Integer.toHexString(recePacket.getData()[i * 66 + 60] & 0xFF), Integer.toHexString(recePacket.getData()[i * 66 + 64] & 0xFF),
                                            Integer.toHexString(recePacket.getData()[i * 66 + 68] & 0xFF), Integer.toHexString(recePacket.getData()[i * 66 + 72] & 0xFF),
                                            Integer.toHexString(recePacket.getData()[i * 66 + 76] & 0xFF), Integer.toHexString(recePacket.getData()[i * 66 + 80] & 0xFF)));
                                } else if (device_type.equals("2")) {
                                    byte[] buffer = new byte[4];
                                    String[] strArray = new String[8];
                                    String value1 = Integer.toHexString(recePacket.getData()[i * 66 + 52] & 0xFF);
                                    strArray[0] = value1;
                                    for (int n = 0; n < 7; n++) {
                                        System.arraycopy(recePacket.getData(), i * 66 + 53 + n * 4, buffer, 0, 4);
                                        int accum = 0;
                                        for (int shiftBy = 0; shiftBy < 4; shiftBy++) {
                                            accum |= (buffer[shiftBy] & 0xff) << shiftBy * 8;
                                        }
                                        float value = Float.intBitsToFloat(accum);
                                        ELog.i("==========value==1111=====" + value);
                                        if (Float.isNaN(value)) {
                                            strArray[1 + n] = "0";
                                        } else {
                                            strArray[1 + n] = value + "";
                                        }
                                    }
                                    ELog.i("==========value==strArray=====" + Arrays.toString(strArray));
                                    devicesDataDao.insert(new DevicesData(Long.parseLong(device_id, 16), device_name, device_type, "1",
                                            strArray[0], strArray[1], strArray[2], strArray[3], strArray[4], strArray[5], strArray[6], strArray[7]));
                                } else if (device_type.equals("3")) {
                                    devicesDataDao.insert(new DevicesData(Long.parseLong(device_id, 16), device_name, device_type, "1",
                                            Integer.toHexString(recePacket.getData()[i * 66 + 52] & 0xFF), Integer.toHexString(recePacket.getData()[i * 66 + 56] & 0xFF),
                                            Integer.toHexString(recePacket.getData()[i * 66 + 60] & 0xFF), Integer.toHexString(recePacket.getData()[i * 66 + 64] & 0xFF),
                                            Integer.toHexString(recePacket.getData()[i * 66 + 68] & 0xFF), Integer.toHexString(recePacket.getData()[i * 66 + 72] & 0xFF),
                                            Integer.toHexString(recePacket.getData()[i * 66 + 76] & 0xFF), Integer.toHexString(recePacket.getData()[i * 66 + 80] & 0xFF)));
                                } else if (device_type.equals("4")) {
                                    devicesDataDao.insert(new DevicesData(Long.parseLong(device_id, 16), device_name, device_type, "1",
                                            Integer.toHexString(recePacket.getData()[i * 66 + 52] & 0xFF), Integer.toHexString(recePacket.getData()[i * 66 + 56] & 0xFF),
                                            Integer.toHexString(recePacket.getData()[i * 66 + 60] & 0xFF), Integer.toHexString(recePacket.getData()[i * 66 + 64] & 0xFF),
                                            Integer.toHexString(recePacket.getData()[i * 66 + 68] & 0xFF), Integer.toHexString(recePacket.getData()[i * 66 + 72] & 0xFF),
                                            Integer.toHexString(recePacket.getData()[i * 66 + 76] & 0xFF), Integer.toHexString(recePacket.getData()[i * 66 + 80] & 0xFF)));

                                } else {
                                    devicesDataDao.insert(new DevicesData(Long.parseLong(device_id, 16), device_name, device_type, "1",
                                            "", "", "", "", "", "", "", ""));
                                }

                            }
                            ELog.i("=======接收数据包===devicesDataDao=====" + devicesDataDao.loadAll().toString());
                            deviceHandler.sendEmptyMessage(1002);
                        }

                        if (msgType.equals("a4")) {
                            String device_id = Integer.toHexString(recePacket.getData()[16] & 0xFF);
                            String value1 = Integer.toHexString(recePacket.getData()[51] & 0xFF);
                            ELog.i("=======接收数据包===a4=====" + device_id);
                            ELog.i("=======接收数据包===a4=====" + value1);
                            DevicesDataDao devicesDataDao = MyApplication.getDaoSession().getDevicesDataDao();
                            DevicesData devicesData = devicesDataDao.load(Long.parseLong(device_id, 16));
                            if (devicesData != null) {
                                devicesData.setValue_1(value1);
                                devicesDataDao.update(devicesData);
                            }
                        }

                        if (changjingHandler != null && msgType.equals("a7")) {
                            ChangjingDatasDao changjingDatasDao = MyApplication.getDaoSession().getChangjingDatasDao();
                            changjingDatasDao.deleteAll();
                            // CC0101A74C4800A75500000000 06 05 01 02 05 04 06 0A0DCD
                            String cjCount = Integer.toHexString(recePacket.getData()[14] & 0xFF);
                            Integer cjSize = Integer.valueOf(cjCount);
                            for (int n = 0; n < cjSize; n++) {
                                String cjId = Integer.toHexString(recePacket.getData()[15 + n] & 0xFF);
                                changjingDatasDao.insert(new ChangjingDatas(Long.parseLong(cjId, 16), "场景"));
                            }
                            ELog.i("=======接收数据包===changjingDatasDao=====" + changjingDatasDao.loadAll().toString());
                            changjingHandler.sendEmptyMessage(1003);
                        }

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

    public static void closeMainHandler() {
        mhandler = null;
    }

    public static void setDeviceHandler(Handler handler) {
        deviceHandler = handler;
    }

    public static void closeDeviceHandler() {
        deviceHandler = null;
    }

    public static void stopReadMsg() {
        isRun = false;
        if (udpSocket != null) {
            udpSocket.close();
            udpSocket = null;
        }
    }

    public static void setChangjingHandler(Handler handler) {
        changjingHandler = handler;
    }

    public static void closeChangjingHandler() {
        changjingHandler = null;
    }
}
