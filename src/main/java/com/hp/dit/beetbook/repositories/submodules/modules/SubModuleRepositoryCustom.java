package com.hp.dit.beetbook.repositories.submodules.modules;

import com.hp.dit.beetbook.entities.ModuleMaster;
import com.hp.dit.beetbook.entities.SubModuleMaster;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "submodules_")
public interface SubModuleRepositoryCustom {


    List<SubModuleMaster> findSubModuleByModueId(Integer moduleId) throws Exception;
    List<SubModuleMaster> getAllSubModules() throws Exception;
    List<SubModuleMaster> getAllActiveSubModules() throws Exception;
    SubModuleMaster getSubModuleViaId(Integer submoduleId) throws Exception;
    Integer submoduleCount(String submodulename);


}
