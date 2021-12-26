package com.hp.dit.police.inventory.form.category;

import java.io.Serializable;

public class UpdateCategory  implements Serializable {

    private String categoryId;
    private String  categoryName;
    private String active;
    private String deleted;

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
        return "UpdateCategory{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", active='" + active + '\'' +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
