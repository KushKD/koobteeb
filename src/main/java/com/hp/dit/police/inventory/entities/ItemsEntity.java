package com.hp.dit.police.inventory.entities;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.Date;

@Table(name = "mst_item")
@Entity
public class ItemsEntity {
    @Id
    @GeneratedValue(generator = "mst_item_items_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_item_items_id_seq", sequenceName = "public.mst_item_items_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "items_id", nullable = false)
    private Integer itemId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", updatable = false )
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private StoreEntity categoryID;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id", updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private SubCategoryEntity subCategoryId;

    @Column(name = "items_name", nullable = false)
    private String itemsName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", updatable = false)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private UnitsEntity unitId;

    @Column(name = "items_desc", nullable = false)
    private String itemsDesc;

    @Column(name = "items_receivedfrom", nullable = false)
    private String itemsReceivedfrom;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "lastmodifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodifiedDate;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public StoreEntity getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(StoreEntity categoryID) {
        this.categoryID = categoryID;
    }

    public SubCategoryEntity getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(SubCategoryEntity subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public UnitsEntity getUnitId() {
        return unitId;
    }

    public void setUnitId(UnitsEntity unitId) {
        this.unitId = unitId;
    }

    public String getItemsDesc() {
        return itemsDesc;
    }

    public void setItemsDesc(String itemsDesc) {
        this.itemsDesc = itemsDesc;
    }

    public String getItemsReceivedfrom() {
        return itemsReceivedfrom;
    }

    public void setItemsReceivedfrom(String itemsReceivedfrom) {
        this.itemsReceivedfrom = itemsReceivedfrom;
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

    public Date getLastmodifiedDate() {
        return lastmodifiedDate;
    }

    public void setLastmodifiedDate(Date lastmodifiedDate) {
        this.lastmodifiedDate = lastmodifiedDate;
    }

    @Override
    public String toString() {
        return "ItemsEntity{" +
                "itemId=" + itemId +
                ", categoryID=" + categoryID +
                ", subCategoryId=" + subCategoryId +
                ", itemsName='" + itemsName + '\'' +
                ", unitId=" + unitId +
                ", itemsDesc='" + itemsDesc + '\'' +
                ", itemsReceivedfrom='" + itemsReceivedfrom + '\'' +
                ", active=" + active +
                ", deleted=" + deleted +
                ", createdDate=" + createdDate +
                ", lastmodifiedDate=" + lastmodifiedDate +
                '}';
    }
}