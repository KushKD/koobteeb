package com.hp.dit.police.inventory.repositories.submodules;

import com.hp.dit.police.inventory.entities.SubModuleMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubModuleRepository extends CrudRepository<SubModuleMaster, Integer>, SubModuleRepositoryCustom {

}
