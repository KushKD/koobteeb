package com.hp.dit.police.inventory.form.stockregister;

import java.io.Serializable;

public class StockinForm implements Serializable {

    private String policelineId;
    private String itemId;
    private String storeId;
    private String categoryId;
    private String quantity;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getPolicelineId() {
        return policelineId;
    }

    public void setPolicelineId(String policelineId) {
        this.policelineId = policelineId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StockinForm{" +
                "policelineId='" + policelineId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
