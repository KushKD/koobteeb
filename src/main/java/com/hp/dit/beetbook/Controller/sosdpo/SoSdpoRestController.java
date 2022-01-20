package com.hp.dit.beetbook.Controller.sosdpo;

import com.hp.dit.beetbook.Controller.beat.BeatRestController;
import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.entities.S0SdpoMaster;
import com.hp.dit.beetbook.repositories.districtRepository.DistrictRepository;
import com.hp.dit.beetbook.repositories.sosdpo.SoSdpoRepository;
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
public class SoSdpoRestController {

    @Autowired
    SoSdpoRepository soSdpoRepository;

    private static final Logger logger = LoggerFactory.getLogger(BeatRestController.class);

    @RequestMapping(value = "/apidataTable/allSdpo", method = RequestMethod.GET)
    public DataTablesOutput<S0SdpoMaster> allDistricts_(@Valid DataTablesInput input) {
        System.out.println(input);
        return soSdpoRepository.findAll(input);
    }
}
