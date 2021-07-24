package com.hp.dit.beetbook.form;

import java.io.Serializable;

public class ActionForm implements Serializable {

    private String modepayment;
    private String amount;
    private String vehicle_number;
    private String vehicleowner_mobile;
    private String vehicleOwnerId;
    private String barrierID;
    private String idCardNumber;
    private String vehicleOwnerName;
    private String barrierName;
    private String vehicleType;
    private String proceedWithoutPayment;

    public String getProceedWithoutPayment() {
        return proceedWithoutPayment;
    }

    public void setProceedWithoutPayment(String proceedWithoutPayment) {
        this.proceedWithoutPayment = proceedWithoutPayment;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleOwnerName() {
        return vehicleOwnerName;
    }

    public void setVehicleOwnerName(String vehicleOwnerName) {
        this.vehicleOwnerName = vehicleOwnerName;
    }

    public String getBarrierName() {
        return barrierName;
    }

    public void setBarrierName(String barrierName) {
        this.barrierName = barrierName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getBarrierID() {
        return barrierID;
    }

    public void setBarrierID(String barrierID) {
        this.barrierID = barrierID;
    }

    public String getModepayment() {
        return modepayment;
    }

    public void setModepayment(String modepayment) {
        this.modepayment = modepayment;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getVehicleowner_mobile() {
        return vehicleowner_mobile;
    }

    public void setVehicleowner_mobile(String vehicleowner_mobile) {
        this.vehicleowner_mobile = vehicleowner_mobile;
    }

    public String getVehicleOwnerId() {
        return vehicleOwnerId;
    }

    public void setVehicleOwnerId(String vehicleOwnerId) {
        this.vehicleOwnerId = vehicleOwnerId;
    }

    @Override
    public String toString() {
        return "ActionForm{" +
                "modepayment='" + modepayment + '\'' +
                ", amount='" + amount + '\'' +
                ", vehicle_number='" + vehicle_number + '\'' +
                ", vehicleowner_mobile='" + vehicleowner_mobile + '\'' +
                ", vehicleOwnerId='" + vehicleOwnerId + '\'' +
                ", barrierID='" + barrierID + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", vehicleOwnerName='" + vehicleOwnerName + '\'' +
                ", barrierName='" + barrierName + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", proceedWithoutPayment='" + proceedWithoutPayment + '\'' +
                '}';
    }
}
