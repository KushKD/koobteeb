package com.hp.dit.beetbook.form.cashier;

import java.io.Serializable;

public class CashCollectionForm implements Serializable {

    private String district_id;
    private String barrier_id;
    private String date;
    private String state_id;
    private String user_id;
    private String from_Date;

    public String getFrom_Date() {
        return from_Date;
    }

    public void setFrom_Date(String from_Date) {
        this.from_Date = from_Date;
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

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "CashCollectionForm{" +
                "district_id='" + district_id + '\'' +
                ", barrier_id='" + barrier_id + '\'' +
                ", date='" + date + '\'' +
                ", state_id='" + state_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", from_Date='" + from_Date + '\'' +
                '}';
    }
}
