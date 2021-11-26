package com.hp.dit.police.inventory.modals.information;

import java.io.Serializable;
import java.util.Date;

public class InformationMarkers implements Serializable {

    private Integer id;
    private Double latitude;
    private Double longitude;
    private String name;
    private String photo;
    private String subModuleName;
    private Integer SubModuleId;
    private Integer moduleId;
    private String subMoculeIcon;
    private Date createdDate;


    public InformationMarkers(Integer id, Double latitude, Double longitude, String name, String photo, String subModuleName, Integer subModuleId, Integer moduleId, String subMoculeIcon, Date createdDate) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.photo = photo;
        this.subModuleName = subModuleName;
        SubModuleId = subModuleId;
        this.moduleId = moduleId;
        this.subMoculeIcon = subMoculeIcon;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSubModuleName() {
        return subModuleName;
    }

    public void setSubModuleName(String subModuleName) {
        this.subModuleName = subModuleName;
    }

    public Integer getSubModuleId() {
        return SubModuleId;
    }

    public void setSubModuleId(Integer subModuleId) {
        SubModuleId = subModuleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getSubMoculeIcon() {
        return subMoculeIcon;
    }

    public void setSubMoculeIcon(String subMoculeIcon) {
        this.subMoculeIcon = subMoculeIcon;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "InformationMarkers{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", subModuleName='" + subModuleName + '\'' +
                ", SubModuleId=" + SubModuleId +
                ", moduleId=" + moduleId +
                ", subMoculeIcon='" + subMoculeIcon + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
