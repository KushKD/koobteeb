package com.hp.dit.police.inventory.modals.submoduleModal;

import java.io.Serializable;

public class SubModuleRoleList implements Serializable {

    private Integer submoduleId;
    private String submoduleName;
    private Integer moduleId;
    private String subModuleIcon;

    public SubModuleRoleList(Integer submoduleId, String submoduleName, Integer moduleId, String subModuleIcon) {
        this.submoduleId = submoduleId;
        this.submoduleName = submoduleName;
        this.moduleId = moduleId;
        this.subModuleIcon = subModuleIcon;
    }

    public Integer getSubmoduleId() {
        return submoduleId;
    }

    public void setSubmoduleId(Integer submoduleId) {
        this.submoduleId = submoduleId;
    }

    public String getSubmoduleName() {
        return submoduleName;
    }

    public void setSubmoduleName(String submoduleName) {
        this.submoduleName = submoduleName;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getSubModuleIcon() {
        return subModuleIcon;
    }

    public void setSubModuleIcon(String subModuleIcon) {
        this.subModuleIcon = subModuleIcon;
    }

    @Override
    public String toString() {
        return "SubModuleRoleList{" +
                "submoduleId=" + submoduleId +
                ", submoduleName='" + submoduleName + '\'' +
                ", moduleId=" + moduleId +
                ", subModuleIcon='" + subModuleIcon + '\'' +
                '}';
    }
}
