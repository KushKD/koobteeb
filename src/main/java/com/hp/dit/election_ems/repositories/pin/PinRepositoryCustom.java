package com.hp.dit.election_ems.repositories.pin;

import com.hp.dit.election_ems.entities.PinMaster;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheConfig(cacheNames = "pin")
public interface PinRepositoryCustom {

    List<PinMaster> findAllActivePins() throws Exception;
    PinMaster getPinViaId(Integer pin_id) throws Exception;
    PinMaster findActivePins(String districtId, String pin) throws Exception;

}
