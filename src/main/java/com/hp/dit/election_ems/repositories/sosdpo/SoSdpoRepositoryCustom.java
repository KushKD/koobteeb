package com.hp.dit.election_ems.repositories.sosdpo;

import com.hp.dit.election_ems.entities.S0SdpoMaster;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheConfig(cacheNames = "sosdpo_")
public interface SoSdpoRepositoryCustom {


    //@Cacheable
    List<S0SdpoMaster> getAllSOSdpo() throws Exception;
    S0SdpoMaster getAllSOSdpoViaId(Integer districtId) throws Exception;
    Integer sdpoCount(String so_sdpo);
    List<S0SdpoMaster> getAllActiveSOSdo() throws Exception;
}
