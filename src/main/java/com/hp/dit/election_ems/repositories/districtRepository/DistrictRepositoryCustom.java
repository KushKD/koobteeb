package com.hp.dit.election_ems.repositories.districtRepository;

import com.hp.dit.election_ems.entities.DistrictMaster;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheConfig(cacheNames = "districts_")
public interface DistrictRepositoryCustom {

   // @Cacheable
    List<DistrictMaster> findDistrictByStateId(Integer stateId) throws Exception;

   // @Cacheable
    List<DistrictMaster> getAllDistricts() throws Exception;


    DistrictMaster getDistrictViaId(Integer districtId) throws Exception;
    Integer districtCount(String StateName);
}
