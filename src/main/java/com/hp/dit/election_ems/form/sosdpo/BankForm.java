package com.hp.dit.election_ems.form.sosdpo;

import java.io.Serializable;

public class BankForm implements Serializable {

    private String soSdpo;

    public String getSoSdpo() {
        return soSdpo;
    }

    public void setSoSdpo(String soSdpo) {
        this.soSdpo = soSdpo;
    }

    @Override
    public String toString() {
        return "StateForm{" +
                "soSdpo='" + soSdpo + '\'' +
                '}';
    }
}