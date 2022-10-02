package com.hp.dit.election_ems.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="mst_district")
public class DistrictMaster implements Serializable {

	@Id
	@GeneratedValue(generator = "mst_district_district_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "mst_district_district_id_seq", sequenceName = "public.mst_district_district_id_seq", initialValue = 1, allocationSize = 1)
	@Column(name = "district_id")
	private Integer districtId;

	@Column(name = "district_name")
	private String districtName;

	@Column(name = "is_active")
	private Boolean active;

	@OneToOne
	@JoinColumn(name = "state_id")
	private StatesMaster stateID;

	@Column(name = "is_deleted")
	private Boolean deleted;

	@Column(name = "created_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Boolean getActive() {
		return active;
	}

	public StatesMaster getStateID() {
		return stateID;
	}

	public void setStateID(StatesMaster stateID) {
		this.stateID = stateID;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@Override
	public String toString() {
		return "DistrictMaster{" +
				"districtId=" + districtId +
				", districtName='" + districtName + '\'' +
				", active=" + active +
				", stateID=" + stateID +
				", deleted=" + deleted +
				", createdDate=" + createdDate +
				", updatedOn=" + updatedOn +
				'}';
	}
}