package com.hp.dit.beetbook.repositories.stateRepository;

import com.hp.dit.beetbook.entities.StatesMaster;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = "states")
public interface StateRepository extends CrudRepository<StatesMaster, Integer>, StateRepositoryCustom {

}
