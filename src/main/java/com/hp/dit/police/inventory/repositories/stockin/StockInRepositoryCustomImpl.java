package com.hp.dit.police.inventory.repositories.stockin;

import com.hp.dit.police.inventory.entities.CategoryItemsEntity;
import com.hp.dit.police.inventory.entities.StockInEntity;
import com.hp.dit.police.inventory.entities.StoreEntity;
import com.hp.dit.police.inventory.modals.ItemGroupWiseTotal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.awt.print.Pageable;
import java.util.List;

@Service
public class StockInRepositoryCustomImpl implements StockInRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ItemGroupWiseTotal> showTotalItemsStockIn() {
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
        TypedQuery<ItemGroupWiseTotal> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Page<ItemGroupWiseTotal> showTotalItemsStockInPaged(Pageable pageable) {

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
        TypedQuery<ItemGroupWiseTotal> query =  entityManager.createQuery(cq);
      //  query.setFirstResult(0);
       // query.setMaxResults(3);

       //  query.getResultList();


         /*
         criteria.setFirstResult(0);
criteria.setMaxResults(pageSize);
          */
        return null;
    }
}
