package com.hp.dit.election_ems.repositories.user;

import com.hp.dit.election_ems.entities.UserEntity;
import com.hp.dit.election_ems.modals.InformationAddedUser;
import com.hp.dit.election_ems.modals.LoggedInUserSession;
import com.hp.dit.election_ems.modals.RevenueUserDetails;
import com.hp.dit.election_ems.modals.UsePoJo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryCustom {

    List<LoggedInUserSession> getUserGeoData(String username_);
    UserEntity findByMobileNumber(Long mobile);
    UserEntity getUserDetails(Long mobile );
    UsePoJo apiLogin(Integer StateId, Integer DistrictId, Integer sosdpoid, Integer psid, Integer beatid, String username, String password );
    UsePoJo apiLoginSho(Integer StateId, Integer DistrictId, String username, String password );

    RevenueUserDetails getRevenueUserPhoneNumber(Integer userId);

    InformationAddedUser getUserByUserId(Integer userId);

}
