package com.hzlh.kzq.data;

public class SendREQRemain {

    private int JCMD;
    private int Remain;
    private int CID;

    public SendREQRemain(int JCMD, int remain, int CID) {
        this.JCMD = JCMD;
        Remain = remain;
        this.CID = CID;
    }

    @Override
    public String toString() {
        return "SendREQRemain{" +
                "JCMD=" + JCMD +
                ", Remain=" + Remain +
                ", CID=" + CID +
                '}';
    }
}
