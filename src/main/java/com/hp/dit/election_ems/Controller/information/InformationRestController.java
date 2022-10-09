package com.hp.dit.election_ems.Controller.information;

import com.hp.dit.election_ems.Controller.beat.BeatRestController;
import com.hp.dit.election_ems.entities.AllInformationEntity;
import com.hp.dit.election_ems.repositories.information.AllInformationRepository;
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