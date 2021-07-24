package com.hp.dit.beetbook.repositories.user;

import com.hp.dit.beetbook.entities.UserEntity;
import com.hp.dit.beetbook.modals.LoggedInUserSession;
import com.hp.dit.beetbook.modals.RevenueUserDetails;
import com.hp.dit.beetbook.modals.UsePoJo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryCustom {

    List<LoggedInUserSession> getUserGeoData(String username_);
    UserEntity findByMobileNumber(Long mobile);
    UserEntity getUserDetails(Long mobile );
    UsePoJo apiLogin(Long mobile, Integer StateId, Integer DistrictId, Integer BarrierId, String username );
    RevenueUserDetails getRevenueUserPhoneNumber(Integer userId);

}
