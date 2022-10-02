package com.hp.dit.election_ems.form;

import java.io.Serializable;
import java.math.BigDecimal;

public class BarrierCollectionReport implements Serializable {

    private String barrierName;
    private BigDecimal amount;

    public String getBarrierName() {
        return barrierName;
    }

    public void setBarrierName(String barrierName) {
        this.barrierName = barrierName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
