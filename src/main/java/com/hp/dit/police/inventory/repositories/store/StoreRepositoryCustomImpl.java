package com.hp.dit.police.inventory.repositories.store;
import com.hp.dit.police.inventory.entities.StoreEntity;
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
public class StoreRepositoryCustomImpl implements StoreRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;




    @Override
    public List<StoreEntity> getAllCategories() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<StoreEntity> cq = cb.createQuery(StoreEntity.class);
        Root<StoreEntity> book = cq.from(StoreEntity.class);
       //Predicate isActive_ = cb.equal(book.get("active"), true);
       // Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        //cq.where(isActive_,isDeleted_);
        TypedQuery<StoreEntity> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Integer categoryCount(String CategoryName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<StoreEntity> book = cq.from(StoreEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        Predicate categoryName = cb.equal(book.get("categoryName"), CategoryName);
        cq.where(isActive_,isDeleted_,categoryName);
        cq.select(cb.count(book)).where(isActive_,isDeleted_,categoryName);
        return Math.toIntExact(entityManager.createQuery(cq).getSingleResult());
    }

    @Override
    public StoreEntity getCategoryViaCategoryId(Integer categoryId_) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<StoreEntity> cq = cb.createQuery(StoreEntity.class);
        Root<StoreEntity> book = cq.from(StoreEntity.class);
        Predicate categoryId = cb.equal(book.get("categoryID"), categoryId_);
        cq.where(categoryId);
        TypedQuery<StoreEntity> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }
}