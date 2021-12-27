package com.hp.dit.police.inventory.repositories.store;

import com.hp.dit.police.inventory.entities.StoreEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheConfig(cacheNames = "category_")
public interface StoreRepositoryCustom {


   // @Cacheable
    List<StoreEntity> getAllCategories();
    Integer categoryCount(String CategoryName);
    StoreEntity getCategoryViaCategoryId(Integer categoryId);
}