package com.hp.dit.beetbook.repositories.rolemodule;

import com.hp.dit.beetbook.entities.ModuleRoleMappingMaster;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleModuleRepository extends DataTablesRepository<ModuleRoleMappingMaster,Integer>, RoleModuleRepositoryCustom {
}
