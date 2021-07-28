package com.hp.dit.beetbook.repositories.pin;

import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.entities.PinMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PinRepository extends CrudRepository<PinMaster, Integer>, PinRepositoryCustom {

}
