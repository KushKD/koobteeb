package com.hp.dit.election_ems.repositories.submoduleoptions;

import com.hp.dit.election_ems.entities.OptionsMaster;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheConfig(cacheNames = "submodules_")
public interface SubModuleOptionRepositoryCustom {


   List<OptionsMaster> getOptionsViaSubModuleId(Integer SubModuleId);


}
