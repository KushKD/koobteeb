package com.hp.dit.beetbook.form.state;

import java.io.Serializable;

public class UpdateState implements Serializable {

    private String stateId;
    private String stateName;
    private String stateIsActive;
    private String stateIsDeleted;

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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
        return "UpdateState{" +
                "stateId='" + stateId + '\'' +
                ", stateName='" + stateName + '\'' +
                ", stateIsActive='" + stateIsActive + '\'' +
                ", stateIsDeleted='" + stateIsDeleted + '\'' +
                '}';
    }
}
