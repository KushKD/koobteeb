package com.hp.dit.police.inventory.modals;

import java.io.Serializable;

public class RevenueUserDetails implements Serializable {

    private String firstName;
    private String lastName;
    private Long MobileNumber;

    public RevenueUserDetails(String firstName, String lastName, Long mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        MobileNumber = mobileNumber;
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
        return MobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        MobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "RevenueUserDetails{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", MobileNumber=" + MobileNumber +
                '}';
    }
}
