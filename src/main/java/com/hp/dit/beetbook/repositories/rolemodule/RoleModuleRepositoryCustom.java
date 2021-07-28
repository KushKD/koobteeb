package com.hp.dit.beetbook.repositories.rolemodule;

import com.hp.dit.beetbook.entities.ModuleMaster;
import com.hp.dit.beetbook.entities.ModuleRoleMappingMaster;
import com.hp.dit.beetbook.modals.moduleRole.ModuleRoleList;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "modules_roles_")
public interface RoleModuleRepositoryCustom {


    List<ModuleRoleList> getAllActiveModulesViaRoles() throws Exception;
    ModuleRoleList getModuleRoleViaId(Integer id) throws Exception;


}
