package com.hp.dit.beetbook.repositories.beats;

import com.hp.dit.beetbook.entities.BeatMaster;
import com.hp.dit.beetbook.entities.DistrictMaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BeatRepository extends CrudRepository<BeatMaster, Integer>, BeatRepositoryCustom {

}
