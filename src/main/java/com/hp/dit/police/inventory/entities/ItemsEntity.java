package com.hp.dit.police.inventory.entities;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="mst_item")
public class ItemsEntity {

    @Id
    @GeneratedValue(generator = "mst_item_items_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_item_items_id_seq", sequenceName = "public.mst_item_items_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "items_id")
    private Integer itemsId;

    @Column(name = "items_name")
    private String itemsName;


    @OneToOne
    @JoinColumn(name = "store_id",updatable = false)
    private StoreEntity store;

    @OneToOne
    @JoinColumn(name = "category_id",updatable = false)
    private CategoryItemsEntity categoryItemsEntity;

    @OneToOne
    @JoinColumn(name = "unit_id",updatable = false)
    private UnitsEntity units;


    @Column(name = "items_desc")
    private String itemsDesc;

    @Column(name = "items_letterrefdoc")
    private String itemsLetterdoc;

    @Column(name = "items_letterrefno")
    private String itemsLetterno;

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

    public Integer getItemsId() {
        return itemsId;
    }

    public void setItemsId(Integer itemsId) {
        this.itemsId = itemsId;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public CategoryItemsEntity getCategoryItemsEntity() {
        return categoryItemsEntity;
    }

    public void setCategoryItemsEntity(CategoryItemsEntity categoryItemsEntity) {
        this.categoryItemsEntity = categoryItemsEntity;
    }

    public UnitsEntity getUnits() {
        return units;
    }

    public void setUnits(UnitsEntity units) {
        this.units = units;
    }

    public String getItemsDesc() {
        return itemsDesc;
    }

    public void setItemsDesc(String itemsDesc) {
        this.itemsDesc = itemsDesc;
    }

    public String getItemsLetterdoc() {
        return itemsLetterdoc;
    }

    public void setItemsLetterdoc(String itemsLetterdoc) {
        this.itemsLetterdoc = itemsLetterdoc;
    }

    public String getItemsLetterno() {
        return itemsLetterno;
    }

    public void setItemsLetterno(String itemsLetterno) {
        this.itemsLetterno = itemsLetterno;
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
                "itemsId=" + itemsId +
                ", itemsName='" + itemsName + '\'' +
                ", store=" + store +
                ", categoryItemsEntity=" + categoryItemsEntity +
                ", units=" + units +
                ", itemsDesc='" + itemsDesc + '\'' +
                ", itemsLetterdoc='" + itemsLetterdoc + '\'' +
                ", itemsLetterno='" + itemsLetterno + '\'' +
                ", active=" + active +
                ", deleted=" + deleted +
                ", createdDate=" + createdDate +
                ", lastmodifiedDate=" + lastmodifiedDate +
                '}';
    }
}
