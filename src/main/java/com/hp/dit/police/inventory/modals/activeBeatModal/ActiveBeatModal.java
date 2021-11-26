package com.hp.dit.police.inventory.modals.activeBeatModal;

import java.io.Serializable;
import java.math.BigInteger;

public class ActiveBeatModal implements Serializable {

    private Integer beatId;
    private String username;
    private Integer userId;
    private Integer roleId;
    private BigInteger mobile;
    private String beatName;
    private String policeStationName;
    private String date;

    public Integer getBeatId() {
        return beatId;
    }

    public void setBeatId(Integer beatId) {
        this.beatId = beatId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public BigInteger getMobile() {
        return mobile;
    }

    public void setMobile(BigInteger mobile) {
        this.mobile = mobile;
    }

    public String getBeatName() {
        return beatName;
    }

    public void setBeatName(String beatName) {
        this.beatName = beatName;
    }

    public String getPoliceStationName() {
        return policeStationName;
    }

    public void setPoliceStationName(String policeStationName) {
        this.policeStationName = policeStationName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ActiveBeatModal{" +
                "beatId=" + beatId +
                ", username='" + username + '\'' +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", mobile=" + mobile +
                ", beatName='" + beatName + '\'' +
                ", policeStationName='" + policeStationName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
