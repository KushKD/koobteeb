package com.hp.dit.election_ems.Controller.beat;

import com.hp.dit.election_ems.entities.BeatMaster;
import com.hp.dit.election_ems.repositories.beats.BeatRepository;
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
public class BeatRestController {


    @Autowired
    BeatRepository beatRepository;

    private static final Logger logger = LoggerFactory.getLogger(BeatRestController.class);

    @RequestMapping(value = "/apidataTable/allbeats", method = RequestMethod.GET)
    public DataTablesOutput<BeatMaster> allbeats_( @Valid DataTablesInput input) {
        System.out.println(input);
        return beatRepository.findAll(input);
    }

}
