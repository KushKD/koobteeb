package com.hp.dit.police.inventory.repositories.submoduleoptions;

import com.hp.dit.police.inventory.entities.OptionsMaster;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "submodules_")
public interface SubModuleOptionRepositoryCustom {


   List<OptionsMaster> getOptionsViaSubModuleId(Integer SubModuleId);


}
