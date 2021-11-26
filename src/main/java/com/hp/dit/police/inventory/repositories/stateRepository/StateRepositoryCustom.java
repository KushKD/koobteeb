package com.hp.dit.police.inventory.repositories.stateRepository;

import com.hp.dit.police.inventory.entities.StatesMaster;
import com.hp.dit.police.inventory.modals.StateModal;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "states_")
public interface StateRepositoryCustom {


    @Cacheable
    List<StatesMaster> getAllStates();

    List<StatesMaster> getCompleteListStates();
    StatesMaster getStateViaStateId(Integer stateID);
    List<StateModal> getHimachalState();
    Integer stateCount(String StateName);

}
