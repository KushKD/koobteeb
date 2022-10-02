package com.hp.dit.election_ems.modals.moduleRole;

import java.io.Serializable;

public class ModuleRoleList implements Serializable {

    private Integer moduleRoleId;
    private String moduleName;
    private String roleName;
    private Integer moduleId;
    private Integer roleId;

    public ModuleRoleList(Integer moduleRoleId, String moduleName, String roleName, Integer moduleId, Integer roleId) {
        this.moduleRoleId = moduleRoleId;
        this.moduleName = moduleName;
        this.roleName = roleName;
        this.moduleId = moduleId;
        this.roleId = roleId;
    }

    public Integer getModuleRoleId() {
        return moduleRoleId;
    }

    public void setModuleRoleId(Integer moduleRoleId) {
        this.moduleRoleId = moduleRoleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "ModuleRoleList{" +
                "moduleRoleId=" + moduleRoleId +
                ", moduleName='" + moduleName + '\'' +
                ", roleName='" + roleName + '\'' +
                ", moduleId=" + moduleId +
                ", roleId=" + roleId +
                '}';
    }
}
