package com.hp.dit.beetbook.modals;

import java.io.Serializable;

public class UsePoJo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1784569477847037016L;
	private Long user_id ;
	private String user_name;
	private Long mobile_number;
	private String firstName;
	private String lastName;
	private String password;
	private Integer stateID;
	private Integer districtId;
	private Integer sosdpoId;
	private Integer psId;
	private Integer beatId;

	public UsePoJo(Long user_id, String user_name, Long mobile_number, String firstName, String lastName, String password, Integer stateID, Integer districtId, Integer sosdpoId, Integer psId, Integer beatId) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.mobile_number = mobile_number;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.stateID = stateID;
		this.districtId = districtId;
		this.sosdpoId = sosdpoId;
		this.psId = psId;
		this.beatId = beatId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Long getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(Long mobile_number) {
		this.mobile_number = mobile_number;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStateID() {
		return stateID;
	}

	public void setStateID(Integer stateID) {
		this.stateID = stateID;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getSosdpoId() {
		return sosdpoId;
	}

	public void setSosdpoId(Integer sosdpoId) {
		this.sosdpoId = sosdpoId;
	}

	public Integer getPsId() {
		return psId;
	}

	public void setPsId(Integer psId) {
		this.psId = psId;
	}

	public Integer getBeatId() {
		return beatId;
	}

	public void setBeatId(Integer beatId) {
		this.beatId = beatId;
	}

	@Override
	public String toString() {
		return "UsePoJo{" +
				"user_id=" + user_id +
				", user_name='" + user_name + '\'' +
				", mobile_number=" + mobile_number +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", password='" + password + '\'' +
				", stateID=" + stateID +
				", districtId=" + districtId +
				", sosdpoId=" + sosdpoId +
				", psId=" + psId +
				", beatId=" + beatId +
				'}';
	}
}
