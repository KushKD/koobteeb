package com.hp.dit.beetbook.Service;

import com.hp.dit.beetbook.entities.LocationEntity;
import com.hp.dit.beetbook.Repository.ProjectRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<LocationEntity> findAll(){
        return projectRepository.findAll();
    }

    public Optional<LocationEntity> findById(int id) {
        return projectRepository.findById(id);
    }

//    public List<LocationEntity> findAllLocationsWithinDistrict(int districtId){
//        return projectRepository.findAllLocationsWithinDistrict(districtId);
//    }

    public List<LocationEntity> findAllNearbyLocations(Double userLongitude, Double userLatitude){
        return projectRepository.findAllNearbyLocations(userLongitude, userLatitude);
    }

    public void deleteById(int id){
        projectRepository.deleteById(id);
    }

    public void saveLocation(String Name, Double longitude, Double latitude){
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setLocationName(Name);
        locationEntity.setLocationPoints(new GeometryFactory().createPoint(new Coordinate(longitude, latitude)));
        projectRepository.save(locationEntity);
    }
}
