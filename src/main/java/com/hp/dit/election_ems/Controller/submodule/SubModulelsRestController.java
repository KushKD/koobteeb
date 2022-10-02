package com.hp.dit.election_ems.Controller.submodule;

import com.hp.dit.election_ems.Controller.beat.BeatRestController;
import com.hp.dit.election_ems.entities.SubModuleMaster;
import com.hp.dit.election_ems.repositories.submodules.SubModuleRepository;
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
public class SubModulelsRestController {

    @Autowired
    SubModuleRepository subModuleRepository;

    private static final Logger logger = LoggerFactory.getLogger(BeatRestController.class);

    @RequestMapping(value = "/apidataTable/allSubModules", method = RequestMethod.GET)
    public DataTablesOutput<SubModuleMaster> allSubModules_(@Valid DataTablesInput input) {
        System.out.println(input);
        return subModuleRepository.findAll(input);
    }

}
