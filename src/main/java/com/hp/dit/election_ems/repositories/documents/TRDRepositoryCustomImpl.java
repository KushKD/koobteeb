package com.hp.dit.election_ems.repositories.documents;

import com.hp.dit.election_ems.entities.SubModuleMaster;
import com.hp.dit.election_ems.entities.TrdocumentsEntity;
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
public class TRDRepositoryCustomImpl implements TRDRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TrdocumentsEntity> getDocumentsViaId(Integer id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TrdocumentsEntity> cq = cb.createQuery(TrdocumentsEntity.class);
        Root<TrdocumentsEntity> book = cq.from(TrdocumentsEntity.class);
        Predicate trd_ = cb.equal(book.get("transferRequestID"), id);
        Predicate active = cb.equal(book.get("active"), true);
        cq.where(trd_,active);
        TypedQuery<TrdocumentsEntity> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }
}
