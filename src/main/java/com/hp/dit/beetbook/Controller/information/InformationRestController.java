package com.hp.dit.beetbook.Controller.information;

import com.hp.dit.beetbook.Controller.beat.BeatRestController;
import com.hp.dit.beetbook.entities.AllInformationEntity;
import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.entities.InformationEntity;
import com.hp.dit.beetbook.repositories.districtRepository.DistrictRepository;
import com.hp.dit.beetbook.repositories.information.AllInformationRepository;
import com.hp.dit.beetbook.repositories.information.InformationRepository;
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
public class InformationRestController {

    @Autowired
    AllInformationRepository informationRepository;

    private static final Logger logger = LoggerFactory.getLogger(BeatRestController.class);

    @RequestMapping(value = "/apidataTable/allInformation", method = RequestMethod.GET)
    public DataTablesOutput<AllInformationEntity> allInformation_(@Valid DataTablesInput input) {
        System.out.println(input);
        return informationRepository.findAll(input);
    }

}
