package com.hp.dit.election_ems.repositories.pin;

import com.hp.dit.election_ems.entities.PinMaster;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PinRepository extends DataTablesRepository<PinMaster, Integer>, PinRepositoryCustom {

}
