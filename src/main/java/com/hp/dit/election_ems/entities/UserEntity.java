package com.hp.dit.election_ems.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

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

	@Column(name = "district_id")
	private Integer districtId;



	@OneToOne
	@JoinColumn(name = "bank_id")
	private BankMaster bankId;


	@Column(name = "state_id")
	private Integer stateId;
    
    @Column(name = "mobile_number")
    private Long mobileNumber;
    
    @Column(name = "active")
    private boolean active;



	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role_mapping", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "role_id"))
    private List<RolesEntity> roles;

	public BankMaster getBankId() {
		return bankId;
	}

	public void setBankId(BankMaster bankId) {
		this.bankId = bankId;
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

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public List<RolesEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RolesEntity> roles) {
		this.roles = roles;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}


	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}


	@Override
	public String toString() {
		return "UserEntity{" +
				"userId=" + userId +
				", username='" + username + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", rank='" + rank + '\'' +
				", password='" + password + '\'' +
				", districtId=" + districtId +
				", bankId=" + bankId +
				", stateId=" + stateId +
				", mobileNumber=" + mobileNumber +
				", active=" + active +
				", roles=" + roles +
				'}';
	}
}