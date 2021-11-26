package com.hp.dit.police.inventory.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class BarrierBoxesCollectionReport implements Serializable {

    private BigDecimal count;
    private String barrierName;

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public String getBarrierName() {
        return barrierName;
    }

    public void setBarrierName(String barrierName) {
        this.barrierName = barrierName;
    }

    @Override
    public String toString() {
        return "BarrierBoxesCollectionReport{" +
                "count=" + count +
                ", barrierName='" + barrierName + '\'' +
                '}';
    }
}
