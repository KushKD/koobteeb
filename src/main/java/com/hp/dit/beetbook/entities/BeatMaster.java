package com.hp.dit.beetbook.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.locationtech.jts.geom.*;

@Entity
@Table(name="mst_beat")
public class BeatMaster implements Serializable {

    @Id
    @GeneratedValue(generator = "mst_beat_beat_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_beat_beat_id_seq", sequenceName = "public.mst_beat_beat_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "beat_id")
    private Integer beatId;

    @Column(name = "beat_name")
    private String beatName;

    @OneToOne()
    @JoinColumn(name = "state_id")
    private StatesMaster stateID;

    @OneToOne()
    @JoinColumn(name = "district_id")
    private DistrictMaster districtId;

    @Column(name = "sosdpo_id")
    private Integer sosdpoId;


    @Column(name = "ps_id")
    private Integer psId;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "is_deleted")
    private Boolean deleted;



//    @Column(name = "latitude")
//    private Double latitude;
//
//    @Column(name = "longitude")
//    private Double longitude;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "lastmodifieddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;


//    @Column(nullable = false, name="beat_geometry" ,columnDefinition = "geometry(Point,4326)")
//    @JsonDeserialize(as = Point.class)
//    private Geometry  beatGeometry;

//    public Geometry getBeatGeometry() {
//        return beatGeometry;
//    }
//
//    public void setBeatGeometry(Geometry beatGeometry) {
//        this.beatGeometry = beatGeometry;
//    }
//
//    public Double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(Double latitude) {
//        this.latitude = latitude;
//    }
//
//    public Double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(Double longitude) {
//        this.longitude = longitude;
//    }

    public Integer getBeatId() {
        return beatId;
    }

    public void setBeatId(Integer beatId) {
        this.beatId = beatId;
    }

    public String getBeatName() {
        return beatName;
    }

    public void setBeatName(String beatName) {
        this.beatName = beatName;
    }

    public StatesMaster getStateID() {
        return stateID;
    }

    public void setStateID(StatesMaster stateID) {
        this.stateID = stateID;
    }

    public DistrictMaster getDistrictId() {
        return districtId;
    }

    public void setDistrictId(DistrictMaster districtId) {
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

    public Boolean getActive() {
        return active;
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

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "BeatMaster{" +
                "beatId=" + beatId +
                ", beatName='" + beatName + '\'' +
                ", stateID=" + stateID +
                ", districtId=" + districtId +
                ", sosdpoId=" + sosdpoId +
                ", psId=" + psId +
                ", active=" + active +
                ", deleted=" + deleted +
                ", createdDate=" + createdDate +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
