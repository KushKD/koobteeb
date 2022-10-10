package com.hp.dit.election_ems.repositories.transfer;

import com.hp.dit.election_ems.entities.TransferRequestEntities;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepositoryCustom  {

    TransferRequestEntities getTransactionViaId(Integer transactionId);
}
