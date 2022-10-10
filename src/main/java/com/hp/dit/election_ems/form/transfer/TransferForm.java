package com.hp.dit.election_ems.form.transfer;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Arrays;

public class TransferForm implements Serializable {

    private String vehicleNo;
    private String fromDate;
    private String thruDate;

    private String sourceAddress;
    private String destAddress;
    private MultipartFile[] attachFiles;

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

    public MultipartFile[] getAttachFiles() {
        return attachFiles;
    }

    public void setAttachFiles(MultipartFile[] attachFiles) {
        this.attachFiles = attachFiles;
    }

    @Override
    public String toString() {
        return "TransferForm{" +
                "vehicleNo='" + vehicleNo + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", thruDate='" + thruDate + '\'' +
                ", sourceAddress='" + sourceAddress + '\'' +
                ", destAddress='" + destAddress + '\'' +
                ", attachFiles=" + Arrays.toString(attachFiles) +
                ", amount='" + amount + '\'' +
                '}';
    }
}
