package com.hp.dit.beetbook.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "mst_state")
public class StatesMaster implements Serializable {

	@Id
	@GeneratedValue(generator = "mst_state_state_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "mst_state_state_id_seq", sequenceName = "public.mst_state_state_id_seq", initialValue = 1, allocationSize = 1)
	@Column(name="state_id")
	private Integer stateID;

	@Column(name="state_name")
	private String stateName;

	@Column(name="is_active")
	private boolean isActive;

	@Column(name="is_deleted")
	private boolean isDeleted;

	@Column(name = "created_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;


	public Integer getStateID() {
		return stateID;
	}

	public void setStateID(Integer stateID) {
		this.stateID = stateID;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "StatesMaster{" +
				"stateID=" + stateID +
				", stateName='" + stateName + '\'' +
				", isActive=" + isActive +
				", isDeleted=" + isDeleted +
				", createdDate=" + createdDate +
				", updatedOn=" + updatedOn +
				'}';
	}
}