package com.hp.dit.beetbook.Controller;


import com.hp.dit.beetbook.entities.LocationEntity;
import com.hp.dit.beetbook.Repository.ProjectRepository;
import com.hp.dit.beetbook.Service.ProjectService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
public class ProjectController {

    @Autowired
    private  ProjectService projectService;

    @Autowired
    private ProjectRepository repository;

    @GetMapping("/alllocations")
    public List<LocationEntity> findAll() {
        return repository.getAllLocations();
        //return "jhhg";
    }

    @GetMapping("/findlocation")
    public Optional<LocationEntity> findById(@RequestParam("locationId") int id) throws NotFoundException {
        return projectService.findById(id);
    }

//    @GetMapping("/locationsindistt")
//    public List<LocationEntity> findAllLocationsWithinDistrict(@RequestParam("districtId") int id){
//        return projectService.findAllLocationsWithinDistrict(id);
//    }

    @GetMapping("/nearbylocations")
    public List<LocationEntity> findAllNearbyLocations(@RequestParam("userlocation") List<Double> userLocation){
        return projectService.findAllNearbyLocations(userLocation.get(0)/*longitude*/, userLocation.get(1)/*latitude*/);
    }

    @GetMapping("/deletelocation")
    public void deleteById(@RequestParam("locationId") int locationId){
        projectService.deleteById(locationId);
    }

    @PostMapping("/savelocation")
    public void saveLocation(
            @RequestParam("name") String Name,
            @RequestParam("longitude") Double longitude, @RequestParam("latitude") Double latitude
            ) {
        projectService.saveLocation(Name, longitude,latitude);
    }
}
