package com.hp.dit.election_ems.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="module_role_mapping")
public class ModuleRoleMappingMaster {

    @Id
    @GeneratedValue(generator = "module_role_mapping_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "module_role_mapping_id_seq", sequenceName = "public.module_role_mapping_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name="id")
    private int id;

	@OneToOne
	@JoinColumn(name="module_id")
	private ModuleMaster moduleId;

	@OneToOne
	@JoinColumn(name="role_id")
	private RolesEntity roleId;
    
    @Column(name="active")
    private boolean active;

	@Column(name = "createddate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "lastmodifieddate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ModuleMaster getModuleId() {
		return moduleId;
	}

	public void setModuleId(ModuleMaster moduleId) {
		this.moduleId = moduleId;
	}

	public RolesEntity getRoleId() {
		return roleId;
	}

	public void setRoleId(RolesEntity roleId) {
		this.roleId = roleId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
		return "ModuleRoleMappingMaster{" +
				"id=" + id +
				", moduleId=" + moduleId +
				", roleId=" + roleId +
				", active=" + active +
				", createdDate=" + createdDate +
				", updatedOn=" + updatedOn +
				'}';
	}
}