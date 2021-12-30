package com.hp.dit.police.inventory.Controller.districts;

import com.hp.dit.police.inventory.entities.DistrictMaster;
import com.hp.dit.police.inventory.repositories.districtRepository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DistrictsRestController {

    @Autowired
    DistrictRepository districtRepository;

    @RequestMapping(value = "/api/districts", method = RequestMethod.POST)
    public DataTablesOutput<DistrictMaster> listPOST(@Valid @RequestBody DataTablesInput input) {
        return districtRepository.findAll(input);
    }
}
