package com.hp.dit.police.inventory.form.items;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class UpdateItem implements Serializable {

    private String itemsId;
    private String itemsName;
    private String storeId;
    private String categoryId;
    private String unitId;
    private String itemsDesc;
    private String itemsLetterrefno;
    private MultipartFile itemsLetterrefdoc;
    private String fileName;
    private String active;
    private String deleted;

    public String getItemsId() {
        return itemsId;
    }

    public void setItemsId(String itemsId) {
        this.itemsId = itemsId;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getItemsDesc() {
        return itemsDesc;
    }

    public void setItemsDesc(String itemsDesc) {
        this.itemsDesc = itemsDesc;
    }

    public String getItemsLetterrefno() {
        return itemsLetterrefno;
    }

    public void setItemsLetterrefno(String itemsLetterrefno) {
        this.itemsLetterrefno = itemsLetterrefno;
    }

    public MultipartFile getItemsLetterrefdoc() {
        return itemsLetterrefdoc;
    }

    public void setItemsLetterrefdoc(MultipartFile itemsLetterrefdoc) {
        this.itemsLetterrefdoc = itemsLetterrefdoc;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
        return "ItemForm{" +
                "itemsId='" + itemsId + '\'' +
                ", itemsName='" + itemsName + '\'' +
                ", storeId='" + storeId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", unitId='" + unitId + '\'' +
                ", itemsDesc='" + itemsDesc + '\'' +
                ", itemsLetterrefno='" + itemsLetterrefno + '\'' +
                ", itemsLetterrefdoc=" + itemsLetterrefdoc +
                ", fileName='" + fileName + '\'' +
                ", active='" + active + '\'' +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
