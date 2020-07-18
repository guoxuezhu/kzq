package com.hzlh.kzq.data;

import java.util.ArrayList;

public class SendREQ {

    private int JCMD;
    private ArrayList<String> PL;
    private int CID;

    public SendREQ(int JCMD, ArrayList<String> PL, int CID) {
        this.JCMD = JCMD;
        this.PL = PL;
        this.CID = CID;
    }

    @Override
    public String toString() {
        return "SendREQ{" +
                "JCMD=" + JCMD +
                ", PL=" + PL +
                ", CID=" + CID +
                '}';
    }
}
