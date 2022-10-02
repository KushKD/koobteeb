package com.hp.dit.election_ems.repositories.submoduleoptions;

import com.hp.dit.election_ems.entities.OptionsMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubModuleOptionRepository extends CrudRepository<OptionsMaster, Integer>, SubModuleOptionRepositoryCustom {

}
