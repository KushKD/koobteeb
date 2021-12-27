package com.hp.dit.police.inventory.repositories.itemcategory;

import com.hp.dit.police.inventory.entities.CategoryItemsEntity;
import com.hp.dit.police.inventory.entities.StoreEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "categories_")
public interface ItemCategoryRepositoryCustom  {

    List<CategoryItemsEntity> getAllCategories();
    Integer categoryCount(String CategoryName);
    CategoryItemsEntity getCategoryViaCategoryId(Integer categoryId);

}
