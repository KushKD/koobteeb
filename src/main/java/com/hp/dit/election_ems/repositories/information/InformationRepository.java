package com.hp.dit.election_ems.repositories.information;

import com.hp.dit.election_ems.entities.InformationEntity;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationRepository extends DataTablesRepository<InformationEntity,Integer>,InformationRepositoryCustom {

    @Query(value="SELECT id, inf.submodule_id,sm.submodule_name,name,to_char(inf.createddate,'dd-MM-YYYY hh:MM:ss') FROM information inf INNER JOIN mst_submodule sm ON sm.submodule_id = inf.submodule_id WHERE state_id =:stateId AND district_id =:districtId  AND ps_id=:psId AND beat_id =:beatId AND inf.submodule_id =:submoduleId AND to_char(inf.createddate,'dd-MM-YYYY') >= :fromDate AND to_char(inf.createddate,'dd-MM-YYYY') <= :toDate ORDER BY inf.createddate DESC",nativeQuery = true)
    List<Object[]> getmarkersWeb(@Param("stateId") Integer stateId,
                                 @Param("districtId") Integer districtId,
                                 @Param("submoduleId") Integer submoduleId,
                                 @Param("beatId") Integer beatId,
                                 @Param("psId") Integer psId,
                                 @Param("fromDate") String fromDate,
                                 @Param("toDate") String toDate);

}
