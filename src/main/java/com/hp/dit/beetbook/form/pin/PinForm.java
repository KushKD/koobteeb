package com.hp.dit.beetbook.form.pin;

import java.io.Serializable;

public class PinForm implements Serializable {

    private String pinId;
    private String pin;
    private String stateIsActive;
    private String stateIsDeleted;

    public String getPinId() {
        return pinId;
    }

    public void setPinId(String pinId) {
        this.pinId = pinId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getStateIsActive() {
        return stateIsActive;
    }

    public void setStateIsActive(String stateIsActive) {
        this.stateIsActive = stateIsActive;
    }

    public String getStateIsDeleted() {
        return stateIsDeleted;
    }

    public void setStateIsDeleted(String stateIsDeleted) {
        this.stateIsDeleted = stateIsDeleted;
    }

    @Override
    public String toString() {
        return "PinForm{" +
                "pinId='" + pinId + '\'' +
                ", pin='" + pin + '\'' +
                ", stateIsActive='" + stateIsActive + '\'' +
                ", stateIsDeleted='" + stateIsDeleted + '\'' +
                '}';
    }
}
