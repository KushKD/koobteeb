package com.hp.dit.police.inventory.form.store;

import java.io.Serializable;

public class StoreForm implements Serializable {

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoryForm{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}
