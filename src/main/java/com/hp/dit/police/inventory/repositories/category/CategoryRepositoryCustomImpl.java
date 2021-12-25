package com.hp.dit.police.inventory.repositories.category;
import com.hp.dit.police.inventory.entities.CategoryEntity;
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
    public List<CategoryModal> getCategories() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoryModal> cq = cb.createQuery(CategoryModal.class);
        Root<CategoryEntity> book = cq.from(CategoryEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        cq.where(isActive_);
        cq.multiselect(book.get("categoryID"), book.get("categoryName")).distinct(true);
        TypedQuery<CategoryModal> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Boolean checkCategory(String rolenmae) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CategoryEntity> book = cq.from(CategoryEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate category = cb.equal(book.get("categoryName"), rolenmae);
        cq.where(isActive_,category);
        cq.select(cb.count(book)).where(isActive_,category) ;
        TypedQuery<Long> query =  entityManager.createQuery(cq);
        if(Math.toIntExact(entityManager.createQuery(cq).getSingleResult())>0){
            return true;
        }else{
            return false;
        }


    }
}