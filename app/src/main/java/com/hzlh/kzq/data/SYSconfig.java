package com.hzlh.kzq.data;

public class SYSconfig {

    private String User;
    private String Password;
    private Integer DhcpEn;
    private String IpAddr;
    private String GateWay;
    private String Mask;
    private String Dns;
    private String HostName;
    private Integer TelnetEn;
    private Integer TelnetPort;
    private Integer TelnetEcho;
    private Integer WebEn;
    private Integer WebPort;
    private Integer Ipv6En;
    private String Ipv6Addr;
    private Integer Ipv6DhcpEn;
    private Integer ntpEn;
    private String ntpServer;
    private Integer ntpPort;
    private Integer ntpGMT;
    private Integer Longitude;
    private Integer Latitude;
    private String Lang;


    public SYSconfig(String user, String password, Integer dhcpEn, String ipAddr, String gateWay, String mask, String dns, String hostName, Integer telnetEn, Integer telnetPort, Integer telnetEcho, Integer webEn, Integer webPort, Integer ipv6En, String ipv6Addr, Integer ipv6DhcpEn, Integer ntpEn, String ntpServer, Integer ntpPort, Integer ntpGMT, Integer longitude, Integer latitude, String lang) {
        User = user;
        Password = password;
        DhcpEn = dhcpEn;
        IpAddr = ipAddr;
        GateWay = gateWay;
        Mask = mask;
        Dns = dns;
        HostName = hostName;
        TelnetEn = telnetEn;
        TelnetPort = telnetPort;
        TelnetEcho = telnetEcho;
        WebEn = webEn;
        WebPort = webPort;
        Ipv6En = ipv6En;
        Ipv6Addr = ipv6Addr;
        Ipv6DhcpEn = ipv6DhcpEn;
        this.ntpEn = ntpEn;
        this.ntpServer = ntpServer;
        this.ntpPort = ntpPort;
        this.ntpGMT = ntpGMT;
        Longitude = longitude;
        Latitude = latitude;
        Lang = lang;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Integer getDhcpEn() {
        return DhcpEn;
    }

    public void setDhcpEn(Integer dhcpEn) {
        DhcpEn = dhcpEn;
    }

    public String getIpAddr() {
        return IpAddr;
    }

    public void setIpAddr(String ipAddr) {
        IpAddr = ipAddr;
    }

    public String getGateWay() {
        return GateWay;
    }

    public void setGateWay(String gateWay) {
        GateWay = gateWay;
    }

    public String getMask() {
        return Mask;
    }

    public void setMask(String mask) {
        Mask = mask;
    }

    public String getDns() {
        return Dns;
    }

    public void setDns(String dns) {
        Dns = dns;
    }

    public String getHostName() {
        return HostName;
    }

    public void setHostName(String hostName) {
        HostName = hostName;
    }

    public Integer getTelnetEn() {
        return TelnetEn;
    }

    public void setTelnetEn(Integer telnetEn) {
        TelnetEn = telnetEn;
    }

    public Integer getTelnetPort() {
        return TelnetPort;
    }

    public void setTelnetPort(Integer telnetPort) {
        TelnetPort = telnetPort;
    }

    public Integer getTelnetEcho() {
        return TelnetEcho;
    }

    public void setTelnetEcho(Integer telnetEcho) {
        TelnetEcho = telnetEcho;
    }

    public Integer getWebEn() {
        return WebEn;
    }

    public void setWebEn(Integer webEn) {
        WebEn = webEn;
    }

    public Integer getWebPort() {
        return WebPort;
    }

    public void setWebPort(Integer webPort) {
        WebPort = webPort;
    }

    public Integer getIpv6En() {
        return Ipv6En;
    }

    public void setIpv6En(Integer ipv6En) {
        Ipv6En = ipv6En;
    }

    public String getIpv6Addr() {
        return Ipv6Addr;
    }

    public void setIpv6Addr(String ipv6Addr) {
        Ipv6Addr = ipv6Addr;
    }

    public Integer getIpv6DhcpEn() {
        return Ipv6DhcpEn;
    }

    public void setIpv6DhcpEn(Integer ipv6DhcpEn) {
        Ipv6DhcpEn = ipv6DhcpEn;
    }

    public Integer getNtpEn() {
        return ntpEn;
    }

    public void setNtpEn(Integer ntpEn) {
        this.ntpEn = ntpEn;
    }

    public String getNtpServer() {
        return ntpServer;
    }

    public void setNtpServer(String ntpServer) {
        this.ntpServer = ntpServer;
    }

    public Integer getNtpPort() {
        return ntpPort;
    }

    public void setNtpPort(Integer ntpPort) {
        this.ntpPort = ntpPort;
    }

    public Integer getNtpGMT() {
        return ntpGMT;
    }

    public void setNtpGMT(Integer ntpGMT) {
        this.ntpGMT = ntpGMT;
    }

    public Integer getLongitude() {
        return Longitude;
    }

    public void setLongitude(Integer longitude) {
        Longitude = longitude;
    }

    public Integer getLatitude() {
        return Latitude;
    }

    public void setLatitude(Integer latitude) {
        Latitude = latitude;
    }

    public String getLang() {
        return Lang;
    }

    public void setLang(String lang) {
        Lang = lang;
    }

    @Override
    public String toString() {
        return "SYSconfig{" +
                "User='" + User + '\'' +
                ", Password='" + Password + '\'' +
                ", DhcpEn=" + DhcpEn +
                ", IpAddr='" + IpAddr + '\'' +
                ", GateWay='" + GateWay + '\'' +
                ", Mask='" + Mask + '\'' +
                ", Dns='" + Dns + '\'' +
                ", HostName='" + HostName + '\'' +
                ", TelnetEn=" + TelnetEn +
                ", TelnetPort=" + TelnetPort +
                ", TelnetEcho=" + TelnetEcho +
                ", WebEn=" + WebEn +
                ", WebPort=" + WebPort +
                ", Ipv6En=" + Ipv6En +
                ", Ipv6Addr='" + Ipv6Addr + '\'' +
                ", Ipv6DhcpEn=" + Ipv6DhcpEn +
                ", ntpEn=" + ntpEn +
                ", ntpServer='" + ntpServer + '\'' +
                ", ntpPort=" + ntpPort +
                ", ntpGMT=" + ntpGMT +
                ", Longitude=" + Longitude +
                ", Latitude=" + Latitude +
                ", Lang='" + Lang + '\'' +
                '}';
    }
}
