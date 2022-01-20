package com.hp.dit.police.inventory;

import com.hp.dit.police.inventory.entities.CategoryItemsEntity;
import com.hp.dit.police.inventory.entities.StockInEntity;
import com.hp.dit.police.inventory.entities.StoreEntity;
import com.hp.dit.police.inventory.modals.ItemGroupWiseTotal;
import com.hp.dit.police.inventory.repositories.stockin.StockInRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@SpringBootTest
class BeetbookApplicationTests {


    @Autowired
    StockInRepository stockInRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Test
    @Transactional
    @Rollback(value = false)
    void showTotalItemsStockIn() throws Exception  {


        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemGroupWiseTotal> cq = cb.createQuery(ItemGroupWiseTotal.class);
        Root<StockInEntity> book = cq.from(StockInEntity.class);
        cq.groupBy(
                book.get("policeLines").<Integer>get("policelineId"),
                book.get("policeLines").<String>get("policelineName"),
                book.get("items").<StoreEntity>get("store").<String>get("storeName"),
                book.get("items").<CategoryItemsEntity>get("categoryItemsEntity").<String>get("categoryName"),
                book.get("items").<String>get("itemsName"),
                book.get("items").<Integer>get("itemsId")
        );

        cq.multiselect(
                book.get("policeLines").<Integer>get("policelineId"),
                book.get("policeLines").<String>get("policelineName"),
                book.get("items").<StoreEntity>get("store").<String>get("storeName"),
                book.get("items").<CategoryItemsEntity>get("categoryItemsEntity").<String>get("categoryName"),
                book.get("items").<String>get("itemsName"),
                book.get("items").<Integer>get("itemsId"),
                cb.sum(book.<Long>get("quantity"))

        );
        cq.orderBy(cb.asc(book.get("items").<String>get("itemsName")));


        TypedQuery<ItemGroupWiseTotal> query =  entityManager.createQuery(cq);
        int totalRows = query.getResultList().size();
        // Fetches the count of all Books as per given criteria
        //ItemGroupWiseTotal totalRows = query.getSingleResult();

        Pageable pageable = PageRequest.of(1, 5);  //page no and page size
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        System.out.println(query.getResultList().toString());

        Page<ItemGroupWiseTotal> result = new PageImpl<>(query.getResultList(), pageable, totalRows);

        System.out.println(result.getTotalPages());
        System.out.println(result.getPageable().getPageSize());
        System.out.println(result.getPageable().getPageNumber());
        System.out.println(result.getTotalElements());



    }

   


}
