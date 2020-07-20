package com.hzlh.kzq.data;

public class SockInfo {

    private String Name;
    private String Protocol;
    private String Server;
    private Integer ServerPort;
    private Integer LocalPort;
    private Integer BufSize;
    private Integer KeepAlive;
    private Integer Timeout;
    private String Security;
    private String SecuKey;
    private String ConnectMode;
    private String StopSerial;
    private String Rout;
    private Integer maxAccept;
    private Integer VcomEn;

    public SockInfo(String name, String protocol, String server, Integer serverPort, Integer localPort, Integer bufSize, Integer keepAlive, Integer timeout, String security, String secuKey, String connectMode, String stopSerial, String rout, Integer maxAccept, Integer vcomEn) {
        Name = name;
        Protocol = protocol;
        Server = server;
        ServerPort = serverPort;
        LocalPort = localPort;
        BufSize = bufSize;
        KeepAlive = keepAlive;
        Timeout = timeout;
        Security = security;
        SecuKey = secuKey;
        ConnectMode = connectMode;
        StopSerial = stopSerial;
        Rout = rout;
        this.maxAccept = maxAccept;
        VcomEn = vcomEn;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getProtocol() {
        return Protocol;
    }

    public void setProtocol(String protocol) {
        Protocol = protocol;
    }

    public String getServer() {
        return Server;
    }

    public void setServer(String server) {
        Server = server;
    }

    public Integer getServerPort() {
        return ServerPort;
    }

    public void setServerPort(Integer serverPort) {
        ServerPort = serverPort;
    }

    public Integer getLocalPort() {
        return LocalPort;
    }

    public void setLocalPort(Integer localPort) {
        LocalPort = localPort;
    }

    public Integer getBufSize() {
        return BufSize;
    }

    public void setBufSize(Integer bufSize) {
        BufSize = bufSize;
    }

    public Integer getKeepAlive() {
        return KeepAlive;
    }

    public void setKeepAlive(Integer keepAlive) {
        KeepAlive = keepAlive;
    }

    public Integer getTimeout() {
        return Timeout;
    }

    public void setTimeout(Integer timeout) {
        Timeout = timeout;
    }

    public String getSecurity() {
        return Security;
    }

    public void setSecurity(String security) {
        Security = security;
    }

    public String getSecuKey() {
        return SecuKey;
    }

    public void setSecuKey(String secuKey) {
        SecuKey = secuKey;
    }

    public String getConnectMode() {
        return ConnectMode;
    }

    public void setConnectMode(String connectMode) {
        ConnectMode = connectMode;
    }

    public String getStopSerial() {
        return StopSerial;
    }

    public void setStopSerial(String stopSerial) {
        StopSerial = stopSerial;
    }

    public String getRout() {
        return Rout;
    }

    public void setRout(String rout) {
        Rout = rout;
    }

    public Integer getMaxAccept() {
        return maxAccept;
    }

    public void setMaxAccept(Integer maxAccept) {
        this.maxAccept = maxAccept;
    }

    public Integer getVcomEn() {
        return VcomEn;
    }

    public void setVcomEn(Integer vcomEn) {
        VcomEn = vcomEn;
    }

    @Override
    public String toString() {
        return "SockInfo{" +
                "Name='" + Name + '\'' +
                ", Protocol='" + Protocol + '\'' +
                ", Server='" + Server + '\'' +
                ", ServerPort=" + ServerPort +
                ", LocalPort=" + LocalPort +
                ", BufSize=" + BufSize +
                ", KeepAlive=" + KeepAlive +
                ", Timeout=" + Timeout +
                ", Security='" + Security + '\'' +
                ", SecuKey='" + SecuKey + '\'' +
                ", ConnectMode='" + ConnectMode + '\'' +
                ", StopSerial='" + StopSerial + '\'' +
                ", Rout='" + Rout + '\'' +
                ", maxAccept=" + maxAccept +
                ", VcomEn=" + VcomEn +
                '}';
    }
}
