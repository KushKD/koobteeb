package com.hp.dit.election_ems.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserDatatableEntity {

    @Id
    @GeneratedValue(generator = "users_user_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "users_user_id_seq", sequenceName = "public.users_user_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "rank")
    private String rank;


    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "district_id")
    private DistrictMaster districtId;



    @OneToOne
    @JoinColumn(name = "state_id")
    private StatesMaster stateId;

    @Column(name = "mobile_number")
    private Long mobileNumber;

    @Column(name = "active")
    private boolean active;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DistrictMaster getDistrictId() {
        return districtId;
    }

    public void setDistrictId(DistrictMaster districtId) {
        this.districtId = districtId;
    }




    public StatesMaster getStateId() {
        return stateId;
    }

    public void setStateId(StatesMaster stateId) {
        this.stateId = stateId;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserDatatableEntity{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rank='" + rank + '\'' +
                ", password='" + password + '\'' +
                ", districtId=" + districtId +
                ", stateId=" + stateId +
                ", mobileNumber=" + mobileNumber +
                ", active=" + active +
                '}';
    }
}