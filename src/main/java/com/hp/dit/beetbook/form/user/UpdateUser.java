package com.hp.dit.beetbook.form.user;

import java.io.Serializable;

public class UpdateUser implements Serializable {

    private String userId;
    private String username;
    private String password;
    private String passwordConfirm;
    private String mobileNumber;
    private String roleId;
    private String firstName;
    private String lastName;
    private String stateId;
    private String districtId;
    private String beatId;
    private String rank;
    private String sosdpoId;
    private String psId;
    private String userIsActive;
    private Integer oldroleid;

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public String getSosdpoId() {
        return sosdpoId;
    }

    public void setSosdpoId(String sosdpoId) {
        this.sosdpoId = sosdpoId;
    }

    public Integer getOldroleid() {
        return oldroleid;
    }

    public void setOldroleid(Integer oldroleid) {
        this.oldroleid = oldroleid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIsActive() {
        return userIsActive;
    }

    public void setUserIsActive(String userIsActive) {
        this.userIsActive = userIsActive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getBeatId() {
        return beatId;
    }

    public void setBeatId(String beatId) {
        this.beatId = beatId;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "UpdateUser{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", roleId='" + roleId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", stateId='" + stateId + '\'' +
                ", districtId='" + districtId + '\'' +
                ", beatId='" + beatId + '\'' +
                ", rank='" + rank + '\'' +
                ", sosdpoId='" + sosdpoId + '\'' +
                ", psId='" + psId + '\'' +
                ", userIsActive='" + userIsActive + '\'' +
                ", oldroleid=" + oldroleid +
                '}';
    }
}
