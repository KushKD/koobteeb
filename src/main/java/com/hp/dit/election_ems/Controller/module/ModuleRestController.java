package com.hp.dit.election_ems.Controller.module;

import com.hp.dit.election_ems.Controller.beat.BeatRestController;
import com.hp.dit.election_ems.entities.ModuleMaster;
import com.hp.dit.election_ems.repositories.modules.ModuleRepository;
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
public class ModuleRestController {

    @Autowired
    ModuleRepository moduleRepository;

    private static final Logger logger = LoggerFactory.getLogger(BeatRestController.class);

    @RequestMapping(value = "/apidataTable/allModules", method = RequestMethod.GET)
    public DataTablesOutput<ModuleMaster> allModules_(@Valid DataTablesInput input) {
        System.out.println(input);
        return moduleRepository.findAll(input);
    }

}