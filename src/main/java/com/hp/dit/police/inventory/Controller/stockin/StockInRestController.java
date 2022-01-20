package com.hp.dit.police.inventory.Controller.stockin;

import com.hp.dit.police.inventory.entities.StockInEntity;
import com.hp.dit.police.inventory.repositories.stockin.StockInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class StockInRestController {

    @Autowired
    StockInRepository stockInRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/api/stokin", method = RequestMethod.POST)
    public DataTablesOutput<StockInEntity> listPOST(@Valid @RequestBody DataTablesInput input) {
        System.out.println(input);
        return stockInRepository.findAll(input);
    }
//@RequestParam(value = "id", required = true) String id

//    @RequestMapping(value = "/api/stock", method = RequestMethod.POST)
//    public DataTablesOutput<StockInEntity> getItems(@Valid @RequestBody DataTablesInput input) {
//        System.out.println(input);
//        Specification<StockInEntity> specification = (Specification<StockInEntity>) (root, query, criteriaBuilder) -> {
//
//            criteriaBuilder = entityManager.getCriteriaBuilder();
//            CriteriaQuery<ItemGroupWiseTotal> cq = criteriaBuilder.createQuery(ItemGroupWiseTotal.class);
//            Root<StockInEntity> book = cq.from(StockInEntity.class);
//            cq.groupBy(
//                    book.get("policeLines").<Integer>get("policelineId"),
//                    book.get("policeLines").<String>get("policelineName"),
//                    book.get("items").<StoreEntity>get("store").<String>get("storeName"),
//                    book.get("items").<CategoryItemsEntity>get("categoryItemsEntity").<String>get("categoryName"),
//                    book.get("items").<String>get("itemsName"),
//                    book.get("items").<Integer>get("itemsId")
//            );
//
//             cq.multiselect(
//                    book.get("policeLines").<Integer>get("policelineId"),
//                    book.get("policeLines").<String>get("policelineName"),
//                    book.get("items").<StoreEntity>get("store").<String>get("storeName"),
//                    book.get("items").<CategoryItemsEntity>get("categoryItemsEntity").<String>get("categoryName"),
//                    book.get("items").<String>get("itemsName"),
//                    book.get("items").<Integer>get("itemsId"),
//                    criteriaBuilder.sum(book.<Long>get("quantity"))
//
//            );
//
//            return cq.getRestriction();
//        };
//        return stockInRepository.findAll(input,specification);
//    }
}


