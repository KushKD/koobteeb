package com.hp.dit.election_ems.repositories.beats;

import com.hp.dit.election_ems.entities.BeatMaster;
import com.hp.dit.election_ems.modals.beats.BeatsNameId;
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
public class BeatRepositoryCustomImpl implements BeatRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<BeatMaster> findBeatByPSId(Integer ps_id) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BeatMaster> cq = cb.createQuery(BeatMaster.class);
        Root<BeatMaster> book = cq.from(BeatMaster.class);
        Predicate stateId_ = cb.equal(book.get("psId"), ps_id);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate deleted = cb.equal(book.get("deleted"), false);
        cq.where(stateId_,active,deleted);
        TypedQuery<BeatMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<BeatsNameId> findBeatNameIdByPSId(Integer polise_station) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BeatsNameId> cq = cb.createQuery(BeatsNameId.class);
        Root<BeatMaster> book = cq.from(BeatMaster.class);
        Predicate stateId_ = cb.equal(book.get("psId"), polise_station);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate deleted = cb.equal(book.get("deleted"), false);
        cq.where(stateId_,active,deleted);
        cq.multiselect(
                book.get("beatId"),
                book.get("beatName")
               ).distinct(true);
        TypedQuery<BeatsNameId> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<BeatMaster> getAllBeats() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BeatMaster> cq = cb.createQuery(BeatMaster.class);
        Root<BeatMaster> book = cq.from(BeatMaster.class);
        TypedQuery<BeatMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<BeatMaster> getAllActiveBeats() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BeatMaster> cq = cb.createQuery(BeatMaster.class);
        Root<BeatMaster> book = cq.from(BeatMaster.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        cq.where(isActive_,isDeleted_);
        TypedQuery<BeatMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public BeatMaster getBeatViaId(Integer beatId) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BeatMaster> cq = cb.createQuery(BeatMaster.class);
        Root<BeatMaster> book = cq.from(BeatMaster.class);
        Predicate districtId_ = cb.equal(book.get("beatId"), beatId);
        cq.where(districtId_);
        TypedQuery<BeatMaster> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }

    @Override
    public Integer beatCount(String beatName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<BeatMaster> book = cq.from(BeatMaster.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        Predicate districtname_ = cb.equal(book.get("beatName"), beatName);
        cq.where(isActive_,isDeleted_,districtname_);
        cq.select(cb.count(book)).where(isActive_,isDeleted_,districtname_);
        return Math.toIntExact(entityManager.createQuery(cq).getSingleResult());
    }


}
