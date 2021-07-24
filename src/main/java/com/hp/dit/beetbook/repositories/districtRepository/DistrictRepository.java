package com.hp.dit.beetbook.repositories.districtRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hp.dit.beetbook.entities.DistrictMaster;


@Repository
public interface DistrictRepository extends CrudRepository<DistrictMaster, Integer>, DistrictRepositoryCustom {

}
