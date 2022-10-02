package com.hp.dit.election_ems.form.modulerole;

import java.io.Serializable;

public class ModuleRoleForm implements Serializable {

    private String id;
    private String moduleId;
    private String roleId;
    private String isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "ModuleRoleForm{" +
                "id='" + id + '\'' +
                ", moduleId='" + moduleId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", isActive='" + isActive + '\'' +
                '}';
    }
}
