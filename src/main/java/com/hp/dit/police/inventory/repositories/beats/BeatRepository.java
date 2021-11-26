package com.hp.dit.police.inventory.repositories.beats;

import com.hp.dit.police.inventory.entities.BeatMaster;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BeatRepository extends DataTablesRepository<BeatMaster, Integer>, BeatRepositoryCustom {

}
