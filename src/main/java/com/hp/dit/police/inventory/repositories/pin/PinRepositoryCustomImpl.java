package com.hp.dit.police.inventory.repositories.pin;

import com.hp.dit.police.inventory.entities.PinMaster;
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
public class PinRepositoryCustomImpl implements PinRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<PinMaster> findAllActivePins() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PinMaster> cq = cb.createQuery(PinMaster.class);
        Root<PinMaster> book = cq.from(PinMaster.class);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate deleted = cb.equal(book.get("deleted"), false);
        cq.where(active,deleted);
        TypedQuery<PinMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public PinMaster getPinViaId(Integer pin_id) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PinMaster> cq = cb.createQuery(PinMaster.class);
        Root<PinMaster> book = cq.from(PinMaster.class);
        Predicate id = cb.equal(book.get("pinId"), pin_id);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate deleted = cb.equal(book.get("deleted"), false);
        cq.where(id,active,deleted);
        TypedQuery<PinMaster> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }

    @Override
    public PinMaster findActivePins() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PinMaster> cq = cb.createQuery(PinMaster.class);
        Root<PinMaster> book = cq.from(PinMaster.class);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate deleted = cb.equal(book.get("deleted"), false);
        cq.where(active,deleted);
        TypedQuery<PinMaster> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }
}
