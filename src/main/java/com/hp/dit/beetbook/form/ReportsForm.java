package com.hp.dit.beetbook.form;

import java.io.Serializable;

public class ReportsForm implements Serializable {

    private String stateId;
    private String districtId;
    private String barrierId;
    private String barrierType;
    private String vehicleType;
    private String ownerType;
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

    public String getBarrierId() {
        return barrierId;
    }

    public void setBarrierId(String barrierId) {
        this.barrierId = barrierId;
    }

    public String getBarrierType() {
        return barrierType;
    }

    public void setBarrierType(String barrierType) {
        this.barrierType = barrierType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
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
        return "ReportsForm{" +
                "stateId='" + stateId + '\'' +
                ", districtId='" + districtId + '\'' +
                ", barrierId='" + barrierId + '\'' +
                ", barrierType='" + barrierType + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", ownerType='" + ownerType + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                '}';
    }
}
