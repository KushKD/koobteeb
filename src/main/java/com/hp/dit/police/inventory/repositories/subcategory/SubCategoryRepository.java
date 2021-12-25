package com.hp.dit.police.inventory.repositories.subcategory;

import com.hp.dit.police.inventory.entities.SubCategoryEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "subCategory")
public interface SubCategoryRepository extends CrudRepository<SubCategoryEntity,Integer>, SubCategoryRepositoryCustom {


}
