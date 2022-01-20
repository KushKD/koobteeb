package com.hp.dit.police.inventory.repositories.stockin;

import com.hp.dit.police.inventory.entities.StockInEntity;
import com.hp.dit.police.inventory.modals.ItemGroupWiseTotal;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockInRepository extends DataTablesRepository<StockInEntity,Integer>,StockInRepositoryCustom {


   // @Query(value = "SELECT stock.policeline_id,pl.policeline_name,store.store_name,cat.category_name, item.items_name,stock.items_id,sum(quantity) FROM mst_stockin as stock INNER JOIN mst_item item on item.items_id=stock.items_id INNER JOIN mst_policelines pl on pl.policeline_id=stock.policeline_id INNER JOIN mst_categoryitems cat on cat.category_id=item.category_id INNER JOIN mst_store store on store.store_id=item.store_id group by stock.items_id,stock.policeline_id,item.items_name,pl.policeline_name,store.store_name,cat.category_name", nativeQuery = true)
   //  List<ItemGroupWiseTotal> showTotalItemsStockIn();
}
