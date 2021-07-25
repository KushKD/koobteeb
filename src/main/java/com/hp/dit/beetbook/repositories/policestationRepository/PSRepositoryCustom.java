package com.hp.dit.beetbook.repositories.policestationRepository;

import com.hp.dit.beetbook.entities.PoliceStationMaster;
import com.hp.dit.beetbook.entities.S0SdpoMaster;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "policeStation")
public interface PSRepositoryCustom {



    List<PoliceStationMaster> getAllPoliceStation() throws Exception;
    PoliceStationMaster getPoliceStationViaId(Integer districtId) throws Exception;
    Integer psCount(String so_sdpo);
    List<PoliceStationMaster> getAllActivePoliceStation() throws Exception;
    List<PoliceStationMaster> getAllActivePoliceStationViaDistrictSoSdpo(Integer DistrictId, Integer SoSdpoId) throws Exception;
    List<PoliceStationMaster> getAllActivePoliceStationViaSoSdpo(Integer SoSdpoId) throws Exception;

}
