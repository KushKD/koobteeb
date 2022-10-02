package com.hp.dit.election_ems.repositories.rolemodule;

import com.hp.dit.election_ems.entities.ModuleRoleMappingMaster;
import com.hp.dit.election_ems.modals.moduleRole.ModuleRoleList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheConfig(cacheNames = "modules_roles_")
public interface RoleModuleRepositoryCustom {


    List<ModuleRoleList> getAllActiveModulesViaRoles() throws Exception;
    ModuleRoleList getModuleRoleViaId(Integer id) throws Exception;
    Integer moduleRoleCount(Integer module_id, Integer role_id);
    ModuleRoleMappingMaster getModuleRoleViaId_(Integer id);


}
