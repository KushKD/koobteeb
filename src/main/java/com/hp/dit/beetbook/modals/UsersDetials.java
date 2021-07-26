package com.hp.dit.beetbook.modals;

import java.io.Serializable;
import java.math.BigInteger;

public class UsersDetials implements Serializable {

    private Integer user_id;
    private String first_name;
    private String last_name;
    private String username;
    private Integer role_id;
    private String role_name;
    private BigInteger mobile_number;
    private Integer state_id;
    private String state_name;
    private Integer district_id;
    private String district_name;
    private Integer beat_id;
    private String beat_name;
    private String rank;
    private Integer sodpo_id;
    private Integer ps_id;

    public Integer getSodpo_id() {
        return sodpo_id;
    }

    public void setSodpo_id(Integer sodpo_id) {
        this.sodpo_id = sodpo_id;
    }

    public Integer getPs_id() {
        return ps_id;
    }

    public void setPs_id(Integer ps_id) {
        this.ps_id = ps_id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public BigInteger getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(BigInteger mobile_number) {
        this.mobile_number = mobile_number;
    }

    public Integer getState_id() {
        return state_id;
    }

    public void setState_id(Integer state_id) {
        this.state_id = state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public Integer getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Integer district_id) {
        this.district_id = district_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public Integer getBeat_id() {
        return beat_id;
    }

    public void setBeat_id(Integer beat_id) {
        this.beat_id = beat_id;
    }

    public String getBeat_name() {
        return beat_name;
    }

    public void setBeat_name(String beat_name) {
        this.beat_name = beat_name;
    }

    @Override
    public String toString() {
        return "UsersDetials{" +
                "user_id=" + user_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", mobile_number=" + mobile_number +
                ", state_id=" + state_id +
                ", state_name='" + state_name + '\'' +
                ", district_id=" + district_id +
                ", district_name='" + district_name + '\'' +
                ", beat_id=" + beat_id +
                ", beat_name='" + beat_name + '\'' +
                ", rank='" + rank + '\'' +
                ", sodpo_id=" + sodpo_id +
                ", ps_id=" + ps_id +
                '}';
    }
}
