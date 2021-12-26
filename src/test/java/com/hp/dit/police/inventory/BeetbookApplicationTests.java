package com.hp.dit.police.inventory;

import com.google.gson.JsonObject;
import com.hp.dit.police.inventory.apicontroller.API;
import com.hp.dit.police.inventory.repositories.stateRepository.StateRepository;
import com.hp.dit.police.inventory.security.EncryptDecrypt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
class BeetbookApplicationTests {

    @Autowired
    StateRepository stateRepository;

    @Autowired
    API apiController;


        @Test
    @Transactional
    @Rollback(value = false)
    void getStates() throws Exception {
            EncryptDecrypt ED  = new EncryptDecrypt();
        String states = apiController.getStates();
            System.out.println("===States===");
            System.out.println(states);
            System.out.println(ED.decrypt(states));

    }


    @Test
    @Transactional
    @Rollback(value = false)
    void getDistricts() throws Exception  {
        EncryptDecrypt ED = new EncryptDecrypt();
        System.out.println(ED.encrypt("9"));
        String districts = apiController.getDistricts(ED.encrypt("9"));
        System.out.println("===Districts===");
        System.out.println(districts);
        System.out.println(ED.decrypt(districts));

    }


    @Test
    @Transactional
    @Rollback(value = false)
    void getPoliceStations() throws Exception  {

        EncryptDecrypt ED = new EncryptDecrypt();
        JsonObject jsonObjecttwo = new JsonObject();
        jsonObjecttwo.addProperty("stateId", "9");
        jsonObjecttwo.addProperty("districtId", "204");
        jsonObjecttwo.addProperty("sdpoId", "1");
         System.out.println(ED.encrypt(jsonObjecttwo.toString()));
        String ps = apiController.getPoliceStations(ED.encrypt(jsonObjecttwo.toString()));
        System.out.println("===Police Station===");
        System.out.println(ps);
        System.out.println(ED.decrypt(ps));

    }



}
