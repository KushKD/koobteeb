package com.hp.dit.election_ems.repositories.userdatatable;

import com.hp.dit.election_ems.entities.UserDatatableEntity;
import com.hp.dit.election_ems.modals.usersviabeat.UsersViaBeat;
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
public class UserDatatableRepositoryCustomImpl implements UserDatatableRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<UsersViaBeat> getActiveUsersViaBeat(Integer beatId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsersViaBeat> cq = cb.createQuery(UsersViaBeat.class);
        Root<UserDatatableEntity> book = cq.from(UserDatatableEntity.class);
        Predicate beatId_ = cb.equal(book.get("beatId").<Integer>get("beatId"), beatId);
        Predicate active = cb.equal(book.get("active"), true);
        cq.where(beatId_,active);
        cq.multiselect(
                book.get("userId"),
                book.get("username")

        );
        TypedQuery<UsersViaBeat> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }
}
