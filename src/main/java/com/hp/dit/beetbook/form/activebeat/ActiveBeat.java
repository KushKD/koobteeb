package com.hp.dit.beetbook.form.activebeat;

import java.io.Serializable;

public class ActiveBeat implements Serializable {

    private String toDate;

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "ActiveBeat{" +
                "toDate='" + toDate + '\'' +
                '}';
    }
}
