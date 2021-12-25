package com.hp.dit.police.inventory.repositories.category;

import com.hp.dit.police.inventory.entities.CategoryEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = "category")
public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer>, CategoryRepositoryCustom {

}