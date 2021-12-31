package com.hp.dit.police.inventory.modals;

import java.io.Serializable;
import java.math.BigInteger;

public class ItemGroupWiseTotal implements Serializable {

    private Integer policelineId;
    private String policeLineName;
    private String storeName;
    private String categoryName;
    private String itemName;
    private Integer itemId;
    private Long sum;
  //  , BigInteger sum


    public ItemGroupWiseTotal(Integer policelineId, String policeLineName, String storeName, String categoryName, String itemName, Integer itemId, Long sum) {
        this.policelineId = policelineId;
        this.policeLineName = policeLineName;
        this.storeName = storeName;
        this.categoryName = categoryName;
        this.itemName = itemName;
        this.itemId = itemId;
        this.sum = sum;
    }

    public Integer getPolicelineId() {
        return policelineId;
    }

    public void setPolicelineId(Integer policelineId) {
        this.policelineId = policelineId;
    }

    public String getPoliceLineName() {
        return policeLineName;
    }

    public void setPoliceLineName(String policeLineName) {
        this.policeLineName = policeLineName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "ItemGroupWiseTotal{" +
                "policelineId=" + policelineId +
                ", policeLineName='" + policeLineName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemId=" + itemId +
                ", sum=" + sum +
                '}';
    }
}
