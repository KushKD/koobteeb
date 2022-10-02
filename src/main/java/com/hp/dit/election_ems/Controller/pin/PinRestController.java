package com.hp.dit.election_ems.Controller.pin;

import com.hp.dit.election_ems.Controller.beat.BeatRestController;
import com.hp.dit.election_ems.entities.PinMaster;
import com.hp.dit.election_ems.repositories.pin.PinRepository;
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
public class PinRestController {

    @Autowired
    PinRepository pinRepository;

    private static final Logger logger = LoggerFactory.getLogger(BeatRestController.class);

    @RequestMapping(value = "/apidataTable/allPins", method = RequestMethod.GET)
    public DataTablesOutput<PinMaster> allPins_(@Valid DataTablesInput input) {
        System.out.println(input);
        return pinRepository.findAll(input);
    }

}
