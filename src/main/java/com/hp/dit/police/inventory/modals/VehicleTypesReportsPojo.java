package com.hp.dit.police.inventory.modals;

import java.io.Serializable;
import java.math.BigInteger;

public class VehicleTypesReportsPojo implements Serializable {

    private String vehicleType;
    private BigInteger count;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "VehicleTypesReportsPojo{" +
                "vehicleType='" + vehicleType + '\'' +
                ", count=" + count +
                '}';
    }
}
