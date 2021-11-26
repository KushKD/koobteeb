package com.hp.dit.police.inventory.form.state;

import java.io.Serializable;

public class StateForm implements Serializable {

    private String stateName;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return "StateForm{" +
                "stateName='" + stateName + '\'' +
                '}';
    }
}
