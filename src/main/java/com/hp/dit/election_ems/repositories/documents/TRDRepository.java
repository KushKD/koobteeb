package com.hp.dit.election_ems.repositories.documents;

import com.hp.dit.election_ems.entities.TrdocumentsEntity;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TRDRepository extends DataTablesRepository<TrdocumentsEntity,Integer>,TRDRepositoryCustom {
}
