package com.hp.dit.beetbook.repositories.policestationRepository;

import com.hp.dit.beetbook.entities.PoliceStationMaster;
import com.hp.dit.beetbook.entities.S0SdpoMaster;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PSRepository extends
        DataTablesRepository<PoliceStationMaster, Integer>,
                PSRepositoryCustom {

}
