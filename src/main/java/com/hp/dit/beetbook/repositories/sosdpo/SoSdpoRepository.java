package com.hp.dit.beetbook.repositories.sosdpo;

import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.entities.S0SdpoMaster;
import com.hp.dit.beetbook.repositories.districtRepository.DistrictRepositoryCustom;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SoSdpoRepository extends DataTablesRepository<S0SdpoMaster, Integer>, SoSdpoRepositoryCustom {

}
