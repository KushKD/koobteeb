package com.hp.dit.election_ems.form.policestation;

import java.io.Serializable;

public class PSForm implements Serializable {

    private String psId;
    private String psName;
    private String stateId;
    private String districtId;
    private String sosdpoId;
    private String isActive;
    private String isDeleted;

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getSosdpoId() {
        return sosdpoId;
    }

    public void setSosdpoId(String sosdpoId) {
        this.sosdpoId = sosdpoId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "PSForm{" +
                "psId='" + psId + '\'' +
                ", psName='" + psName + '\'' +
                ", stateId='" + stateId + '\'' +
                ", districtId='" + districtId + '\'' +
                ", sosdpoId='" + sosdpoId + '\'' +
                ", isActive='" + isActive + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                '}';
    }
}
