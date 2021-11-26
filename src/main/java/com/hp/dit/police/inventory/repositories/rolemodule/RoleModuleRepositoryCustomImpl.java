package com.hp.dit.police.inventory.repositories.rolemodule;

import com.hp.dit.police.inventory.entities.ModuleRoleMappingMaster;
import com.hp.dit.police.inventory.modals.moduleRole.ModuleRoleList;
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
public class RoleModuleRepositoryCustomImpl implements RoleModuleRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public List<ModuleRoleList> getAllActiveModulesViaRoles() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ModuleRoleList> cq = cb.createQuery(ModuleRoleList.class);
        Root<ModuleRoleMappingMaster> book = cq.from(ModuleRoleMappingMaster.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        cq.where(isActive_);
        cq.multiselect(book.get("id"),
                book.get("moduleId").<String>get("moduleName"),
                book.get("roleId").<String>get("roleName"),
                book.get("moduleId").<Integer>get("moduleId"),
                book.get("roleId").<Integer>get("roleId")).distinct(true);
        TypedQuery<ModuleRoleList> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public ModuleRoleList getModuleRoleViaId(Integer id) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ModuleRoleList> cq = cb.createQuery(ModuleRoleList.class);
        Root<ModuleRoleMappingMaster> book = cq.from(ModuleRoleMappingMaster.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate id_ = cb.equal(book.get("id"), id);
        cq.where(isActive_,id_);
        cq.multiselect(book.get("id"),
                book.get("moduleId").<String>get("moduleName"),
                book.get("roleId").<String>get("roleName"),
                book.get("moduleId").<Integer>get("moduleId"),
                book.get("roleId").<Integer>get("roleId")).distinct(true);
        TypedQuery<ModuleRoleList> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }

    @Override
    public Integer moduleRoleCount(Integer module_id, Integer role_id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ModuleRoleMappingMaster> book = cq.from(ModuleRoleMappingMaster.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate moduleId = cb.equal(book.get("moduleId").<Integer>get("moduleId"), module_id);
        Predicate roleId = cb.equal(book.get("roleId").<Integer>get("roleId"), role_id);
        cq.where(isActive_,moduleId,roleId);
        cq.select(cb.count(book)).where(isActive_,moduleId,roleId);
        return Math.toIntExact(entityManager.createQuery(cq).getSingleResult());
    }

    @Override
    public ModuleRoleMappingMaster getModuleRoleViaId_(Integer module_role_id ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ModuleRoleMappingMaster> cq = cb.createQuery(ModuleRoleMappingMaster.class);
        Root<ModuleRoleMappingMaster> book = cq.from(ModuleRoleMappingMaster.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate id_ = cb.equal(book.get("id"), module_role_id);

        cq.where(id_,isActive_);
        TypedQuery<ModuleRoleMappingMaster> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }


}
