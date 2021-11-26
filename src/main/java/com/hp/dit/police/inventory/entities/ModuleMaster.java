package com.hp.dit.police.inventory.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="mst_module")
public class ModuleMaster {

    @Id
    @GeneratedValue(generator = "mst_module_module_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_module_module_id_seq", sequenceName = "public.mst_module_module_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "module_id")
    private Integer moduleId;

    @Column(name = "module_name")
    private String moduleName;


    @Column(name = "module_icon")
    private String iconName;

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

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
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
        return "ModuleMaster{" +
                "moduleId=" + moduleId +
                ", moduleName='" + moduleName + '\'' +
                ", iconName=" + iconName +
                ", active=" + active +
                ", deleted=" + deleted +
                ", createdDate=" + createdDate +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
