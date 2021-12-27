package com.hp.dit.police.inventory.repositories.category;
import com.hp.dit.police.inventory.entities.CategoryEntity;
import com.hp.dit.police.inventory.entities.StatesMaster;
import com.hp.dit.police.inventory.modals.CategoryModal;
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
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;




    @Override
    public List<CategoryEntity> getAllCategories() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> cq = cb.createQuery(CategoryEntity.class);
        Root<CategoryEntity> book = cq.from(CategoryEntity.class);
       //Predicate isActive_ = cb.equal(book.get("active"), true);
       // Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        //cq.where(isActive_,isDeleted_);
        TypedQuery<CategoryEntity> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Integer categoryCount(String CategoryName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CategoryEntity> book = cq.from(CategoryEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        Predicate categoryName = cb.equal(book.get("categoryName"), CategoryName);
        cq.where(isActive_,isDeleted_,categoryName);
        cq.select(cb.count(book)).where(isActive_,isDeleted_,categoryName);
        return Math.toIntExact(entityManager.createQuery(cq).getSingleResult());
    }

    @Override
    public CategoryEntity getCategoryViaCategoryId(Integer categoryId_) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> cq = cb.createQuery(CategoryEntity.class);
        Root<CategoryEntity> book = cq.from(CategoryEntity.class);
        Predicate categoryId = cb.equal(book.get("categoryID"), categoryId_);
        cq.where(categoryId);
        TypedQuery<CategoryEntity> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }
}