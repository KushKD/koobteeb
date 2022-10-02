package com.hp.dit.election_ems.repositories.rolemodule;

import com.hp.dit.election_ems.entities.ModuleRoleMappingMaster;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleModuleRepository extends DataTablesRepository<ModuleRoleMappingMaster,Integer>, RoleModuleRepositoryCustom {
}
