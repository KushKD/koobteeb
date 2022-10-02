package com.hp.dit.election_ems.CustomLogin;

import com.hp.dit.election_ems.entities.UserEntity;

public interface CustomUserService {


    void save(UserEntity user);
    UserEntity findByUsername(String userName);
    UserEntity findByMobileNumber(String mobileNumber);
}
