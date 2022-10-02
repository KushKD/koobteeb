package com.hp.dit.election_ems.repositories.submodules;

import com.hp.dit.election_ems.entities.SubModuleMaster;
import com.hp.dit.election_ems.modals.submoduleModal.SubModuleRoleList;
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
public class SubModuleRepositoryCustomImpl implements SubModuleRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SubModuleMaster> findSubModuleByModueId(Integer moduleId) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubModuleMaster> cq = cb.createQuery(SubModuleMaster.class);
        Root<SubModuleMaster> book = cq.from(SubModuleMaster.class);
        Predicate stateId_ = cb.equal(book.get("moduleId"), moduleId);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate deleted = cb.equal(book.get("deleted"), false);
        cq.where(stateId_,active,deleted);
        TypedQuery<SubModuleMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<SubModuleMaster> getAllSubModules() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubModuleMaster> cq = cb.createQuery(SubModuleMaster.class);
        Root<SubModuleMaster> book = cq.from(SubModuleMaster.class);
        TypedQuery<SubModuleMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<SubModuleMaster> getAllActiveSubModules() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubModuleMaster> cq = cb.createQuery(SubModuleMaster.class);
        Root<SubModuleMaster> book = cq.from(SubModuleMaster.class);
        TypedQuery<SubModuleMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public SubModuleMaster getSubModuleViaId(Integer submoduleId) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubModuleMaster> cq = cb.createQuery(SubModuleMaster.class);
        Root<SubModuleMaster> book = cq.from(SubModuleMaster.class);
        Predicate districtId_ = cb.equal(book.get("submoduleId"), submoduleId);
        cq.where(districtId_);
        TypedQuery<SubModuleMaster> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }

    @Override
    public Integer submoduleCount(String submodulename) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<SubModuleMaster> book = cq.from(SubModuleMaster.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        Predicate districtname_ = cb.equal(book.get("submoduleName"), submodulename);
        cq.where(isActive_,isDeleted_,districtname_);
        cq.select(cb.count(book)).where(isActive_,isDeleted_,districtname_);
        return Math.toIntExact(entityManager.createQuery(cq).getSingleResult());
    }

    @Override
    public List<SubModuleRoleList> findSubModulesByModueId(Integer moduleId) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubModuleRoleList> cq = cb.createQuery(SubModuleRoleList.class);
        Root<SubModuleMaster> book = cq.from(SubModuleMaster.class);
        Predicate stateId_ = cb.equal(book.get("moduleId"), moduleId);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate deleted = cb.equal(book.get("deleted"), false);
        cq.where(stateId_,active,deleted);
        cq.multiselect(
                book.get("submoduleId"),
                book.get("submoduleName"),
                book.get("moduleId").<Integer>get("moduleId"),
                book.get("subiconName")
        ).distinct(true);
        TypedQuery<SubModuleRoleList> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }
}
