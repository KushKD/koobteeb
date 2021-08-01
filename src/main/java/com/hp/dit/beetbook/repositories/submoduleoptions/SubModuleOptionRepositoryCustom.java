package com.hp.dit.beetbook.repositories.submoduleoptions;

import com.hp.dit.beetbook.entities.OptionsMaster;
import com.hp.dit.beetbook.entities.SubModuleMaster;
import com.hp.dit.beetbook.modals.submoduleModal.SubModuleRoleList;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "submodules_")
public interface SubModuleOptionRepositoryCustom {


   List<OptionsMaster> getOptionsViaSubModuleId(Integer SubModuleId);


}
