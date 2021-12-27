package com.hp.dit.police.inventory.form.state;

import java.io.Serializable;

public class StateForm implements Serializable {

    private String stateName;
    private String unitDescription;

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

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
                ", unitDescription='" + unitDescription + '\'' +
                '}';
    }
}
