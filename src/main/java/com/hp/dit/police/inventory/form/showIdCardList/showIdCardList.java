package com.hp.dit.police.inventory.form.showIdCardList;

import java.io.Serializable;

public class showIdCardList implements Serializable {

    private String district_id;
    private String barrier_id;
    private String date;
    private String state_id;

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getBarrier_id() {
        return barrier_id;
    }

    public void setBarrier_id(String barrier_id) {
        this.barrier_id = barrier_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "showIdCardList{" +
                "district_id='" + district_id + '\'' +
                ", barrier_id='" + barrier_id + '\'' +
                ", date='" + date + '\'' +
                ", state_id='" + state_id + '\'' +
                '}';
    }
}
