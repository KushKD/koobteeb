package com.hp.dit.beetbook.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="mst_submoduleoption")
public class OptionsMaster {

    @Id
    @GeneratedValue(generator = "mst_submoduleoption_option_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_submoduleoption_option_id_seq", sequenceName = "public.mst_submoduleoption_option_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "option_id")
    private Integer optionId;

    @Column(name = "option_name")
    private String optionName;


    @Column(name = "submodule_id")
    private Integer subModuleId;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "lastmodifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Integer getSubModuleId() {
        return subModuleId;
    }

    public void setSubModuleId(Integer subModuleId) {
        this.subModuleId = subModuleId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
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
        return "OptionsMaster{" +
                "optionId=" + optionId +
                ", optionName='" + optionName + '\'' +
                ", subModuleId=" + subModuleId +
                ", active=" + active +
                ", createdDate=" + createdDate +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
