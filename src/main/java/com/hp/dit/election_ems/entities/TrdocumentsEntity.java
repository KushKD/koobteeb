package com.hp.dit.election_ems.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name="mst_transfer_request_documents")
public class TrdocumentsEntity {

    @Id
    @GeneratedValue(generator = "mst_transfer_request_documents_doc_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_transfer_request_documents_doc_id_seq", sequenceName = "public.mst_transfer_request_documents_doc_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name="doc_id")
    private Integer documentId;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name="vehicle_image_owner")
    private byte[] vehicleImageOwner;

    @Column(name="document_name")
    private String documentName;

    @Column(name = "transfer_request_id")
    private Integer transferRequestID;


    @Column (name="uploaded_by")
    private Integer enteredBy;

    @Column(name="active")
    private boolean active;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "lastmodifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;


    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public byte[] getVehicleImageOwner() {
        return vehicleImageOwner;
    }

    public void setVehicleImageOwner(byte[] vehicleImageOwner) {
        this.vehicleImageOwner = vehicleImageOwner;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Integer getTransferRequestID() {
        return transferRequestID;
    }

    public void setTransferRequestID(Integer transferRequestID) {
        this.transferRequestID = transferRequestID;
    }

    public Integer getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(Integer enteredBy) {
        this.enteredBy = enteredBy;
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
        return "TrdocumentsEntity{" +
                "documentId=" + documentId +
                ", vehicleImageOwner=" + Arrays.toString(vehicleImageOwner) +
                ", documentName='" + documentName + '\'' +
                ", transferRequestID=" + transferRequestID +
                ", enteredBy=" + enteredBy +
                ", active=" + active +
                ", createdDate=" + createdDate +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
