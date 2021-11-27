package com.hp.dit.police.inventory.repositories.policelines;

import com.hp.dit.police.inventory.entities.PoliceLines;
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
public class PoliceLinesRepositoryCustomImpl implements PoliceLinesRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PoliceLines> getAllSOSdpo() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PoliceLines> cq = cb.createQuery(PoliceLines.class);
        Root<PoliceLines> book = cq.from(PoliceLines.class);
        TypedQuery<PoliceLines> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public PoliceLines getAllSOSdpoViaId(Integer id_) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PoliceLines> cq = cb.createQuery(PoliceLines.class);
        Root<PoliceLines> book = cq.from(PoliceLines.class);
        Predicate id = cb.equal(book.get("policelineId"), id_);
        cq.where(id);
        TypedQuery<PoliceLines> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }

    @Override
    public Integer sdpoCount(String so_sdpo) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<PoliceLines> book = cq.from(PoliceLines.class);
            Predicate isActive_ = cb.equal(book.get("isActive"), true);
            Predicate isDeleted_ = cb.equal(book.get("isDeleted"), false);
            Predicate sosdpoName = cb.equal(book.get("policelineName"), so_sdpo);
            cq.where(isActive_,isDeleted_,sosdpoName);
            cq.select(cb.count(book)).where(isActive_,isDeleted_,sosdpoName);
            return Math.toIntExact(entityManager.createQuery(cq).getSingleResult());


    }

    @Override
    public List<PoliceLines> getAllActiveSOSdo() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PoliceLines> cq = cb.createQuery(PoliceLines.class);
        Root<PoliceLines> book = cq.from(PoliceLines.class);
        Predicate active = cb.equal(book.get("isActive"), true);
        Predicate deleted = cb.equal(book.get("isDeleted"), false);
        cq.where(active,deleted);
        TypedQuery<PoliceLines> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }
}
