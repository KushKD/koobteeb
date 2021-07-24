package com.hp.dit.beetbook.modals;

import java.io.Serializable;
import java.math.BigInteger;

public class CashDeposited implements Serializable {

    private BigInteger depositedAmount;

    public BigInteger getDepositedAmount() {
        return depositedAmount;
    }

    public void setDepositedAmount(BigInteger depositedAmount) {
        this.depositedAmount = depositedAmount;
    }

    @Override
    public String toString() {
        return "CashDeposited{" +
                "depositedAmount=" + depositedAmount +
                '}';
    }
}
