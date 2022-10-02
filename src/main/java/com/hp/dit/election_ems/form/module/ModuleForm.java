package com.hp.dit.election_ems.form.module;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class ModuleForm implements Serializable {

    private String moduleId;
    private String moduleName;
    private MultipartFile moduleIcon;
    private String isActive;
    private String isDeleted;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public MultipartFile getModuleIcon() {
        return moduleIcon;
    }

    public void setModuleIcon(MultipartFile moduleIcon) {
        this.moduleIcon = moduleIcon;
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
        return "ModuleForm{" +
                "moduleId='" + moduleId + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", moduleIcon=" + moduleIcon +
                ", isActive='" + isActive + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                '}';
    }
}
