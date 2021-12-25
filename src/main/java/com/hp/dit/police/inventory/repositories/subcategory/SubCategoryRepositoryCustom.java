package com.hp.dit.police.inventory.repositories.subcategory;

import com.hp.dit.police.inventory.modals.SubCategoryModal;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "subCategory_")
public interface SubCategoryRepositoryCustom {

    @Cacheable
    List<SubCategoryModal> getSubCategories(Integer categoryId);

    Boolean checkSubCategory( String subCategory);
}
