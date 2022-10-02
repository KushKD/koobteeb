package com.hp.dit.election_ems.Controller.bank;

import com.hp.dit.election_ems.Controller.beat.BeatRestController;
import com.hp.dit.election_ems.entities.BankMaster;
import com.hp.dit.election_ems.repositories.bank.BankRepository;
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
public class BankRestController {

    @Autowired
    BankRepository bankRepository;

    private static final Logger logger = LoggerFactory.getLogger(BeatRestController.class);

    @RequestMapping(value = "/apidataTable/allSdpo", method = RequestMethod.GET)
    public DataTablesOutput<BankMaster> allDistricts_(@Valid DataTablesInput input) {
        System.out.println(input);
        return bankRepository.findAll(input);
    }
}
