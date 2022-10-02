package com.hp.dit.election_ems.repositories.bank;

import com.hp.dit.election_ems.entities.BankMaster;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheConfig(cacheNames = "sosdpo_")
public interface BankRepositoryCustom {


    //@Cacheable
    List<BankMaster> getAllSOSdpo() throws Exception;
    BankMaster getAllSOSdpoViaId(Integer districtId) throws Exception;
    Integer sdpoCount(String so_sdpo);
    List<BankMaster> getAllActiveSOSdo() throws Exception;
}
