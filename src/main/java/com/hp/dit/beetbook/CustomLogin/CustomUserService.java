package com.hp.dit.beetbook.CustomLogin;

import com.hp.dit.beetbook.entities.UserEntity;

public interface CustomUserService {


    void save(UserEntity user);
    UserEntity findByUsername(String userName);
    UserEntity findByMobileNumber(String mobileNumber);
}
