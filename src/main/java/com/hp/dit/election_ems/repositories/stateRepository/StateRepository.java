package com.hp.dit.election_ems.repositories.stateRepository;

import com.hp.dit.election_ems.entities.StatesMaster;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
//@CacheConfig(cacheNames = "states")
public interface StateRepository extends DataTablesRepository<StatesMaster, Integer>, StateRepositoryCustom {

}
