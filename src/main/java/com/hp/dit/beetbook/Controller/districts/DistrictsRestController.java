package com.hp.dit.beetbook.Controller.districts;

import com.hp.dit.beetbook.Controller.beat.BeatRestController;
import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.entities.StatesMaster;
import com.hp.dit.beetbook.repositories.districtRepository.DistrictRepository;
import com.hp.dit.beetbook.repositories.stateRepository.StateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DistrictsRestController {

    @Autowired
    DistrictRepository districtRepository;

    private static final Logger logger = LoggerFactory.getLogger(BeatRestController.class);

    @RequestMapping(value = "/apidataTable/allDistricts", method = RequestMethod.GET)
    public DataTablesOutput<DistrictMaster> allDistricts_(@Valid DataTablesInput input) {
        System.out.println(input);
        return districtRepository.findAll(input);
    }

}
