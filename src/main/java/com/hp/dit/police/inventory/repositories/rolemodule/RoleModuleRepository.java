package com.hp.dit.police.inventory.repositories.rolemodule;

import com.hp.dit.police.inventory.entities.ModuleRoleMappingMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleModuleRepository extends CrudRepository<ModuleRoleMappingMaster,Integer>, RoleModuleRepositoryCustom {
}
