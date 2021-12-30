package com.hp.dit.police.inventory.repositories.districtRepository;

import com.hp.dit.police.inventory.entities.DistrictMaster;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "districts_")
public interface DistrictRepositoryCustom {

    @Cacheable
    List<DistrictMaster> findDistrictByStateId(Integer stateId) throws Exception;

    @Cacheable
    List<DistrictMaster> getAllDistricts() throws Exception;


    DistrictMaster getDistrictViaId(Integer districtId) throws Exception;
    Integer districtCount(String StateName);
}
