package com.hp.dit.election_ems.modals;

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
