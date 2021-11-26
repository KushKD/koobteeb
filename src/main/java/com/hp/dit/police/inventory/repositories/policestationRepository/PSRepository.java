package com.hp.dit.police.inventory.repositories.policestationRepository;

import com.hp.dit.police.inventory.entities.PoliceStationMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PSRepository
        extends PagingAndSortingRepository<PoliceStationMaster, Integer>,
                CrudRepository<PoliceStationMaster, Integer> ,
                PSRepositoryCustom {

}
