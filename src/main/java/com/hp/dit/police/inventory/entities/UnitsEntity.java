package com.hp.dit.police.inventory.entities;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="mst_units")
public class UnitsEntity {

    @Id
    @GeneratedValue(generator = "mst_units_unit_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_units_unit_id_seq", sequenceName = "public.mst_units_unit_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "unit_id")
    private Integer unitId;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "unit_desc")
    private String unitDesc;

    @Column(name = "is_active")
    private Boolean active;

    @Column(name = "is_deleted")
    private Boolean deleted;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitDesc() {
        return unitDesc;
    }

    public void setUnitDesc(String unitDesc) {
        this.unitDesc = unitDesc;
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

    @Override
    public String toString() {
        return "UnitsEntity{" +
                "unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                ", unitDesc='" + unitDesc + '\'' +
                ", active=" + active +
                ", deleted=" + deleted +
                ", createdDate=" + createdDate +
                '}';
    }
}
