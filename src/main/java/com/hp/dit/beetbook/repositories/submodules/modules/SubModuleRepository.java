package com.hp.dit.beetbook.repositories.submodules.modules;

import com.hp.dit.beetbook.entities.ModuleMaster;
import com.hp.dit.beetbook.entities.SubModuleMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubModuleRepository extends CrudRepository<SubModuleMaster, Integer>, SubModuleRepositoryCustom {

}
