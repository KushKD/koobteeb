package com.hp.dit.police.inventory.CustomLogin;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String userName, String password);
}