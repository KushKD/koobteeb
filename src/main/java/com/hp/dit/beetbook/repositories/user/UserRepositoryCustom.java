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
    UsePoJo apiLogin(Integer StateId, Integer DistrictId, Integer sosdpoid, Integer psid, Integer beatid, String username, String password );
    UsePoJo apiLoginSho(Integer StateId, Integer DistrictId, Integer sosdpoid, Integer beatid, String username, String password );

    RevenueUserDetails getRevenueUserPhoneNumber(Integer userId);

}
