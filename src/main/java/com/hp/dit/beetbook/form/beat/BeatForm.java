package com.hp.dit.beetbook.form.beat;

import java.io.Serializable;

public class BeatForm implements Serializable {

    private String beatId;
    private String beatName;
    private String psId;
    private String stateId;
    private String districtId;
    private String sosdpoId;
    private String isActive;
    private String isDeleted;
    //private String latitude;
    //private String longitude;

//    public String getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(String latitude) {
//        this.latitude = latitude;
//    }
//
//    public String getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(String longitude) {
//        this.longitude = longitude;
//    }

    public String getBeatId() {
        return beatId;
    }

    public void setBeatId(String beatId) {
        this.beatId = beatId;
    }

    public String getBeatName() {
        return beatName;
    }

    public void setBeatName(String beatName) {
        this.beatName = beatName;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
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
        return "BeatForm{" +
                "beatId='" + beatId + '\'' +
                ", beatName='" + beatName + '\'' +
                ", psId='" + psId + '\'' +
                ", stateId='" + stateId + '\'' +
                ", districtId='" + districtId + '\'' +
                ", sosdpoId='" + sosdpoId + '\'' +
                ", isActive='" + isActive + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
               // ", latitude='" + latitude + '\'' +
               // ", longitude='" + longitude + '\'' +
                '}';
    }
}
