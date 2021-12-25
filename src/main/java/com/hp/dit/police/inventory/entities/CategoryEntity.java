package com.hp.dit.police.inventory.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mst_category")
public class CategoryEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "mst_category_category_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_category_category_id_seq", sequenceName = "public.mst_category_category_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "category_id")
    private Integer categoryID;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "is_active")
    private Boolean active;

    @Column(name = "is_deleted")
    private Boolean deleted;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                ", active=" + active +
                ", deleted=" + deleted +
                ", createdOn=" + createdOn +
                '}';
    }
}