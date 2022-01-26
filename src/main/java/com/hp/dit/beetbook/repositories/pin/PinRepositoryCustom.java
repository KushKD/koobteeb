package com.hp.dit.beetbook.repositories.pin;

import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.entities.PinMaster;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheConfig(cacheNames = "pin")
public interface PinRepositoryCustom {

    List<PinMaster> findAllActivePins() throws Exception;
    PinMaster getPinViaId(Integer pin_id) throws Exception;
    PinMaster findActivePins(String districtId, String pin) throws Exception;

}
