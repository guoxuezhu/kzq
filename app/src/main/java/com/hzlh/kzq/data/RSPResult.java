package com.hzlh.kzq.data;


public class RSPResult<T> {

    public int CID;
    public int RC;
    public int Remain;

    T PL;

    public T getPL() {
        return PL;
    }

    public RSPResult(int CID, int RC, int remain, T PL) {
        this.CID = CID;
        this.RC = RC;
        Remain = remain;
        this.PL = PL;
    }

    @Override
    public String toString() {
        return "RSPResult{" +
                "CID=" + CID +
                ", RC=" + RC +
                ", Remain=" + Remain +
                ", PL=" + PL +
                '}';
    }
}
