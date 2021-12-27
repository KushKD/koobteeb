package com.hp.dit.police.inventory.repositories.store;

import com.hp.dit.police.inventory.entities.StoreEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = "category")
public interface StoreRepository extends CrudRepository<StoreEntity,Integer>, StoreRepositoryCustom {

}