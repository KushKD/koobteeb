package com.hp.dit.election_ems.repositories.submodules;

import com.hp.dit.election_ems.entities.SubModuleMaster;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubModuleRepository extends DataTablesRepository<SubModuleMaster, Integer>, SubModuleRepositoryCustom {

}
