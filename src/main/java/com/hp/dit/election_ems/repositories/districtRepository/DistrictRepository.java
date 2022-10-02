package com.hp.dit.election_ems.repositories.districtRepository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import com.hp.dit.election_ems.entities.DistrictMaster;


@Repository
public interface DistrictRepository extends DataTablesRepository <DistrictMaster, Integer>, DistrictRepositoryCustom {

}
