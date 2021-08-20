package com.hp.dit.beetbook.repositories.beats;

import com.hp.dit.beetbook.entities.BeatMaster;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BeatRepository extends DataTablesRepository<BeatMaster, Integer>, BeatRepositoryCustom {

}
