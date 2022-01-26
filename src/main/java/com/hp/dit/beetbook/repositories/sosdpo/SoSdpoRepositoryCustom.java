package com.hp.dit.beetbook.repositories.sosdpo;

import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.entities.S0SdpoMaster;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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
