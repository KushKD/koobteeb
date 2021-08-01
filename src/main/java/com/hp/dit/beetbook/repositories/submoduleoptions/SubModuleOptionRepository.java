package com.hp.dit.beetbook.repositories.submoduleoptions;

import com.hp.dit.beetbook.entities.OptionsMaster;
import com.hp.dit.beetbook.entities.SubModuleMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubModuleOptionRepository extends CrudRepository<OptionsMaster, Integer>, SubModuleOptionRepositoryCustom {

}
