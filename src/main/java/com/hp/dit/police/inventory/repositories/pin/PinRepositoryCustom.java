package com.hp.dit.police.inventory.repositories.pin;

import com.hp.dit.police.inventory.entities.PinMaster;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "pin")
public interface PinRepositoryCustom {

    List<PinMaster> findAllActivePins() throws Exception;
    PinMaster getPinViaId(Integer pin_id) throws Exception;
    PinMaster findActivePins() throws Exception;

}
