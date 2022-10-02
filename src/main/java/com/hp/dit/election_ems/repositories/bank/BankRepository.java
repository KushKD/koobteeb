package com.hp.dit.election_ems.repositories.bank;

import com.hp.dit.election_ems.entities.BankMaster;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BankRepository extends DataTablesRepository<BankMaster, Integer>, BankRepositoryCustom {

}
