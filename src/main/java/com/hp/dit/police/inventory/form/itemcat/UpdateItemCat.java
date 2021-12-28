package com.hp.dit.police.inventory.form.itemcat;

import java.io.Serializable;

public class UpdateItemCat implements Serializable {

    private String categoryId;
    private String categoryName;
    private String categoryDesc;
    private String categoryIsActive;
    private String categoryIsDeleted;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getCategoryIsActive() {
        return categoryIsActive;
    }

    public void setCategoryIsActive(String categoryIsActive) {
        this.categoryIsActive = categoryIsActive;
    }

    public String getCategoryIsDeleted() {
        return categoryIsDeleted;
    }

    public void setCategoryIsDeleted(String categoryIsDeleted) {
        this.categoryIsDeleted = categoryIsDeleted;
    }

    @Override
    public String toString() {
        return "UpdateItemCat{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDesc='" + categoryDesc + '\'' +
                ", categoryIsActive='" + categoryIsActive + '\'' +
                ", categoryIsDeleted='" + categoryIsDeleted + '\'' +
                '}';
    }
}
