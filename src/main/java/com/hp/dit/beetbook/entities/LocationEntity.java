package com.hp.dit.beetbook.entities;



import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "mst_locations")
public class LocationEntity implements Serializable {


    @Id
    @GeneratedValue
    private Integer location_id;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "location_points")
    private Point locationPoints;

    @Column(name = "is_active")
    private Boolean active;

    @Column(name = "is_deleted")
    private Boolean deleted;



    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Point getLocationPoints() {
        return locationPoints;
    }

    public void setLocationPoints(Point locationPoints) {
        this.locationPoints = locationPoints;
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

    @Override
    public String toString() {
        return "LocationEntity{" +
                "location_id=" + location_id +
                ", locationName='" + locationName + '\'' +
                ", locationPoints=" + locationPoints +
                ", active=" + active +
                ", deleted=" + deleted +
                '}';
    }
}
