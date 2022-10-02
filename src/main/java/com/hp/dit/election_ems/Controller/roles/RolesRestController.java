package com.hp.dit.election_ems.Controller.roles;

import com.hp.dit.election_ems.entities.RolesEntity;
import com.hp.dit.election_ems.repositories.roles.RolesRepositoryDatatables;
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
public class RolesRestController {

    @Autowired
    RolesRepositoryDatatables rolesRepository;

    private static final Logger logger = LoggerFactory.getLogger(RolesRestController.class);

    @RequestMapping(value = "/apidataTable/getRoles", method = RequestMethod.GET)
    public DataTablesOutput<RolesEntity> getUsers_(@Valid DataTablesInput input) {
        System.out.println(input);
        return rolesRepository.findAll(input);
    }

}
