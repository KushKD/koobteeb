package com.hp.dit.election_ems.repositories.transfer;

import com.hp.dit.election_ems.entities.SubModuleMaster;
import com.hp.dit.election_ems.entities.TransferRequestEntities;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends DataTablesRepository<TransferRequestEntities, Integer>, TransferRepositoryCustom {
}
