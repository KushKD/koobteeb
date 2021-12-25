package com.hp.dit.police.inventory.repositories.subcategory;

import com.hp.dit.police.inventory.entities.SubCategoryEntity;
import com.hp.dit.police.inventory.modals.SubCategoryModal;
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
public class SubCategoryRepositoryCustomImpl implements SubCategoryRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<SubCategoryModal> getSubCategories(Integer categoryId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubCategoryModal> cq = cb.createQuery(SubCategoryModal.class);
        Root<SubCategoryEntity> book = cq.from(SubCategoryEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate categoryId_ = cb.equal(book.get("categoryId"), categoryId);
        cq.where(isActive_,categoryId_);
        cq.multiselect(book.get("subCategoryId"), book.get("subCategoryName")).distinct(true);
        TypedQuery<SubCategoryModal> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }


    @Override
    public Boolean checkSubCategory(String subCategory) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<SubCategoryEntity> book = cq.from(SubCategoryEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate categoryId_ = cb.equal(book.get("subCategoryName"), subCategory);
        cq.where(isActive_,categoryId_);
        cq.select(cb.count(book)).where(isActive_,categoryId_) ;
        TypedQuery<Long> query =  entityManager.createQuery(cq);
        if(Math.toIntExact(entityManager.createQuery(cq).getSingleResult())>0){
            return true;
        }else{
            return false;
        }
    }


}
