package com.hp.dit.police.inventory.form.units;

import java.io.Serializable;

public class UpdateUnit implements Serializable {

    private String unitId;
    private String unitName;
    private String unitDescription;
    private String unitIsActive;
    private String unitIsDeleted;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    public String getUnitIsActive() {
        return unitIsActive;
    }

    public void setUnitIsActive(String unitIsActive) {
        this.unitIsActive = unitIsActive;
    }

    public String getUnitIsDeleted() {
        return unitIsDeleted;
    }

    public void setUnitIsDeleted(String unitIsDeleted) {
        this.unitIsDeleted = unitIsDeleted;
    }

    @Override
    public String toString() {
        return "UpdateUnit{" +
                "unitId='" + unitId + '\'' +
                ", unitName='" + unitName + '\'' +
                ", unitDescription='" + unitDescription + '\'' +
                ", unitIsActive='" + unitIsActive + '\'' +
                ", unitIsDeleted='" + unitIsDeleted + '\'' +
                '}';
    }
}
