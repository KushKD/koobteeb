package com.hp.dit.election_ems.entities;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "user_location_logs")
public class BeatOfficerLogs implements Serializable {

    @Id
    @GeneratedValue(generator = "user_location_logs_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "user_location_logs_id_seq", sequenceName = "public.user_location_logs_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private Long logsId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserDatatableEntity userId;

    @OneToOne
    @JoinColumn(name = "role_id")
    private RolesEntity roleId;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;


    @Column(name = "active")
    private Boolean active;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "lastmodifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    public Long getLogsId() {
        return logsId;
    }

    public void setLogsId(Long logsId) {
        this.logsId = logsId;
    }

    public UserDatatableEntity getUserId() {
        return userId;
    }

    public void setUserId(UserDatatableEntity userId) {
        this.userId = userId;
    }

    public RolesEntity getRoleId() {
        return roleId;
    }

    public void setRoleId(RolesEntity roleId) {
        this.roleId = roleId;
    }



    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
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
        return "UserLocationLogsEntity{" +
                "logsId=" + logsId +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", active=" + active +
                ", createdDate=" + createdDate +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
