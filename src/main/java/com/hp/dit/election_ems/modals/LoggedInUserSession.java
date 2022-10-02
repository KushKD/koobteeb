package com.hp.dit.election_ems.modals;

import java.io.Serializable;

public class LoggedInUserSession implements Serializable {

    private Integer stateId;
    private Integer districtId;
   // private Integer BarrierId;
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private Long mobileNumber;

    public LoggedInUserSession(Integer stateId, Integer districtId, Long userId, String userName, String firstName, String lastName, Long mobileNumber) {
        this.stateId = stateId;
        this.districtId = districtId;
       // BarrierId = barrierId;
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "LoggedInUserSession{" +
                "stateId=" + stateId +
                ", districtId=" + districtId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber=" + mobileNumber +
                '}';
    }
}