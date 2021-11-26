package com.hp.dit.police.inventory.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="mst_submodule")
public class SubModuleMaster {

    @Id
    @GeneratedValue(generator = "mst_submodule_submodule_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_submodule_submodule_id_seq", sequenceName = "public.mst_submodule_submodule_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "submodule_id")
    private Integer submoduleId;

    @Column(name = "submodule_name")
    private String submoduleName;

    @Column(name = "submodule_icon")
    private String subiconName;

    @Column(name = "module_id")
    private Integer moduleId;

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

    public Integer getSubmoduleId() {
        return submoduleId;
    }

    public void setSubmoduleId(Integer submoduleId) {
        this.submoduleId = submoduleId;
    }

    public String getSubmoduleName() {
        return submoduleName;
    }

    public void setSubmoduleName(String submoduleName) {
        this.submoduleName = submoduleName;
    }

    public String getSubiconName() {
        return subiconName;
    }

    public void setSubiconName(String subiconName) {
        this.subiconName = subiconName;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
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
        return "SubModuleMaster{" +
                "submoduleId=" + submoduleId +
                ", submoduleName='" + submoduleName + '\'' +
                ", subiconName='" + subiconName + '\'' +
                ", moduleId=" + moduleId +
                ", active=" + active +
                ", deleted=" + deleted +
                ", createdDate=" + createdDate +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
