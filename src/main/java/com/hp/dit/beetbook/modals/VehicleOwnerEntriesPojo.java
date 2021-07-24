package com.hp.dit.beetbook.modals;

import java.io.Serializable;

public class VehicleOwnerEntriesPojo implements Serializable {

    private Long vehicleOwnerId;
    private String vehicleOwnerName;
    private Long vehicleOwnerMobile;
    private String vehicleOwnerVehicleNumber;
    private boolean genrerated_;

    public Long getVehicleOwnerId() {
        return vehicleOwnerId;
    }

    public void setVehicleOwnerId(Long vehicleOwnerId) {
        this.vehicleOwnerId = vehicleOwnerId;
    }

    public String getVehicleOwnerName() {
        return vehicleOwnerName;
    }

    public void setVehicleOwnerName(String vehicleOwnerName) {
        this.vehicleOwnerName = vehicleOwnerName;
    }

    public Long getVehicleOwnerMobile() {
        return vehicleOwnerMobile;
    }

    public void setVehicleOwnerMobile(Long vehicleOwnerMobile) {
        this.vehicleOwnerMobile = vehicleOwnerMobile;
    }

    public String getVehicleOwnerVehicleNumber() {
        return vehicleOwnerVehicleNumber;
    }

    public void setVehicleOwnerVehicleNumber(String vehicleOwnerVehicleNumber) {
        this.vehicleOwnerVehicleNumber = vehicleOwnerVehicleNumber;
    }

    public boolean isGenrerated_() {
        return genrerated_;
    }

    public void setGenrerated_(boolean genrerated_) {
        this.genrerated_ = genrerated_;
    }

    public VehicleOwnerEntriesPojo(Long vehicleOwnerId, String vehicleOwnerName, Long vehicleOwnerMobile, String vehicleOwnerVehicleNumber, boolean genrerated_) {
        this.vehicleOwnerId = vehicleOwnerId;
        this.vehicleOwnerName = vehicleOwnerName;
        this.vehicleOwnerMobile = vehicleOwnerMobile;
        this.vehicleOwnerVehicleNumber = vehicleOwnerVehicleNumber;
        this.genrerated_ = genrerated_;
    }

    @Override
    public String toString() {
        return "VehicleOwnerEntriesPojo{" +
                "vehicleOwnerId=" + vehicleOwnerId +
                ", vehicleOwnerName='" + vehicleOwnerName + '\'' +
                ", vehicleOwnerMobile=" + vehicleOwnerMobile +
                ", vehicleOwnerVehicleNumber='" + vehicleOwnerVehicleNumber + '\'' +
                ", genrerated_=" + genrerated_ +
                '}';
    }
}
