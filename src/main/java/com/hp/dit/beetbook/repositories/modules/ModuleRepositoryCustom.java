package com.hp.dit.beetbook.repositories.modules;

import com.hp.dit.beetbook.entities.BeatMaster;
import com.hp.dit.beetbook.entities.ModuleMaster;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepositoryCustom {


   // List<ModuleMaster> findModuleByRoleId(Integer roleId) throws Exception;
    List<ModuleMaster> getAllModules() throws Exception;
    List<ModuleMaster> getAllActiveModules() throws Exception;
    ModuleMaster getModuleViaId(Integer beatId) throws Exception;
    Integer moduleCount(String moduleName);


}
