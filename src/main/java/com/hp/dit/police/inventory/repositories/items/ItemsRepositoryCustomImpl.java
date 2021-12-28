package com.hp.dit.police.inventory.repositories.items;

import com.hp.dit.police.inventory.entities.CategoryItemsEntity;
import com.hp.dit.police.inventory.entities.ItemsEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class ItemsRepositoryCustomImpl implements ItemsRepositoryCustom  {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ItemsEntity> getAllItems() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemsEntity> cq = cb.createQuery(ItemsEntity.class);
        Root<ItemsEntity> book = cq.from(ItemsEntity.class);
        //Predicate isActive_ = cb.equal(book.get("active"), true);
        // Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        //cq.where(isActive_,isDeleted_);
        TypedQuery<ItemsEntity> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<ItemsEntity> getAllActiveItems() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemsEntity> cq = cb.createQuery(ItemsEntity.class);
        Root<ItemsEntity> book = cq.from(ItemsEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        cq.where(isActive_);
        TypedQuery<ItemsEntity> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Integer itemCount(String itemName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ItemsEntity> book = cq.from(ItemsEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        Predicate itemName_ = cb.equal(book.get("itemsName"), itemName);
        cq.select(cb.count(book)).where(isActive_,isDeleted_,itemName_);
        return Math.toIntExact(entityManager.createQuery(cq).getSingleResult());
    }

    @Override
    public ItemsEntity getItemViaItemId(Integer itemId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemsEntity> cq = cb.createQuery(ItemsEntity.class);
        Root<CategoryItemsEntity> book = cq.from(CategoryItemsEntity.class);
        Predicate itemId_ = cb.equal(book.get("itemsId"), itemId);
        cq.where(itemId_);
        TypedQuery<ItemsEntity> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }
}
