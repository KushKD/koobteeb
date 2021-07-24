package com.hp.dit.beetbook.Repository;

import com.hp.dit.beetbook.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<LocationEntity, Integer> {

//    @Query(value = "SELECT nhf.id, nhf.name, nhf.geom "
//            + "FROM nairobi_Health_facilities nhf, nairobi_sub_counties nsc "
//            + "WHERE ST_Within(nhf.geom, nsc.geom) AND nsc.id= :subCountyId"
//            , nativeQuery = true)
//    List<LocationEntity> findAllLocationsWithinDistrict(@Param("districtId")  int districtId);



    @Query(value ="SELECT location_id, ST_AsText(location_points) as location_points  , location_name, is_active, is_deleted FROM mst_locations", nativeQuery = true)
    List<LocationEntity> getAllLocations();




    @Query(value = "SELECT location_id, location_name, location_points, ST_Distance(location_points,ST_SetSRID(ST_Point(:userLongitude,:userLatitude),4326)) AS distance "
            + "FROM mst_locations  "
            + "ORDER BY location_points  <-> ST_SetSRID(ST_Point(:userLongitude,:userLatitude),4326) "
            + "LIMIT 20"
            , nativeQuery = true)
    List<LocationEntity> findAllNearbyLocations(@Param("userLongitude") Double userLongitude,@Param("userLatitude")  Double userLatitude);


}
