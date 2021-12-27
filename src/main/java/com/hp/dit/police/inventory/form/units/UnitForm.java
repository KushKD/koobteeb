package com.hp.dit.police.inventory.form.units;

import java.io.Serializable;

public class UnitForm implements Serializable {

    private String unitName;
    private String unitDescription;

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public String toString() {
        return "UnitForm{" +
                "unitName='" + unitName + '\'' +
                ", unitDescription='" + unitDescription + '\'' +
                '}';
    }
}
