package com.hp.dit.police.inventory.repositories.units;
import com.hp.dit.police.inventory.entities.StoreEntity;
import com.hp.dit.police.inventory.entities.UnitsEntity;
import com.hp.dit.police.inventory.repositories.store.StoreRepositoryCustom;
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
public class UnitsRepositoryCustomImpl implements UnitsRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;




    @Override
    public List<UnitsEntity> getAllUnits() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UnitsEntity> cq = cb.createQuery(UnitsEntity.class);
        Root<UnitsEntity> book = cq.from(UnitsEntity.class);
        TypedQuery<UnitsEntity> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<UnitsEntity> getAllActiveUnits() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UnitsEntity> cq = cb.createQuery(UnitsEntity.class);
        Root<UnitsEntity> book = cq.from(UnitsEntity.class);
        Predicate unitId = cb.equal(book.get("active"), true);
        cq.where(unitId);
        TypedQuery<UnitsEntity> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Integer unitCount(String unitName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<UnitsEntity> book = cq.from(UnitsEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        Predicate unitName_ = cb.equal(book.get("unitName"), unitName);
        cq.where(isActive_,isDeleted_,unitName_);
        cq.select(cb.count(book)).where(isActive_,isDeleted_,unitName_);
        return Math.toIntExact(entityManager.createQuery(cq).getSingleResult());
    }

    @Override
    public UnitsEntity getUnitViaUnitId(Integer unitId_) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UnitsEntity> cq = cb.createQuery(UnitsEntity.class);
        Root<UnitsEntity> book = cq.from(UnitsEntity.class);
        Predicate unitId = cb.equal(book.get("unitId"), unitId_);
        cq.where(unitId);
        TypedQuery<UnitsEntity> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }
}