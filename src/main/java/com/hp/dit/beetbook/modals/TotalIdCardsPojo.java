package com.hp.dit.beetbook.modals;

import java.io.Serializable;
import java.math.BigInteger;

public class TotalIdCardsPojo implements Serializable {


    private String barrierName;
    private BigInteger noOfIdCardGenerated;

    public String getBarrierName() {
        return barrierName;
    }

    public void setBarrierName(String barrierName) {
        this.barrierName = barrierName;
    }

    public BigInteger getNoOfIdCardGenerated() {
        return noOfIdCardGenerated;
    }

    public void setNoOfIdCardGenerated(BigInteger noOfIdCardGenerated) {
        this.noOfIdCardGenerated = noOfIdCardGenerated;
    }

    @Override
    public String toString() {
        return "TotalIdCardsPojo{" +
                "barrierName='" + barrierName + '\'' +
                ", noOfIdCardGenerated=" + noOfIdCardGenerated +
                '}';
    }
}
