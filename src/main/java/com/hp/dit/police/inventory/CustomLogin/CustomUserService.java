package com.hp.dit.police.inventory.CustomLogin;

import com.hp.dit.police.inventory.entities.UserEntity;

public interface CustomUserService {


    void save(UserEntity user);
    UserEntity findByUsername(String userName);
    UserEntity findByMobileNumber(String mobileNumber);
}
