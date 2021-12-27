package com.hp.dit.police.inventory.repositories.itemcategory;

import com.hp.dit.police.inventory.entities.CategoryItemsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends CrudRepository<CategoryItemsEntity,Integer>,ItemCategoryRepositoryCustom {
}
