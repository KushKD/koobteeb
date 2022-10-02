package com.hp.dit.election_ems.repositories.sosdpo;

import com.hp.dit.election_ems.entities.S0SdpoMaster;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SoSdpoRepository extends DataTablesRepository<S0SdpoMaster, Integer>, SoSdpoRepositoryCustom {

}
