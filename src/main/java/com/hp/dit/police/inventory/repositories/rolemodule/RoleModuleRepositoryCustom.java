package com.hp.dit.police.inventory.repositories.rolemodule;

import com.hp.dit.police.inventory.entities.ModuleRoleMappingMaster;
import com.hp.dit.police.inventory.modals.moduleRole.ModuleRoleList;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "modules_roles_")
public interface RoleModuleRepositoryCustom {


    List<ModuleRoleList> getAllActiveModulesViaRoles() throws Exception;
    ModuleRoleList getModuleRoleViaId(Integer id) throws Exception;
    Integer moduleRoleCount(Integer module_id, Integer role_id);
    ModuleRoleMappingMaster getModuleRoleViaId_(Integer id);


}
