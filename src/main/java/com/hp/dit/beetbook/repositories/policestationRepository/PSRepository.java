package com.hp.dit.beetbook.repositories.policestationRepository;

import com.hp.dit.beetbook.entities.PoliceStationMaster;
import com.hp.dit.beetbook.entities.S0SdpoMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PSRepository extends CrudRepository<PoliceStationMaster, Integer>, PSRepositoryCustom {

}
