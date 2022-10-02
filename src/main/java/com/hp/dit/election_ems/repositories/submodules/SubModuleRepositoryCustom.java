package com.hp.dit.election_ems.repositories.submodules;

import com.hp.dit.election_ems.entities.SubModuleMaster;
import com.hp.dit.election_ems.modals.submoduleModal.SubModuleRoleList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubModuleRepositoryCustom {


    List<SubModuleMaster> findSubModuleByModueId(Integer moduleId) throws Exception;
    List<SubModuleMaster> getAllSubModules() throws Exception;
    List<SubModuleMaster> getAllActiveSubModules() throws Exception;
    SubModuleMaster getSubModuleViaId(Integer submoduleId) throws Exception;
    Integer submoduleCount(String submodulename);
    List<SubModuleRoleList> findSubModulesByModueId(Integer moduleId) throws Exception;


}
