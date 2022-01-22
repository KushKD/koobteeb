package com.hp.dit.beetbook.form.dailyactivity;

import java.io.Serializable;

public class DailyactivityForm implements Serializable {

    private String stateId;
    private String districtId;
    private String sosdpoId;
    private String psId;
    private String beatId;
    private String userId;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DailyactivityForm{" +
                "stateId='" + stateId + '\'' +
                ", districtId='" + districtId + '\'' +
                ", sosdpoId='" + sosdpoId + '\'' +
                ", psId='" + psId + '\'' +
                ", beatId='" + beatId + '\'' +
                ", userId='" + userId + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
