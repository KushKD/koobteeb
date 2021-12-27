package com.hp.dit.police.inventory.form.store;

import java.io.Serializable;

public class UpdateStore implements Serializable {

    private String storeId;
    private String  storeName;
    private String active;
    private String deleted;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "UpdateStore{" +
                "storeId='" + storeId + '\'' +
                ", storeName='" + storeName + '\'' +
                ", active='" + active + '\'' +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
