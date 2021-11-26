package com.hp.dit.police.inventory.form.sosdpo;

import java.io.Serializable;

public class SoSdpoUpdate implements Serializable {

    private String soSdpoId;
    private String soSdpoName;
    private String soSdpoActive;
    private String soSdpoDeleted;

    public String getSoSdpoId() {
        return soSdpoId;
    }

    public void setSoSdpoId(String soSdpoId) {
        this.soSdpoId = soSdpoId;
    }

    public String getSoSdpoName() {
        return soSdpoName;
    }

    public void setSoSdpoName(String soSdpoName) {
        this.soSdpoName = soSdpoName;
    }

    public String getSoSdpoActive() {
        return soSdpoActive;
    }

    public void setSoSdpoActive(String soSdpoActive) {
        this.soSdpoActive = soSdpoActive;
    }

    public String getSoSdpoDeleted() {
        return soSdpoDeleted;
    }

    public void setSoSdpoDeleted(String soSdpoDeleted) {
        this.soSdpoDeleted = soSdpoDeleted;
    }

    @Override
    public String toString() {
        return "SoSdpoUpdate{" +
                "soSdpoId='" + soSdpoId + '\'' +
                ", soSdpoName='" + soSdpoName + '\'' +
                ", soSdpoActive='" + soSdpoActive + '\'' +
                ", soSdpoDeleted='" + soSdpoDeleted + '\'' +
                '}';
    }
}
