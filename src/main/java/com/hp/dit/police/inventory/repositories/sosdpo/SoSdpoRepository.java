package com.hp.dit.police.inventory.repositories.sosdpo;

import com.hp.dit.police.inventory.entities.S0SdpoMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SoSdpoRepository extends CrudRepository<S0SdpoMaster, Integer>, SoSdpoRepositoryCustom {

}
