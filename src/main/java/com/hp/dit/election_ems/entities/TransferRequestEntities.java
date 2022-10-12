package com.hp.dit.election_ems.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="mst_transfer_request_entries")
public class TransferRequestEntities {

    @Id
    @GeneratedValue(generator = "mst_transfer_request_entries_transfer_request_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_transfer_request_entries_transfer_request_id_seq", sequenceName = "public.mst_transfer_request_entries_transfer_request_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name="transfer_request_id")
    private Integer transferRequestID;

    @Column(name="vehicle_number")
    private String vehicleNumber;

    @Column(name="source_address")
    private String sourceAddress;

    @Column(name="dest_address")
    private String destAddress;

    @Column(name="from_date")
    private String fromDate;

    @Column(name="thrue_date")
    private String thrueDate;

    @Column(name="amount")
    private Long amount;

    @Column(name="other_information")
    private String otherInformation;

    @OneToOne
    @JoinColumn (name="data_entered_by")
    private UserEntity enteredBy;

    @OneToOne
    @JoinColumn (name="bank_id")
    private BankMaster bankId;


    @Column(name="active")
    private boolean active;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "lastmodifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    public Integer getTransferRequestID() {
        return transferRequestID;
    }

    public void setTransferRequestID(Integer transferRequestID) {
        this.transferRequestID = transferRequestID;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
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

    public String getThrueDate() {
        return thrueDate;
    }

    public void setThrueDate(String thrueDate) {
        this.thrueDate = thrueDate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public UserEntity getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(UserEntity enteredBy) {
        this.enteredBy = enteredBy;
    }

    public BankMaster getBankId() {
        return bankId;
    }

    public void setBankId(BankMaster bankId) {
        this.bankId = bankId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "TransferRequestEntities{" +
                "transferRequestID=" + transferRequestID +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", sourceAddress='" + sourceAddress + '\'' +
                ", destAddress='" + destAddress + '\'' +
                ", fromDate='" + fromDate + '\'' +
                ", thrueDate='" + thrueDate + '\'' +
                ", amount=" + amount +
                ", otherInformation='" + otherInformation + '\'' +
                ", enteredBy=" + enteredBy +
                ", bankId=" + bankId +
                ", active=" + active +
                ", createdDate=" + createdDate +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
