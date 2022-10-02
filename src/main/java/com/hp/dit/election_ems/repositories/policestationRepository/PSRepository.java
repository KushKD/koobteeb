package com.hp.dit.election_ems.repositories.policestationRepository;

import com.hp.dit.election_ems.entities.PoliceStationMaster;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PSRepository extends
        DataTablesRepository<PoliceStationMaster, Integer>,
                PSRepositoryCustom {

}
