package com.hp.dit.police.inventory.form.viewdata;

import java.io.Serializable;

public class ViewData implements Serializable {

    private String stateId;
    private String districtId;
    private String sosdpoId;
    private String psId;
    private String beatId;
    private String submoduleId;
    private String fromDate;
    private String toDate;

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

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public String getBeatId() {
        return beatId;
    }

    public void setBeatId(String beatId) {
        this.beatId = beatId;
    }

    public String getSubmoduleId() {
        return submoduleId;
    }

    public void setSubmoduleId(String submoduleId) {
        this.submoduleId = submoduleId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "ViewData{" +
                "stateId='" + stateId + '\'' +
                ", districtId='" + districtId + '\'' +
                ", sosdpoId='" + sosdpoId + '\'' +
                ", psId='" + psId + '\'' +
                ", beatId='" + beatId + '\'' +
                ", subModuleId='" + submoduleId + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                '}';
    }
}
