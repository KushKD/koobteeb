package com.hp.dit.police.inventory.repositories.itemcategory;


import com.hp.dit.police.inventory.entities.CategoryItemsEntity;
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
public class ItemCategoryRepositoryCustomImpl implements ItemCategoryRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CategoryItemsEntity> getAllCategories() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryItemsEntity> cq = cb.createQuery(CategoryItemsEntity.class);
        Root<CategoryItemsEntity> book = cq.from(CategoryItemsEntity.class);
        //Predicate isActive_ = cb.equal(book.get("active"), true);
        // Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        //cq.where(isActive_,isDeleted_);
        TypedQuery<CategoryItemsEntity> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Integer categoryCount(String CategoryName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CategoryItemsEntity> book = cq.from(CategoryItemsEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        Predicate categoryName = cb.equal(book.get("categoryName"), CategoryName);
        cq.where(isActive_,isDeleted_,categoryName);
        cq.select(cb.count(book)).where(isActive_,isDeleted_,categoryName);
        return Math.toIntExact(entityManager.createQuery(cq).getSingleResult());
    }

    @Override
    public CategoryItemsEntity getCategoryViaCategoryId(Integer categoryId_) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryItemsEntity> cq = cb.createQuery(CategoryItemsEntity.class);
        Root<CategoryItemsEntity> book = cq.from(CategoryItemsEntity.class);
        Predicate categoryId = cb.equal(book.get("categoryId"), categoryId_);
        cq.where(categoryId);
        TypedQuery<CategoryItemsEntity> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }
}
