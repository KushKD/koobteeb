package com.hp.dit.police.inventory.modals.moduleModel;

import java.io.Serializable;

public class ModulesModal implements Serializable {

    private Integer moduleId;
    private String moduleName;
    private String moduleIcon;
    private Boolean isActive;

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleIcon() {
        return moduleIcon;
    }

    public void setModuleIcon(String moduleIcon) {
        this.moduleIcon = moduleIcon;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "ModulesModal{" +
                "moduleId=" + moduleId +
                ", moduleName='" + moduleName + '\'' +
                ", moduleIcon='" + moduleIcon + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
