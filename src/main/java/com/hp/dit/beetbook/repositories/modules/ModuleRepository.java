package com.hp.dit.beetbook.repositories.modules;

import com.hp.dit.beetbook.entities.BeatMaster;
import com.hp.dit.beetbook.entities.ModuleMaster;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ModuleRepository extends DataTablesRepository<ModuleMaster, Integer>, ModuleRepositoryCustom {


    @Query(value = "SELECT module.module_id, module.module_name,module.module_icon, module.active FROM mst_module as module INNER JOIN module_role_mapping as mrm ON mrm.module_id = module.module_id WHERE role_id =:roleId ORDER BY module_id ASC", nativeQuery = true)
    List<Object[]> getModulesViaRoleId(@Param("roleId")Integer userId);

}
