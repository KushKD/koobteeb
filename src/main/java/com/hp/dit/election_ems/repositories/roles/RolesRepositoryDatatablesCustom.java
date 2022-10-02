package com.hp.dit.election_ems.repositories.roles;

import com.hp.dit.election_ems.entities.ModuleMaster;
import com.hp.dit.election_ems.entities.RolesEntity;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepositoryDatatablesCustom  {

    RolesEntity getRoleViaId(Integer roleId) throws Exception;
}
