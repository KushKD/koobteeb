package com.hp.dit.police.inventory.repositories.submoduleoptions;

import com.hp.dit.police.inventory.entities.OptionsMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubModuleOptionRepository extends CrudRepository<OptionsMaster, Integer>, SubModuleOptionRepositoryCustom {

}
