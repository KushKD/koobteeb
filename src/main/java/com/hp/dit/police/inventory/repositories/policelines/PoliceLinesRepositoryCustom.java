package com.hp.dit.police.inventory.repositories.policelines;

import com.hp.dit.police.inventory.entities.PoliceLines;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "sosdpo_")
public interface PoliceLinesRepositoryCustom {


   // @Cacheable
    List<PoliceLines> getAllSOSdpo() throws Exception;
    PoliceLines getAllSOSdpoViaId(Integer districtId) throws Exception;
    Integer sdpoCount(String so_sdpo);
    List<PoliceLines> getAllActiveSOSdo() throws Exception;
}
