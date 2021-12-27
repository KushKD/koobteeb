package com.hp.dit.police.inventory.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mst_store")
public class StoreEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "mst_category_category_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_category_category_id_seq", sequenceName = "public.mst_category_category_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "store_id")
    private Integer storeID;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "is_active")
    private Boolean active;

    @Column(name = "is_deleted")
    private Boolean deleted;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    public Integer getStoreID() {
        return storeID;
    }

    public void setStoreID(Integer storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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
        return "StoreEntity{" +
                "storeID=" + storeID +
                ", storeName='" + storeName + '\'' +
                ", active=" + active +
                ", deleted=" + deleted +
                ", createdOn=" + createdOn +
                '}';
    }
}