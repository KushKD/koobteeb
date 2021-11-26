package com.hp.dit.police.inventory.repositories.policestationRepository;

import com.hp.dit.police.inventory.entities.PoliceStationMaster;
import org.springframework.cache.annotation.CacheConfig;
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
    List<PoliceStationMaster> getAllActivePoliceStationViaDistrictSoSdpo(Integer stateId,Integer DistrictId, Integer SoSdpoId) throws Exception;


}
