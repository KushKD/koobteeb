package com.hp.dit.police.inventory.repositories.stockin;

import com.hp.dit.police.inventory.entities.StockInEntity;
import com.hp.dit.police.inventory.modals.ItemGroupWiseTotal;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface StockInRepositoryCustom {

    List<ItemGroupWiseTotal> showTotalItemsStockIn();
    Page<ItemGroupWiseTotal> showTotalItemsStockInPaged(Pageable pageable);
}
