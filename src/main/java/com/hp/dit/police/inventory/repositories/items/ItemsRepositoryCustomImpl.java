package com.hp.dit.police.inventory.repositories.items;

import com.hp.dit.police.inventory.entities.CategoryItemsEntity;
import com.hp.dit.police.inventory.entities.ItemsEntity;
import com.hp.dit.police.inventory.entities.UserEntity;
import com.hp.dit.police.inventory.modals.ItemList;
import com.hp.dit.police.inventory.modals.ItemListAjax;
import com.hp.dit.police.inventory.modals.LoggedInUserSession;
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
    public List<ItemList> getAllItemsSelectedColumns() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemList> cq = cb.createQuery(ItemList.class);
        Root<ItemsEntity> book = cq.from(ItemsEntity.class);
        cq.multiselect(
                book.get("itemsId"),
                book.get("itemsName"),
                book.get("itemsLetterno"),
                book.get("active"),
                book.get("store").<String>get("storeName"));
        TypedQuery<ItemList> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<ItemListAjax> getAllItemsViaStoreandCategory(Integer cid, Integer sid) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemListAjax> cq = cb.createQuery(ItemListAjax.class);
        Root<ItemsEntity> book = cq.from(ItemsEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        Predicate cat = cb.equal(book.get("categoryItemsEntity").<Integer>get("categoryId"), cid);
        Predicate sto = cb.equal(book.get("store").<Integer>get("storeID"), sid);
        cq.where(isActive_,isDeleted_,cat,sto);
        cq.multiselect(
                book.get("itemsId"),
                book.get("itemsName"),
                book.get("units").<String>get("unitName"));
        TypedQuery<ItemListAjax> query =  entityManager.createQuery(cq);
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
        Root<ItemsEntity> book = cq.from(ItemsEntity.class);
        Predicate itemId_ = cb.equal(book.get("itemsId"), itemId);
        cq.where(itemId_);
        TypedQuery<ItemsEntity> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }
}
