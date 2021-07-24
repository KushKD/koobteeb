package com.hp.dit.beetbook.modals;

import java.io.Serializable;
import java.math.BigInteger;

public class TotalAmountCollectedPojo implements Serializable {

    private BigInteger totalAmount;

    public BigInteger getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigInteger totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "TotalAmountCollectedPojo{" +
                "totalAmount=" + totalAmount +
                '}';
    }
}
