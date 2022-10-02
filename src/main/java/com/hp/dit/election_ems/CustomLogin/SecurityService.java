package com.hp.dit.election_ems.CustomLogin;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String userName, String password);
}