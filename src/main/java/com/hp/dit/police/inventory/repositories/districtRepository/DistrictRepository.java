package com.hp.dit.police.inventory.repositories.districtRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hp.dit.police.inventory.entities.DistrictMaster;


@Repository
public interface DistrictRepository extends CrudRepository<DistrictMaster, Integer>, DistrictRepositoryCustom {

}
