package com.hp.dit.beetbook.form.district;

import java.io.Serializable;

public class DistrictForm implements Serializable {

    private String districtId;
    private String stateId;
    private String districtName;
    private String districtIsActive;
    private String districtIsDeleted;

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

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictIsActive() {
        return districtIsActive;
    }

    public void setDistrictIsActive(String districtIsActive) {
        this.districtIsActive = districtIsActive;
    }

    public String getDistrictIsDeleted() {
        return districtIsDeleted;
    }

    public void setDistrictIsDeleted(String districtIsDeleted) {
        this.districtIsDeleted = districtIsDeleted;
    }

    @Override
    public String toString() {
        return "DistrictForm{" +
                "districtId='" + districtId + '\'' +
                ", stateId='" + stateId + '\'' +
                ", districtName='" + districtName + '\'' +
                ", districtIsActive='" + districtIsActive + '\'' +
                ", districtIsDeleted='" + districtIsDeleted + '\'' +
                '}';
    }
}
