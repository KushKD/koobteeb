package com.hp.dit.election_ems.repositories.beats;

import com.hp.dit.election_ems.entities.BeatMaster;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BeatRepository extends DataTablesRepository<BeatMaster, Integer>, BeatRepositoryCustom {

}
