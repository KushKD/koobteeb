package com.hp.dit.police.inventory.repositories.information;

import com.hp.dit.police.inventory.entities.InformationEntity;
import com.hp.dit.police.inventory.modals.information.InformationMarkers;
import com.hp.dit.police.inventory.modals.information.InformationViaId;
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
public class InformationRepositoryCustomImpl implements InformationRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<InformationMarkers> getmarkers(Integer moduleId, Integer submoduleId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InformationMarkers> cq = cb.createQuery(InformationMarkers.class);
        Root<InformationEntity> book = cq.from(InformationEntity.class);
        Predicate moduleId_ = cb.equal(book.get("moduleId"), moduleId);
        Predicate subModuleID_ = cb.equal(book.get("submoduleId").<Integer>get("submoduleId"), submoduleId);
        Predicate active = cb.equal(book.get("active"), true);
        cq.where(moduleId_,active,subModuleID_).orderBy(cb.desc(book.get("createdDate")));
        cq.multiselect(
                book.get("id"),
                book.get("latitude"),
                book.get("longitude"),
                book.get("name"),
                book.get("photo"),
                book.get("submoduleId").<String>get("submoduleName"),
                book.get("submoduleId").<Integer>get("submoduleId"),
                book.get("moduleId"),
                book.get("submoduleId").<String>get("subiconName"),
                book.get("createdDate")

        ).distinct(true);
        TypedQuery<InformationMarkers> query =  entityManager.createQuery(cq).setMaxResults(40);
        return query.getResultList();
    }

    @Override
    public List<InformationMarkers> getmarkersLocationWise(Integer moduleId, Integer submoduleId, Double latitude, Double longitude) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InformationMarkers> cq = cb.createQuery(InformationMarkers.class);
        Root<InformationEntity> book = cq.from(InformationEntity.class);
        Predicate moduleId_ = cb.equal(book.get("moduleId"), moduleId);
        Predicate subModuleID_ = cb.equal(book.get("submoduleId").<Integer>get("submoduleId"), submoduleId);
        Predicate latitude_ = cb.equal(book.get("latitude"), latitude);
        Predicate longitude_ = cb.equal(book.get("longitude"), longitude);
        Predicate active = cb.equal(book.get("active"), true);
        cq.where(moduleId_,active,subModuleID_);
        cq.multiselect(
                book.get("id"),
                book.get("latitude"),
                book.get("longitude"),
                book.get("name"),
                book.get("photo"),
                book.get("submoduleId").<String>get("submoduleName"),
                book.get("submoduleId").<Integer>get("submoduleId"),
                book.get("moduleId"),
                book.get("submoduleId").<String>get("subiconName"),
                book.get("createdDate")
        ).distinct(true);
        TypedQuery<InformationMarkers> query =  entityManager.createQuery(cq).setMaxResults(40);
        return query.getResultList();
    }

    @Override
    public List<InformationMarkers> getmarkersViaLocation(Integer moduleId, Integer submoduleId, Integer beatId, Integer psId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InformationMarkers> cq = cb.createQuery(InformationMarkers.class);
        Root<InformationEntity> book = cq.from(InformationEntity.class);
        Predicate moduleId_ = cb.equal(book.get("moduleId"), moduleId);
        Predicate subModuleID_ = cb.equal(book.get("submoduleId").<Integer>get("submoduleId"), submoduleId);
        Predicate latitude_ = cb.equal(book.get("beatId"), beatId);
        Predicate psid_ = cb.equal(book.get("psId"), psId);
        Predicate active = cb.equal(book.get("active"), true);
        cq.where(moduleId_,active,subModuleID_,psid_).orderBy(cb.desc(book.get("createdDate")));
        cq.multiselect(
                book.get("id"),
                book.get("latitude"),
                book.get("longitude"),
                book.get("name"),
                book.get("photo"),
                book.get("submoduleId").<String>get("submoduleName"),
                book.get("submoduleId").<Integer>get("submoduleId"),
                book.get("moduleId"),
                book.get("submoduleId").<String>get("subiconName"),
                book.get("createdDate")
        ).distinct(true);
        TypedQuery<InformationMarkers> query =  entityManager.createQuery(cq).setMaxResults(40);
        return query.getResultList();
    }

    @Override
    public List<InformationMarkers> getmarkersViaLocationPsBeat(Integer moduleId, Integer submoduleId, Integer beatId, Integer psId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<InformationMarkers> cq = cb.createQuery(InformationMarkers.class);
            Root<InformationEntity> book = cq.from(InformationEntity.class);
            Predicate moduleId_ = cb.equal(book.get("moduleId"), moduleId);
            Predicate subModuleID_ = cb.equal(book.get("submoduleId").<Integer>get("submoduleId"), submoduleId);
            Predicate beat_id = cb.equal(book.get("beatId"), beatId);
            Predicate psid_ = cb.equal(book.get("psId"), psId);
            Predicate active = cb.equal(book.get("active"), true);
            cq.where(moduleId_,active,subModuleID_,psid_,beat_id).orderBy(cb.desc(book.get("createdDate")));
            cq.multiselect(
                    book.get("id"),
                    book.get("latitude"),
                    book.get("longitude"),
                    book.get("name"),
                    book.get("photo"),
                    book.get("submoduleId").<String>get("submoduleName"),
                    book.get("submoduleId").<Integer>get("submoduleId"),
                    book.get("moduleId"),
                    book.get("submoduleId").<String>get("subiconName")
            ).distinct(true);
            TypedQuery<InformationMarkers> query =  entityManager.createQuery(cq).setMaxResults(40);
            return query.getResultList();

    }


    @Override
    public InformationViaId getInformationViaId(Integer id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InformationViaId> cq = cb.createQuery(InformationViaId.class);
        Root<InformationEntity> book = cq.from(InformationEntity.class);
        Predicate districtId_ = cb.equal(book.get("id"), id);
        Predicate active = cb.equal(book.get("active"), true);
        cq.where(districtId_,active);
        cq.multiselect(
                book.get("name"),
                book.get("ownerName"),
                book.get("ownerNameTwo"),
                book.get("photo"),
                book.get("photoId"),
                book.get("contactNoOne"),
                book.get("contactNoTwo"),
                book.get("helplineNumber"),
                book.get("landlineNumber"),
                book.get("optionId"),
                book.get("cctv"),
                book.get("numberIdols"),
                book.get("numberSecurityPersons"),
                book.get("emailId"),
                book.get("facbookId"),
                book.get("presentAddress"),
                book.get("permanentAddress"),
                book.get("firNo"),
                book.get("firDetails"),
                book.get("licenceeNo"),
                book.get("licenceeName"),
                book.get("details"),
                book.get("other"),
                book.get("checkingDateSho"),
                book.get("totalPopulation"),
                book.get("periodFair"),
                book.get("authority"),
                book.get("durationParole"),
                book.get("idProof"),
                book.get("section"),
                book.get("SpecialReportedCases"),
                book.get("extraOne"),
                book.get("extraTwo"),
                book.get("latitude"),
                book.get("longitude"));
        TypedQuery<InformationViaId> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }

    @Override
    public InformationEntity getCompleteInformationViaId(Integer id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InformationEntity> cq = cb.createQuery(InformationEntity.class);
        Root<InformationEntity> book = cq.from(InformationEntity.class);
        Predicate districtId_ = cb.equal(book.get("id"), id);
        Predicate active = cb.equal(book.get("active"), true);
        cq.where(districtId_,active);
        TypedQuery<InformationEntity> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }

    @Override
    public InformationViaId getInformationViaId(Integer id, Integer moduleId, Integer SubmoduleId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InformationViaId> cq = cb.createQuery(InformationViaId.class);
        Root<InformationEntity> book = cq.from(InformationEntity.class);
        Predicate districtId_ = cb.equal(book.get("id"), id);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate subModuleID_ = cb.equal(book.get("submoduleId").<Integer>get("submoduleId"), SubmoduleId);
        cq.where(districtId_,active);
        cq.multiselect(
                book.get("name"),
                book.get("ownerName"),
                book.get("ownerNameTwo"),
                book.get("photo"),
                book.get("photoId"),
                book.get("contactNoOne"),
                book.get("contactNoTwo"),
                book.get("helplineNumber"),
                book.get("landlineNumber"),
                book.get("optionId"),
                book.get("cctv"),
                book.get("numberIdols"),
                book.get("numberSecurityPersons"),
                book.get("emailId"),
                book.get("facbookId"),
                book.get("presentAddress"),
                book.get("permanentAddress"),
                book.get("firNo"),
                book.get("firDetails"),
                book.get("licenceeNo"),
                book.get("licenceeName"),
                book.get("details"),
                book.get("other"),
                book.get("checkingDateSho"),
                book.get("totalPopulation"),
                book.get("periodFair"),
                book.get("authority"),
                book.get("durationParole"),
                book.get("idProof"),
                book.get("section"),
                book.get("SpecialReportedCases"),
                book.get("extraOne"),
                book.get("extraTwo"),
                book.get("latitude"),
                book.get("longitude"));
        TypedQuery<InformationViaId> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }
}
