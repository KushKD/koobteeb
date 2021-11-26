package com.hp.dit.police.inventory.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "mst_sosdpo")
public class S0SdpoMaster implements Serializable {

    @Id
    @GeneratedValue(generator = "mst_sosdpo_sosdpo_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_sosdpo_sosdpo_id_seq", sequenceName = "public.mst_sosdpo_sosdpo_id_seq", initialValue = 1, allocationSize = 1)

    @Column(name="sosdpo_id")
    private Integer sosdpoId;

    @Column(name="sosdpo_name")
    private String sosdpoName;

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


    public Integer getSosdpoId() {
        return sosdpoId;
    }

    public void setSosdpoId(Integer sosdpoId) {
        this.sosdpoId = sosdpoId;
    }

    public String getSosdpoName() {
        return sosdpoName;
    }

    public void setSosdpoName(String sosdpoName) {
        this.sosdpoName = sosdpoName;
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
        return "S0SdpoMaster{" +
                "sosdpoId=" + sosdpoId +
                ", sosdpoName='" + sosdpoName + '\'' +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                ", createdDate=" + createdDate +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
