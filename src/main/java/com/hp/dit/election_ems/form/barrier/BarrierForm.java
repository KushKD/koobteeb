package com.hp.dit.election_ems.form.barrier;

import java.io.Serializable;

public class BarrierForm implements Serializable {

    private String barrierId;
    private String barrierName;
    private String stateId;
    private String districtId;
    private String barrierType;
    private String barrierIsActive;
    private String barrierIsDeleted;

    public String getBarrierType() {
        return barrierType;
    }

    public void setBarrierType(String barrierType) {
        this.barrierType = barrierType;
    }

    public String getBarrierId() {
        return barrierId;
    }

    public void setBarrierId(String barrierId) {
        this.barrierId = barrierId;
    }

    public String getBarrierName() {
        return barrierName;
    }

    public void setBarrierName(String barrierName) {
        this.barrierName = barrierName;
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

    public String getBarrierIsActive() {
        return barrierIsActive;
    }

    public void setBarrierIsActive(String barrierIsActive) {
        this.barrierIsActive = barrierIsActive;
    }

    public String getBarrierIsDeleted() {
        return barrierIsDeleted;
    }

    public void setBarrierIsDeleted(String barrierIsDeleted) {
        this.barrierIsDeleted = barrierIsDeleted;
    }

    @Override
    public String toString() {
        return "BarrierForm{" +
                "barrierId='" + barrierId + '\'' +
                ", barrierName='" + barrierName + '\'' +
                ", stateId='" + stateId + '\'' +
                ", districtId='" + districtId + '\'' +
                ", barrierType='" + barrierType + '\'' +
                ", barrierIsActive='" + barrierIsActive + '\'' +
                ", barrierIsDeleted='" + barrierIsDeleted + '\'' +
                '}';
    }
}
