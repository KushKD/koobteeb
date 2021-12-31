package com.hp.dit.police.inventory.modals;

import java.io.Serializable;

public class ItemList implements Serializable {

    private Integer itemId;
    private String itemsName;
    private String itemsLetterdoc;
    private Boolean active;
    private String storeName;

    public ItemList(Integer itemId, String itemsName, String itemsLetterdoc, Boolean active, String storeName) {
        this.itemId = itemId;
        this.itemsName = itemsName;
        this.itemsLetterdoc = itemsLetterdoc;
        this.active = active;
        this.storeName = storeName;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public String getItemsLetterdoc() {
        return itemsLetterdoc;
    }

    public void setItemsLetterdoc(String itemsLetterdoc) {
        this.itemsLetterdoc = itemsLetterdoc;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public String toString() {
        return "ItemList{" +
                "itemId='" + itemId + '\'' +
                ", itemsName='" + itemsName + '\'' +
                ", itemsLetterdoc='" + itemsLetterdoc + '\'' +
                ", active=" + active +
                ", storeName='" + storeName + '\'' +
                '}';
    }
}