package com.hp.dit.election_ems.entities;



import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "user_location_logs")
public class UserLocationLogsEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "user_location_logs_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "user_location_logs_id_seq", sequenceName = "public.user_location_logs_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private Long logsId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "beat_id")
    private Integer beat_id;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "username")
    private String username;

    @Column(name = "mobile_number")
    private Long mobileNumber;

    @Column(nullable = false, name="location_points" ,columnDefinition = "geometry(Point,4326)")
    @JsonDeserialize(as = Point.class)
    private Geometry locationPoints;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getBeat_id() {
        return beat_id;
    }

    public void setBeat_id(Integer beat_id) {
        this.beat_id = beat_id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Geometry getLocationPoints() {
        return locationPoints;
    }

    public void setLocationPoints(Geometry locationPoints) {
        this.locationPoints = locationPoints;
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
                ", beat_id=" + beat_id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", username='" + username + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", locationPoints=" + locationPoints +
                ", active=" + active +
                ", createdDate=" + createdDate +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
