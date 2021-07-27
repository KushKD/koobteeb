package com.hp.dit.beetbook.repositories.modules;

import com.hp.dit.beetbook.entities.BeatMaster;
import com.hp.dit.beetbook.entities.ModuleMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ModuleRepository extends CrudRepository<ModuleMaster, Integer>, ModuleRepositoryCustom {

}
