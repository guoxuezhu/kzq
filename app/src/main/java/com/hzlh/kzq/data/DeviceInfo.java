package com.hzlh.kzq.data;

public class DeviceInfo {

    private int Agent;
    private String Type;
    private String Version;
    private int ClientEn;
    private String HostName;
    private String CustomerID;
    private String AddressIP;
    private String Zone;
    private String MAC;
    private String Idle;
    private int UdpSupport;

    public DeviceInfo(int agent, String type, String version, int clientEn, String hostName, String customerID, String addressIP, String zone, String MAC, String idle, int udpSupport) {
        Agent = agent;
        Type = type;
        Version = version;
        ClientEn = clientEn;
        HostName = hostName;
        CustomerID = customerID;
        AddressIP = addressIP;
        Zone = zone;
        this.MAC = MAC;
        Idle = idle;
        UdpSupport = udpSupport;
    }

    public int getAgent() {
        return Agent;
    }

    public String getType() {
        return Type;
    }

    public String getVersion() {
        return Version;
    }

    public int getClientEn() {
        return ClientEn;
    }

    public String getHostName() {
        return HostName;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public String getAddressIP() {
        return AddressIP;
    }

    public String getZone() {
        return Zone;
    }

    public String getMAC() {
        return MAC;
    }

    public String getIdle() {
        return Idle;
    }

    public int getUdpSupport() {
        return UdpSupport;
    }

    public void setAgent(int agent) {
        Agent = agent;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public void setClientEn(int clientEn) {
        ClientEn = clientEn;
    }

    public void setHostName(String hostName) {
        HostName = hostName;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public void setAddressIP(String addressIP) {
        AddressIP = addressIP;
    }

    public void setZone(String zone) {
        Zone = zone;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public void setIdle(String idle) {
        Idle = idle;
    }

    public void setUdpSupport(int udpSupport) {
        UdpSupport = udpSupport;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "Agent=" + Agent +
                ", Type='" + Type + '\'' +
                ", Version='" + Version + '\'' +
                ", ClientEn=" + ClientEn +
                ", HostName='" + HostName + '\'' +
                ", CustomerID='" + CustomerID + '\'' +
                ", AddressIP='" + AddressIP + '\'' +
                ", Zone='" + Zone + '\'' +
                ", MAC='" + MAC + '\'' +
                ", Idle='" + Idle + '\'' +
                ", UdpSupport=" + UdpSupport +
                '}';
    }
}
