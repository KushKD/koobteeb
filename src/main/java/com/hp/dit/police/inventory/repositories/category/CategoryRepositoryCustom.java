package com.hp.dit.police.inventory.repositories.category;

import com.hp.dit.police.inventory.entities.CategoryEntity;
import com.hp.dit.police.inventory.entities.StatesMaster;
import com.hp.dit.police.inventory.modals.CategoryModal;
import com.hp.dit.police.inventory.modals.StateModal;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheConfig(cacheNames = "category_")
public interface CategoryRepositoryCustom {


   // @Cacheable
    List<CategoryEntity> getAllCategories();
    Integer categoryCount(String CategoryName);
    CategoryEntity getCategoryViaCategoryId(Integer categoryId);
}