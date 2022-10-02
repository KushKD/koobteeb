package com.hp.dit.election_ems.modals;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class TotalCollectionBarrierWise implements Serializable {

    private BigDecimal totalCollection;
    private BigInteger totalDeposited;
    private BigDecimal totalBalance;

    public BigDecimal getTotalCollection() {
        return totalCollection;
    }

    public void setTotalCollection(BigDecimal totalCollection) {
        this.totalCollection = totalCollection;
    }

    public BigInteger getTotalDeposited() {
        return totalDeposited;
    }

    public void setTotalDeposited(BigInteger totalDeposited) {
        this.totalDeposited = totalDeposited;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    @Override
    public String toString() {
        return "TotalCollectionBarrierWise{" +
                "totalCollection=" + totalCollection +
                ", totalDeposited=" + totalDeposited +
                ", totalBalance=" + totalBalance +
                '}';
    }
}
