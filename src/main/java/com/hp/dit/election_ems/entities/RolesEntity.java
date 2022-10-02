package com.hp.dit.election_ems.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="roles")
public class RolesEntity   {

    @Id
    @GeneratedValue(generator = "roles_role_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "roles_role_id_seq", sequenceName = "public.roles_role_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name="role_id")
    private int roleId;

    @Column(name="role_name")
    private String roleName;

    @Column(name="role_description")
    private String roleDescription;
    
    @Column(name="active")
    private boolean active;

	@Column(name = "createddate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "lastmodifieddate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "user_role_mapping", joinColumns = @JoinColumn(name = "roleId", referencedColumnName = "role_id"),
//                                           inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "user_id"))
//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<UserEntity> users;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

//	public List<UserEntity> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<UserEntity> users) {
//		this.users = users;
//	}


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
		return "RolesEntity{" +
				"roleId=" + roleId +
				", roleName='" + roleName + '\'' +
				", roleDescription='" + roleDescription + '\'' +
				", active=" + active +
				", createdDate=" + createdDate +
				", updatedOn=" + updatedOn +
				'}';
	}
}