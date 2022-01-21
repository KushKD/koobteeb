package com.hp.dit.beetbook.entities;

import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mst_apppin")
public class PinMaster implements Serializable {

    @Id
    @GeneratedValue(generator = "mst_apppin_pin_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_apppin_pin_id_seq", sequenceName = "public.mst_apppin_pin_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "pin_id")
    private Integer pinId;

    @Column(name = "pin")
    private Integer pin;

    @OneToOne
    @JoinColumn(name = "state_id")
    private StatesMaster stateID;

    @OneToOne
    @JoinColumn(name = "district_id")
    private DistrictMaster districtId;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "is_deleted")
    private Boolean deleted;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "lastmodifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    public Integer getPinId() {
        return pinId;
    }

    public void setPinId(Integer pinId) {
        this.pinId = pinId;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public StatesMaster getStateID() {
        return stateID;
    }

    public void setStateID(StatesMaster stateID) {
        this.stateID = stateID;
    }

    public DistrictMaster getDistrictId() {
        return districtId;
    }

    public void setDistrictId(DistrictMaster districtId) {
        this.districtId = districtId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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
        return "PinMaster{" +
                "pinId=" + pinId +
                ", pin=" + pin +
                ", stateID=" + stateID +
                ", districtId=" + districtId +
                ", active=" + active +
                ", deleted=" + deleted +
                ", createdDate=" + createdDate +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
