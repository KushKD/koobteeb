package com.hp.dit.police.inventory.modals;

import java.io.Serializable;

public class ItemListAjax implements Serializable {

    private Integer itemId;
    private String itemsName;
    private String units;

    public ItemListAjax(Integer itemId, String itemsName, String units) {
        this.itemId = itemId;
        this.itemsName = itemsName;
        this.units = units;
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

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "ItemListAjax{" +
                "itemId=" + itemId +
                ", itemsName='" + itemsName + '\'' +
                ", units='" + units + '\'' +
                '}';
    }
}