package com.hzlh.kzq.data;

public class DeviceInfo {

    private int Agent;
    private String Type;
    private int PasswordEn;
    private int ServPort;
    private String Version;
    private String ConnectMode;
    private String Zone;
    private int ServEn;
    private String ServAddr;
    private int ClientEn;

    public DeviceInfo(int agent, String type, int passwordEn, int servPort, String version, String connectMode, String zone, int servEn, String servAddr, int clientEn) {
        Agent = agent;
        Type = type;
        PasswordEn = passwordEn;
        ServPort = servPort;
        Version = version;
        ConnectMode = connectMode;
        Zone = zone;
        ServEn = servEn;
        ServAddr = servAddr;
        ClientEn = clientEn;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "Agent=" + Agent +
                ", Type='" + Type + '\'' +
                ", PasswordEn=" + PasswordEn +
                ", ServPort=" + ServPort +
                ", Version='" + Version + '\'' +
                ", ConnectMode='" + ConnectMode + '\'' +
                ", Zone='" + Zone + '\'' +
                ", ServEn=" + ServEn +
                ", ServAddr='" + ServAddr + '\'' +
                ", ClientEn=" + ClientEn +
                '}';
    }
}
