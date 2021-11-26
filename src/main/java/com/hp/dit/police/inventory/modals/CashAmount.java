package com.hp.dit.police.inventory.modals;

import java.io.Serializable;
import java.math.BigInteger;

public class CashAmount implements Serializable {

    private BigInteger amount;

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CashAmount{" +
                "amount=" + amount +
                '}';
    }
}
