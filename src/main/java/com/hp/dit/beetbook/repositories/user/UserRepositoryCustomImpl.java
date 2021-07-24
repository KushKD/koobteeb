package com.hp.dit.beetbook.repositories.user;

import com.hp.dit.beetbook.entities.UserEntity;
import com.hp.dit.beetbook.modals.LoggedInUserSession;
import com.hp.dit.beetbook.modals.RevenueUserDetails;
import com.hp.dit.beetbook.modals.UsePoJo;
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
    public UsePoJo apiLogin(Long mobile, Integer StateId, Integer DistrictId, Integer BarrierId, String username) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsePoJo> cq = cb.createQuery(UsePoJo.class);
        Root<UserEntity> book = cq.from(UserEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate mobile_ = cb.equal(book.get("mobileNumber"), mobile);
        Predicate stateId_ = cb.equal(book.get("stateId"), StateId);
        Predicate districtId_ = cb.equal(book.get("districtId"), DistrictId);
        Predicate barrierId_ = cb.equal(book.get("barrierId"), BarrierId);
        Predicate username_ = cb.equal(book.get("username"), username);
        cq.where(isActive_,mobile_,stateId_,districtId_,barrierId_,username_);
        cq.multiselect(book.get("userId"),
                book.get("username"),
                book.get("mobileNumber"),
                book.get("firstName"),
                book.get("lastName"),
                book.get("password")).distinct(true);
        TypedQuery<UsePoJo> query =  entityManager.createQuery(cq);
        return query.getSingleResult();
    }

    @Override
    public RevenueUserDetails getRevenueUserPhoneNumber(Integer userId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RevenueUserDetails> cq = cb.createQuery(RevenueUserDetails.class);
        Root<UserEntity> book = cq.from(UserEntity.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        Predicate userId_ = cb.equal(book.get("userId"), userId);
        cq.where(isActive_,userId_);
        cq.multiselect(
                book.get("firstName"),
                book.get("lastName"),
                book.get("mobileNumber")).distinct(true);
        TypedQuery<RevenueUserDetails> query =  entityManager.createQuery(cq);
        return query.getSingleResult();
    }


}
