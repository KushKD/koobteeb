package com.hp.dit.beetbook.repositories.sosdpo;

import com.hp.dit.beetbook.entities.S0SdpoMaster;
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
public class SoSdpoRepositoryCustomImpl implements SoSdpoRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<S0SdpoMaster> getAllSOSdpo() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<S0SdpoMaster> cq = cb.createQuery(S0SdpoMaster.class);
        Root<S0SdpoMaster> book = cq.from(S0SdpoMaster.class);
        TypedQuery<S0SdpoMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public S0SdpoMaster getAllSOSdpoViaId(Integer id_) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<S0SdpoMaster> cq = cb.createQuery(S0SdpoMaster.class);
        Root<S0SdpoMaster> book = cq.from(S0SdpoMaster.class);
        Predicate id = cb.equal(book.get("sosdpoId"), id_);
        cq.where(id);
        TypedQuery<S0SdpoMaster> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }

    @Override
    public Integer sdpoCount(String so_sdpo) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<S0SdpoMaster> book = cq.from(S0SdpoMaster.class);
            Predicate isActive_ = cb.equal(book.get("active"), true);
            Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
            Predicate sosdpoName = cb.equal(book.get("sosdpoName"), so_sdpo);
            cq.where(isActive_,isDeleted_,sosdpoName);
            cq.select(cb.count(book)).where(isActive_,isDeleted_,sosdpoName);
            return Math.toIntExact(entityManager.createQuery(cq).getSingleResult());


    }

    @Override
    public List<S0SdpoMaster> getAllActiveSOSdo() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<S0SdpoMaster> cq = cb.createQuery(S0SdpoMaster.class);
        Root<S0SdpoMaster> book = cq.from(S0SdpoMaster.class);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate deleted = cb.equal(book.get("deleted"), false);
        cq.where(active,deleted);
        TypedQuery<S0SdpoMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }
}
