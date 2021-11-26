package com.hp.dit.police.inventory.form;

import java.io.Serializable;

public class TwoStep implements Serializable {

   private String enterOtp;

    public String getEnterOtp() {
        return enterOtp;
    }

    public void setEnterOtp(String enterOtp) {
        this.enterOtp = enterOtp;
    }

    @Override
    public String toString() {
        return "TwoStepAuthenticationForm{" +
                "enterOtp='" + enterOtp + '\'' +
                '}';
    }
}