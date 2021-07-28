package com.hp.dit.beetbook.repositories.rolemodule;

import com.hp.dit.beetbook.entities.ModuleMaster;
import com.hp.dit.beetbook.entities.ModuleRoleMappingMaster;
import com.hp.dit.beetbook.modals.moduleRole.ModuleRoleList;
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


}
