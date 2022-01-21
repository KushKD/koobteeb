package com.hp.dit.beetbook.repositories.submodules;

import com.hp.dit.beetbook.entities.SubModuleMaster;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubModuleRepository extends DataTablesRepository<SubModuleMaster, Integer>, SubModuleRepositoryCustom {

}
