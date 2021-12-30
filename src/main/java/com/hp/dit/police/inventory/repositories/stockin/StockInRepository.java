package com.hp.dit.police.inventory.repositories.stockin;

import com.hp.dit.police.inventory.entities.StockInEntity;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockInRepository extends DataTablesRepository<StockInEntity,Integer>,StockInRepositoryCustom {

}
