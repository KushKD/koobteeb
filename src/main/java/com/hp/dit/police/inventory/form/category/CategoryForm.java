package com.hp.dit.police.inventory.form.category;

import java.io.Serializable;

public class CategoryForm implements Serializable {

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
