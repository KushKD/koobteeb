package com.hp.dit.beetbook.Controller.modulerole;

import com.hp.dit.beetbook.Controller.beat.BeatRestController;
import com.hp.dit.beetbook.entities.ModuleRoleMappingMaster;
import com.hp.dit.beetbook.entities.PoliceStationMaster;
import com.hp.dit.beetbook.repositories.policestationRepository.PSRepository;
import com.hp.dit.beetbook.repositories.rolemodule.RoleModuleRepository;
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
public class ModuleRoleRestController {

    @Autowired
    RoleModuleRepository roleModuleRepository;

    private static final Logger logger = LoggerFactory.getLogger(BeatRestController.class);

    @RequestMapping(value = "/apidataTable/allmodulerole", method = RequestMethod.GET)
    public DataTablesOutput<ModuleRoleMappingMaster> allmodulerole_(@Valid DataTablesInput input) {
        System.out.println(input);
        return roleModuleRepository.findAll(input);
    }

}
