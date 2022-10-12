package com.hp.dit.election_ems.repositories.transfer;

import com.hp.dit.election_ems.entities.TransferRequestEntities;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepositoryCustom  {

    TransferRequestEntities getTransactionViaId(Integer transactionId);

}
