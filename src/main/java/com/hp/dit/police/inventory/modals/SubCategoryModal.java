package com.hp.dit.police.inventory.modals;


import java.io.Serializable;

public class SubCategoryModal implements Serializable {

    private Integer vendorCategoryId;
    private String vendorCategoryName;

    public SubCategoryModal(Integer vendorCategoryId, String vendorCategoryName) {
        this.vendorCategoryId = vendorCategoryId;
        this.vendorCategoryName = vendorCategoryName;
    }

    public Integer getVendorCategoryId() {
        return vendorCategoryId;
    }

    public void setVendorCategoryId(Integer vendorCategoryId) {
        this.vendorCategoryId = vendorCategoryId;
    }

    public String getVendorCategoryName() {
        return vendorCategoryName;
    }

    public void setVendorCategoryName(String vendorCategoryName) {
        this.vendorCategoryName = vendorCategoryName;
    }

    @Override
    public String toString() {
        return "SubCategoryModal{" +
                "vendorCategoryId=" + vendorCategoryId +
                ", vendorCategoryName='" + vendorCategoryName + '\'' +
                '}';
    }
}
