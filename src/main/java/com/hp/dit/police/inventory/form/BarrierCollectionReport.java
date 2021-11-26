package com.hp.dit.police.inventory.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

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
