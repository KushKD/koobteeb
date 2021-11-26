package com.hp.dit.police.inventory.form.cashier;

import java.io.Serializable;

public class CashCollectionFormSave implements Serializable {

    private String enterotp;
    private String rev_user;
    private String amount;
    private String barrierid;
    private Long mobileNumber;

    public String getBarrierid() {
        return barrierid;
    }

    public void setBarrierid(String barrierid) {
        this.barrierid = barrierid;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEnterotp() {
        return enterotp;
    }

    public void setEnterotp(String enterotp) {
        this.enterotp = enterotp;
    }

    public String getRev_user() {
        return rev_user;
    }

    public void setRev_user(String rev_user) {
        this.rev_user = rev_user;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CashCollectionFormSave{" +
                "enterotp='" + enterotp + '\'' +
                ", rev_user='" + rev_user + '\'' +
                ", amount='" + amount + '\'' +
                ", barrierid='" + barrierid + '\'' +
                ", mobileNumber=" + mobileNumber +
                '}';
    }
}
