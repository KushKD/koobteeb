package com.hp.dit.beetbook.repositories.modules;

import com.hp.dit.beetbook.entities.ModuleMaster;
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
public class ModuleRepositoryCustomImpl implements ModuleRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


//    @Override
//    public List<ModuleMaster> findModuleByRoleId(Integer roleId) throws Exception {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<ModuleMaster> cq = cb.createQuery(ModuleMaster.class);
//        Root<ModuleMaster> book = cq.from(ModuleMaster.class);
//        Predicate stateId_ = cb.equal(book.get("roleId"), roleId);
//        Predicate active = cb.equal(book.get("active"), true);
//        Predicate deleted = cb.equal(book.get("deleted"), false);
//        cq.where(stateId_,active,deleted);
//        TypedQuery<ModuleMaster> query =  entityManager.createQuery(cq);
//        return query.getResultList();
//    }

    @Override
    public List<ModuleMaster> getAllModules() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ModuleMaster> cq = cb.createQuery(ModuleMaster.class);
        Root<ModuleMaster> book = cq.from(ModuleMaster.class);
        TypedQuery<ModuleMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<ModuleMaster> getAllActiveModules() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ModuleMaster> cq = cb.createQuery(ModuleMaster.class);
        Root<ModuleMaster> book = cq.from(ModuleMaster.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        cq.where(isActive_,isDeleted_);
        TypedQuery<ModuleMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public ModuleMaster getModuleViaId(Integer moduleId) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ModuleMaster> cq = cb.createQuery(ModuleMaster.class);
        Root<ModuleMaster> book = cq.from(ModuleMaster.class);
        Predicate districtId_ = cb.equal(book.get("moduleId"), moduleId);
        cq.where(districtId_);
        TypedQuery<ModuleMaster> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }

    @Override
    public Integer moduleCount(String moduleName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ModuleMaster> book = cq.from(ModuleMaster.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        Predicate districtname_ = cb.equal(book.get("moduleName"), moduleName);
        cq.where(isActive_,isDeleted_,districtname_);
        cq.select(cb.count(book)).where(isActive_,isDeleted_,districtname_);
        return Math.toIntExact(entityManager.createQuery(cq).getSingleResult());
    }
}
