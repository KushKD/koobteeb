package com.hp.dit.police.inventory.repositories.items;

import com.hp.dit.police.inventory.entities.CategoryItemsEntity;
import com.hp.dit.police.inventory.entities.ItemsEntity;
import com.hp.dit.police.inventory.modals.ItemList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepositoryCustom {

    List<ItemsEntity> getAllItems();
    List<ItemList> getAllItemsSelectedColumns();
    List<ItemsEntity> getAllActiveItems();
    Integer itemCount(String itemName);
    ItemsEntity getItemViaItemId(Integer categoryId);
}
