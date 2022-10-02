package com.hp.dit.election_ems.repositories.information;

import com.hp.dit.election_ems.entities.AllInformationEntity;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllInformationRepository extends DataTablesRepository<AllInformationEntity,Integer> {
}
