package com.hp.dit.beetbook.form.sosdpo;

import java.io.Serializable;

public class SoSdpoForm implements Serializable {

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
