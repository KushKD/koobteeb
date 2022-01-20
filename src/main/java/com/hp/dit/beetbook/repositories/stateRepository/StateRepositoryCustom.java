package com.hp.dit.beetbook.repositories.stateRepository;

import com.hp.dit.beetbook.entities.StatesMaster;
import com.hp.dit.beetbook.modals.StateModal;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheConfig(cacheNames = "states_")
public interface StateRepositoryCustom {

    List<StatesMaster> getAllStates();
    List<StatesMaster> getCompleteListStates();
    StatesMaster getStateViaStateId(Integer stateID);
    List<StateModal> getHimachalState();
    Integer stateCount(String StateName);

}
