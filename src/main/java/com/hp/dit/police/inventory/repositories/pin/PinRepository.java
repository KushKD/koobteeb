package com.hp.dit.police.inventory.repositories.pin;

import com.hp.dit.police.inventory.entities.PinMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PinRepository extends CrudRepository<PinMaster, Integer>, PinRepositoryCustom {

}
