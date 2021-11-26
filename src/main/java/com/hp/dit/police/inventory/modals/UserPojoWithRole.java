package com.hp.dit.police.inventory.modals;

import java.io.Serializable;

public class UserPojoWithRole implements Serializable {

    private UsePoJo userPojo;
    private Integer roleId;
    private String roleName;

    public UsePoJo getUserPojo() {
        return userPojo;
    }

    public void setUserPojo(UsePoJo userPojo) {
        this.userPojo = userPojo;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserPojoWithRole{" +
                "userPojo=" + userPojo +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
