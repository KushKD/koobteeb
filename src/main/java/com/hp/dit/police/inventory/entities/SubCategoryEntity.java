package com.hp.dit.police.inventory.entities;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mst_itemcat")
public class SubCategoryEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "mst_itemcat_itemcat_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_itemcat_itemcat_id_seq", sequenceName = "public.mst_itemcat_itemcat_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "subcategory_id")
    private Integer subCategoryId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", updatable = false )
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private StoreEntity categoryID;

    @Column(name = "subcategory_name")
    private String subCategoryName;

    @Column(name = "is_active")
    private Boolean active;

    @Column(name = "is_deleted")
    private Boolean deleted;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public StoreEntity getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(StoreEntity categoryID) {
        this.categoryID = categoryID;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
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
        return "SubCategoryEntity{" +
                "subCategoryId=" + subCategoryId +
                ", categoryID=" + categoryID +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", active=" + active +
                ", deleted=" + deleted +
                ", createdDate=" + createdDate +
                '}';
    }
}