package com.hp.dit.beetbook.repositories.districtRepository;

import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.entities.StatesMaster;
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
public class DistrictRepositoryCustomImpl implements DistrictRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<DistrictMaster> findDistrictByStateId(Integer stateId) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<DistrictMaster> cq = cb.createQuery(DistrictMaster.class);
        Root<DistrictMaster> book = cq.from(DistrictMaster.class);
        Predicate stateId_ = cb.equal(book.get("stateID"), stateId);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate deleted = cb.equal(book.get("deleted"), false);
        cq.where(stateId_,active,deleted);
        TypedQuery<DistrictMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<DistrictMaster> getAllDistricts() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<DistrictMaster> cq = cb.createQuery(DistrictMaster.class);
        Root<DistrictMaster> book = cq.from(DistrictMaster.class);
        TypedQuery<DistrictMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public DistrictMaster getDistrictViaId(Integer districtId) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<DistrictMaster> cq = cb.createQuery(DistrictMaster.class);
        Root<DistrictMaster> book = cq.from(DistrictMaster.class);
        Predicate districtId_ = cb.equal(book.get("districtId"), districtId);
        cq.where(districtId_);
        TypedQuery<DistrictMaster> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }

    @Override
    public Integer districtCount(String districtName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DistrictMaster> book = cq.from(DistrictMaster.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        Predicate districtname_ = cb.equal(book.get("districtName"), districtName);
        cq.where(isActive_,isDeleted_,districtname_);
        cq.select(cb.count(book)).where(isActive_,isDeleted_,districtname_);
        return Math.toIntExact(entityManager.createQuery(cq).getSingleResult());
    }


}
