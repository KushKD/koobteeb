package com.hp.dit.beetbook.form.submodule;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class SubModuleForm implements Serializable {

    private String submoduleId;
    private String submoduleName;
    private MultipartFile submoduleIcon;
    private String moduleId;
    private String isActive;
    private String isDeleted;

    public String getSubmoduleId() {
        return submoduleId;
    }

    public void setSubmoduleId(String submoduleId) {
        this.submoduleId = submoduleId;
    }

    public String getSubmoduleName() {
        return submoduleName;
    }

    public void setSubmoduleName(String submoduleName) {
        this.submoduleName = submoduleName;
    }

    public MultipartFile getSubmoduleIcon() {
        return submoduleIcon;
    }

    public void setSubmoduleIcon(MultipartFile submoduleIcon) {
        this.submoduleIcon = submoduleIcon;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "SubModuleForm{" +
                "submoduleId='" + submoduleId + '\'' +
                ", submoduleName='" + submoduleName + '\'' +
                ", submoduleIcon=" + submoduleIcon +
                ", moduleId='" + moduleId + '\'' +
                ", isActive='" + isActive + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                '}';
    }
}
