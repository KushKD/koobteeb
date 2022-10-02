package com.hp.dit.election_ems.repositories.roles;

import com.hp.dit.election_ems.entities.ModuleMaster;
import com.hp.dit.election_ems.entities.RolesEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class RolesRepositoryDatatablesCustomImpl implements RolesRepositoryDatatablesCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public RolesEntity getRoleViaId(Integer roleId) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RolesEntity> cq = cb.createQuery(RolesEntity.class);
        Root<RolesEntity> book = cq.from(RolesEntity.class);
        Predicate role_id = cb.equal(book.get("roleId"), roleId);
        cq.where(role_id);
        TypedQuery<RolesEntity> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }
}
