package com.hp.dit.election_ems.repositories.bank;

import com.hp.dit.election_ems.entities.BankMaster;
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
public class BankRepositoryCustomImpl implements BankRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BankMaster> getAllSOSdpo() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BankMaster> cq = cb.createQuery(BankMaster.class);
        Root<BankMaster> book = cq.from(BankMaster.class);
        TypedQuery<BankMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public BankMaster getAllSOSdpoViaId(Integer id_) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BankMaster> cq = cb.createQuery(BankMaster.class);
        Root<BankMaster> book = cq.from(BankMaster.class);
        Predicate id = cb.equal(book.get("bankId"), id_);
        cq.where(id);
        TypedQuery<BankMaster> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }

    @Override
    public Integer sdpoCount(String so_sdpo) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<BankMaster> book = cq.from(BankMaster.class);
            Predicate isActive_ = cb.equal(book.get("active"), true);
            Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
            Predicate sosdpoName = cb.equal(book.get("bankName"), so_sdpo);
            cq.where(isActive_,isDeleted_,sosdpoName);
            cq.select(cb.count(book)).where(isActive_,isDeleted_,sosdpoName);
            return Math.toIntExact(entityManager.createQuery(cq).getSingleResult());


    }

    @Override
    public List<BankMaster> getAllActiveSOSdo() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BankMaster> cq = cb.createQuery(BankMaster.class);
        Root<BankMaster> book = cq.from(BankMaster.class);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate deleted = cb.equal(book.get("deleted"), false);
        cq.where(active,deleted);
        TypedQuery<BankMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }
}
