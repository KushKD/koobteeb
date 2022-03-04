package com.hp.dit.beetbook.modals;

import javax.persistence.Column;
import java.io.Serializable;

public class InformationAddedUser  implements Serializable {

    private String username;
    private String firstName;
    private String lastName;
    private String rank;
    private Long mobileNumber;

    public InformationAddedUser(String username, String firstName, String lastName, String rank, Long mobileNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rank = rank;
        this.mobileNumber = mobileNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "InformationAddedUser{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rank='" + rank + '\'' +
                ", mobileNumber=" + mobileNumber +
                '}';
    }
}
