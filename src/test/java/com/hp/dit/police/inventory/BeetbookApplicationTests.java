package com.hp.dit.police.inventory;

import com.google.gson.JsonObject;
import com.hp.dit.police.inventory.apicontroller.API;
import com.hp.dit.police.inventory.modals.ItemGroupWiseTotal;
import com.hp.dit.police.inventory.repositories.stateRepository.StateRepository;
import com.hp.dit.police.inventory.repositories.stockin.StockInRepository;
import com.hp.dit.police.inventory.security.EncryptDecrypt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class BeetbookApplicationTests {


    @Autowired
    StockInRepository stockInRepository;


    @Test
    @Transactional
    @Rollback(value = false)
    void showTotalItemsStockIn() throws Exception  {


        List<ItemGroupWiseTotal> items = stockInRepository.showTotalItemsStockIn();
        System.out.println("===ITems===");
        System.out.println(items.toString());

//        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
//                Sort.by(sortField).descending();
//
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
    }


}
