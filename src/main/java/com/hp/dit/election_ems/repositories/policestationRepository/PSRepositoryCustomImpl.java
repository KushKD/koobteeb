package com.hp.dit.election_ems.repositories.policestationRepository;

import com.hp.dit.election_ems.entities.PoliceStationMaster;
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
public class PSRepositoryCustomImpl implements PSRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<PoliceStationMaster> getAllPoliceStation() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PoliceStationMaster> cq = cb.createQuery(PoliceStationMaster.class);
        Root<PoliceStationMaster> book = cq.from(PoliceStationMaster.class);
        TypedQuery<PoliceStationMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public PoliceStationMaster getPoliceStationViaId(Integer psid) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PoliceStationMaster> cq = cb.createQuery(PoliceStationMaster.class);
        Root<PoliceStationMaster> book = cq.from(PoliceStationMaster.class);
        Predicate id = cb.equal(book.get("psId"), psid);
       // Predicate active = cb.equal(book.get("active"), true);
       // Predicate deleted = cb.equal(book.get("deleted"), false);
        cq.where(id);
        TypedQuery<PoliceStationMaster> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }

    @Override
    public Integer psCount(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<PoliceStationMaster> book = cq.from(PoliceStationMaster.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate isDeleted_ = cb.equal(book.get("deleted"), false);
        Predicate psname = cb.equal(book.get("psName"), name);
        cq.where(isActive_,isDeleted_,psname);
        cq.select(cb.count(book)).where(isActive_,isDeleted_,psname);
        return Math.toIntExact(entityManager.createQuery(cq).getSingleResult());
    }

    @Override
    public List<PoliceStationMaster> getAllActivePoliceStation() throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PoliceStationMaster> cq = cb.createQuery(PoliceStationMaster.class);
        Root<PoliceStationMaster> book = cq.from(PoliceStationMaster.class);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate deleted = cb.equal(book.get("deleted"), false);
        cq.where(active,deleted);
        TypedQuery<PoliceStationMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<PoliceStationMaster> getAllActivePoliceStationViaDistrictSoSdpo(Integer DistrictId, Integer SoSdpoId) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PoliceStationMaster> cq = cb.createQuery(PoliceStationMaster.class);
        Root<PoliceStationMaster> book = cq.from(PoliceStationMaster.class);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate deleted = cb.equal(book.get("deleted"), false);
        Predicate district_id = cb.equal(book.get("districtId"), DistrictId);
        Predicate sosdpo_id = cb.equal(book.get("sosdpoId"), SoSdpoId);
        cq.where(active,deleted,district_id,sosdpo_id);
        TypedQuery<PoliceStationMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<PoliceStationMaster> getAllActivePoliceStationViaSoSdpo(Integer SoSdpoId) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PoliceStationMaster> cq = cb.createQuery(PoliceStationMaster.class);
        Root<PoliceStationMaster> book = cq.from(PoliceStationMaster.class);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate deleted = cb.equal(book.get("deleted"), false);
        Predicate sosdpo_id = cb.equal(book.get("sosdpoId"), SoSdpoId);
        cq.where(active,deleted,sosdpo_id);
        TypedQuery<PoliceStationMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<PoliceStationMaster> getAllActivePoliceStationViaDistrictSoSdpo(Integer stateId, Integer DistrictId, Integer SoSdpoId) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PoliceStationMaster> cq = cb.createQuery(PoliceStationMaster.class);
        Root<PoliceStationMaster> book = cq.from(PoliceStationMaster.class);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate deleted = cb.equal(book.get("deleted"), false);
        Predicate state_id = cb.equal(book.get("stateID"), stateId);
        Predicate district_id = cb.equal(book.get("districtId"), DistrictId);
        Predicate sosdpo_id = cb.equal(book.get("sosdpoId"), SoSdpoId);
        cq.where(active,deleted,state_id,district_id,sosdpo_id);
        TypedQuery<PoliceStationMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }
}
