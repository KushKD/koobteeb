package com.hp.dit.police.inventory.repositories.user;

import com.hp.dit.police.inventory.entities.UserEntity;
import com.hp.dit.police.inventory.modals.LoggedInUserSession;
import com.hp.dit.police.inventory.modals.UsePoJo;
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
public class UserRepositoryCustomImpl implements UserRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<LoggedInUserSession> getUserGeoData(String username_) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LoggedInUserSession> cq = cb.createQuery(LoggedInUserSession.class);
        Root<UserEntity> book = cq.from(UserEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate username = cb.equal(book.get("username"), username_);
        cq.where(isActive_,username);
        cq.multiselect(
                book.get("stateId"),
                book.get("districtId"),
               // book.get("barrierId"),
                book.get("userId"),
                book.get("username"),
                book.get("firstName"),
                book.get("lastName"),
                book.get("mobileNumber")).distinct(true);
        TypedQuery<LoggedInUserSession> query =  entityManager.createQuery(cq);
        return query.getResultList();

    }

    @Override
    public UserEntity findByMobileNumber(Long mobile) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> book = cq.from(UserEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate mobile_ = cb.equal(book.get("mobileNumber"), mobile);
        cq.where(isActive_,mobile_);
        TypedQuery<UserEntity> query =  entityManager.createQuery(cq);
        return query.getSingleResult();
    }

    @Override
    public UserEntity getUserDetails(Long mobile) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> book = cq.from(UserEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate mobile_ = cb.equal(book.get("mobileNumber"), mobile);
        cq.where(isActive_,mobile_);
        TypedQuery<UserEntity> query =  entityManager.createQuery(cq);
        return query.getSingleResult();
    }

    @Override
    public UsePoJo apiLogin(Integer StateId, Integer DistrictId, Integer sosdpoid, Integer psid, Integer beatId, String username, String password) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsePoJo> cq = cb.createQuery(UsePoJo.class);
        Root<UserEntity> book = cq.from(UserEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate stateId_ = cb.equal(book.get("stateId"), StateId);
        Predicate districtId_ = cb.equal(book.get("districtId"), DistrictId);
        Predicate sosdpoid_ = cb.equal(book.get("sosdpoId"), sosdpoid);
        Predicate psid_ = cb.equal(book.get("psId"), psid);
        Predicate beatid_ = cb.equal(book.get("beatId"), beatId);
        Predicate username_ = cb.equal(book.get("username"), username);
        cq.where(isActive_,sosdpoid_,stateId_,districtId_,psid_,username_,beatid_);
        cq.multiselect(book.get("userId"),
                book.get("username"),
                book.get("mobileNumber"),
                book.get("firstName"),
                book.get("lastName"),
                book.get("password"),
                book.get("stateId"),
                book.get("districtId"),
                book.get("sosdpoId"),
                book.get("psId"),
                book.get("beatId")).distinct(true);
        TypedQuery<UsePoJo> query =  entityManager.createQuery(cq);
        return query.getSingleResult();
    }

    @Override
    public UsePoJo apiLoginSho(Integer StateId, Integer DistrictId, Integer sosdpoid, Integer beatid, String username, String password) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsePoJo> cq = cb.createQuery(UsePoJo.class);
        Root<UserEntity> book = cq.from(UserEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate stateId_ = cb.equal(book.get("stateId"), StateId);
        Predicate districtId_ = cb.equal(book.get("districtId"), DistrictId);
        Predicate sosdpoid_ = cb.equal(book.get("sosdpoId"), sosdpoid);
        Predicate psid_ = cb.equal(book.get("psId"), beatid);
        Predicate username_ = cb.equal(book.get("username"), username);
        cq.where(isActive_,sosdpoid_,stateId_,districtId_,psid_,username_);
        cq.multiselect(book.get("userId"),
                book.get("username"),
                book.get("mobileNumber"),
                book.get("firstName"),
                book.get("lastName"),
                book.get("password"),
                book.get("stateId"),
                book.get("districtId"),
                book.get("sosdpoId"),
                book.get("psId"),
                book.get("beatId")).distinct(true);
        TypedQuery<UsePoJo> query =  entityManager.createQuery(cq);
        return query.getSingleResult();
    }




}
