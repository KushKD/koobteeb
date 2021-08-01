package com.hp.dit.beetbook.repositories.information;

import com.hp.dit.beetbook.entities.InformationEntity;
import com.hp.dit.beetbook.entities.SubModuleMaster;
import com.hp.dit.beetbook.modals.information.InformationMarkers;
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
public class InformationRepositoryCustomImpl implements InformationRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<InformationMarkers> getmarkers(Integer moduleId, Integer submoduleId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InformationMarkers> cq = cb.createQuery(InformationMarkers.class);
        Root<InformationEntity> book = cq.from(InformationEntity.class);
        Predicate moduleId_ = cb.equal(book.get("moduleId"), moduleId);
        Predicate subModuleID_ = cb.equal(book.get("submoduleId").<Integer>get("submoduleId"), submoduleId);
        Predicate active = cb.equal(book.get("active"), true);
        cq.where(moduleId_,active,subModuleID_);
        cq.multiselect(
                book.get("latitude"),
                book.get("longitude"),
                book.get("name"),
                book.get("photo"),
                book.get("submoduleId").<String>get("submoduleName"),
                book.get("submoduleId").<Integer>get("submoduleId"),
                book.get("submoduleId").<String>get("subiconName"),
                book.get("moduleId")
        ).distinct(true);
        TypedQuery<InformationMarkers> query =  entityManager.createQuery(cq).setMaxResults(20);
        return query.getResultList();
    }

    @Override
    public List<InformationMarkers> getmarkersLocationWise(Integer moduleId, Integer submoduleId, Double latitude, Double longitude) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InformationMarkers> cq = cb.createQuery(InformationMarkers.class);
        Root<InformationEntity> book = cq.from(InformationEntity.class);
        Predicate moduleId_ = cb.equal(book.get("moduleId"), moduleId);
        Predicate subModuleID_ = cb.equal(book.get("submoduleId").<Integer>get("submoduleId"), submoduleId);
        Predicate latitude_ = cb.equal(book.get("latitude"), latitude);
        Predicate longitude_ = cb.equal(book.get("longitude"), longitude);
        Predicate active = cb.equal(book.get("active"), true);
        cq.where(moduleId_,active,subModuleID_);
        cq.multiselect(
                book.get("latitude"),
                book.get("longitude"),
                book.get("name"),
                book.get("photo"),
                book.get("submoduleId").<String>get("submoduleName"),
                book.get("submoduleId").<Integer>get("submoduleId"),
                book.get("submoduleId").<String>get("subiconName"),
                book.get("moduleId")
        ).distinct(true);
        TypedQuery<InformationMarkers> query =  entityManager.createQuery(cq).setMaxResults(20);
        return query.getResultList();
    }

    @Override
    public List<InformationMarkers> getmarkersViaLocation(Integer moduleId, Integer submoduleId, Integer beatId, Integer psId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InformationMarkers> cq = cb.createQuery(InformationMarkers.class);
        Root<InformationEntity> book = cq.from(InformationEntity.class);
        Predicate moduleId_ = cb.equal(book.get("moduleId"), moduleId);
        Predicate subModuleID_ = cb.equal(book.get("submoduleId").<Integer>get("submoduleId"), submoduleId);
        Predicate latitude_ = cb.equal(book.get("beatId"), beatId);
        Predicate longitude_ = cb.equal(book.get("psId"), psId);
        Predicate active = cb.equal(book.get("active"), true);
        cq.where(moduleId_,active,subModuleID_);
        cq.multiselect(
                book.get("latitude"),
                book.get("longitude"),
                book.get("name"),
                book.get("photo"),
                book.get("submoduleId").<String>get("submoduleName"),
                book.get("submoduleId").<Integer>get("submoduleId"),
                book.get("submoduleId").<String>get("subiconName"),
                book.get("moduleId")
        ).distinct(true);
        TypedQuery<InformationMarkers> query =  entityManager.createQuery(cq).setMaxResults(20);
        return query.getResultList();
    }

    @Override
    public InformationEntity getInformationViaId(Integer id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InformationEntity> cq = cb.createQuery(InformationEntity.class);
        Root<InformationEntity> book = cq.from(InformationEntity.class);
        Predicate districtId_ = cb.equal(book.get("id"), id);
        Predicate active = cb.equal(book.get("active"), true);
        cq.where(districtId_,active);
        TypedQuery<InformationEntity> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }

    @Override
    public InformationEntity getInformationViaId(Integer id, Integer moduleId, Integer SubmoduleId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InformationEntity> cq = cb.createQuery(InformationEntity.class);
        Root<InformationEntity> book = cq.from(InformationEntity.class);
        Predicate districtId_ = cb.equal(book.get("id"), id);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate subModuleID_ = cb.equal(book.get("submoduleId").<Integer>get("submoduleId"), SubmoduleId);
        cq.where(districtId_,active);
        TypedQuery<InformationEntity> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }
}
