package com.hp.dit.election_ems.modals.usersviabeat;

import com.hp.dit.election_ems.entities.TrdocumentsEntity;

import java.io.Serializable;
import java.util.List;

public class ShowCashRequestData implements Serializable {

    private String vehicleNo;
    private String sourceAddress;
    private String destAddress;
    private String fromDate;
    private String thruDate;
    private String enteredBy;
    private String enteredByMobile;
    private String role;
    private String roleDesc;
    private List<TrdocumentsEntity> documents;

    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getThruDate() {
        return thruDate;
    }

    public void setThruDate(String thruDate) {
        this.thruDate = thruDate;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public String getEnteredByMobile() {
        return enteredByMobile;
    }

    public void setEnteredByMobile(String enteredByMobile) {
        this.enteredByMobile = enteredByMobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<TrdocumentsEntity> getDocuments() {
        return documents;
    }

    public void setDocuments(List<TrdocumentsEntity> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "ShowCashRequestData{" +
                "vehicleNo='" + vehicleNo + '\'' +
                ", sourceAddress='" + sourceAddress + '\'' +
                ", destAddress='" + destAddress + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", thruDate='" + thruDate + '\'' +
                ", enteredBy='" + enteredBy + '\'' +
                ", enteredByMobile='" + enteredByMobile + '\'' +
                ", role='" + role + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", documents=" + documents +
                ", amount=" + amount +
                '}';
    }
}
