package com.hp.dit.election_ems.repositories.stateRepository;

import com.hp.dit.election_ems.entities.StatesMaster;
import com.hp.dit.election_ems.modals.StateModal;
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
