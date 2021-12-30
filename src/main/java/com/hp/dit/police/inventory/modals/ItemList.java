package com.hp.dit.police.inventory.modals;

import java.io.Serializable;

public class ItemList implements Serializable {

    private String itemsName;
    private Integer quantity;
    private String itemsLetterdoc;
    private Boolean active;
    private String storeName;

    public ItemList(String itemsName, Integer quantity, String itemsLetterdoc, Boolean active, String storeName) {
        this.itemsName = itemsName;
        this.quantity = quantity;
        this.itemsLetterdoc = itemsLetterdoc;
        this.active = active;
        this.storeName = storeName;
    }

    public String getItemName() {
        return itemsName;
    }

    public void setItemName(String itemName) {
        this.itemsName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
                "itemName='" + itemsName + '\'' +
                ", quantity=" + quantity +
                ", itemsLetterdoc='" + itemsLetterdoc + '\'' +
                ", active=" + active +
                ", storeName='" + storeName + '\'' +
                '}';
    }
}