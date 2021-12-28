package com.hp.dit.police.inventory.repositories.items;

import com.hp.dit.police.inventory.entities.ItemsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends CrudRepository<ItemsEntity,Integer>,ItemsRepositoryCustom {
}
