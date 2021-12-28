package com.hp.dit.police.inventory.form.items;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class ItemForm implements Serializable {

    private String itemsId;
    private String itemsName;
    private String policelineId;
    private String psId;
    private String storeId;
    private String categoryId;
    private String unitId;
    private String quantity;
    private String itemsDesc;
    private String itemsLetterrefno;
    private MultipartFile itemsLetterrefdoc;
    private String fileName;
    private String active;
    private String deleted;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

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

    public String getPolicelineId() {
        return policelineId;
    }

    public void setPolicelineId(String policelineId) {
        this.policelineId = policelineId;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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
                ", policelineId='" + policelineId + '\'' +
                ", psId='" + psId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", unitId='" + unitId + '\'' +
                ", quantity='" + quantity + '\'' +
                ", itemsDesc='" + itemsDesc + '\'' +
                ", itemsLetterrefno='" + itemsLetterrefno + '\'' +
                ", itemsLetterrefdoc=" + itemsLetterrefdoc +
                ", fileName='" + fileName + '\'' +
                ", active='" + active + '\'' +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
