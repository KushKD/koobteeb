package com.hp.dit.beetbook.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mst_policestation")
public class PoliceStationMaster implements Serializable {

    @Id
    @GeneratedValue(generator = "mst_policestation_ps_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_policestation_ps_id_seq", sequenceName = "public.mst_policestation_ps_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "ps_id")
    private Integer psId;

    @Column(name = "ps_name")
    private String psName;

    @Column(name = "state_id")
    private Integer stateID;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "sosdpo_id")
    private Integer sosdpoId;

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

    public Integer getPsId() {
        return psId;
    }

    public void setPsId(Integer psId) {
        this.psId = psId;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    public Integer getStateID() {
        return stateID;
    }

    public void setStateID(Integer stateID) {
        this.stateID = stateID;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getSosdpoId() {
        return sosdpoId;
    }

    public void setSosdpoId(Integer sosdpoId) {
        this.sosdpoId = sosdpoId;
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
        return "PoliceStationMaster{" +
                "psId=" + psId +
                ", psName='" + psName + '\'' +
                ", stateID=" + stateID +
                ", districtId=" + districtId +
                ", sosdpoId=" + sosdpoId +
                ", active=" + active +
                ", deleted=" + deleted +
                ", createdDate=" + createdDate +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
