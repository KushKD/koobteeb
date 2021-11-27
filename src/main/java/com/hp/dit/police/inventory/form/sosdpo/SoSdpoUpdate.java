package com.hp.dit.police.inventory.form.sosdpo;

import java.io.Serializable;

public class SoSdpoUpdate implements Serializable {

    private String policelineId;
    private String policelineName;
    private String soSdpoActive;
    private String soSdpoDeleted;


    public String getPolicelineId() {
        return policelineId;
    }

    public void setPolicelineId(String policelineId) {
        this.policelineId = policelineId;
    }

    public String getPolicelineName() {
        return policelineName;
    }

    public void setPolicelineName(String policelineName) {
        this.policelineName = policelineName;
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
                "policelineId='" + policelineId + '\'' +
                ", policelineName='" + policelineName + '\'' +
                ", soSdpoActive='" + soSdpoActive + '\'' +
                ", soSdpoDeleted='" + soSdpoDeleted + '\'' +
                '}';
    }
}
