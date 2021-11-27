package com.hp.dit.police.inventory.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "mst_policelines")
public class PoliceLines implements Serializable {

    @Id
    @GeneratedValue(generator = "mst_policelines_policeline_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_policelines_policeline_id_seq", sequenceName = "public.mst_policelines_policeline_id_seq", initialValue = 1, allocationSize = 1)

    @Column(name="policeline_id")
    private Integer policelineId;

    @Column(name="policeline_name")
    private String policelineName;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name="is_deleted")
    private boolean isDeleted;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "lastmodifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;


    public Integer getPolicelineId() {
        return policelineId;
    }

    public void setPolicelineId(Integer policelineId) {
        this.policelineId = policelineId;
    }

    public String getPolicelineName() {
        return policelineName;
    }

    public void setPolicelineName(String policelineName) {
        this.policelineName = policelineName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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
        return "PoliceLines{" +
                "policelineId=" + policelineId +
                ", policelineName='" + policelineName + '\'' +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                ", createdDate=" + createdDate +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
