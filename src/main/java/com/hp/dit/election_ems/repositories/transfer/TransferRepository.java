package com.hp.dit.election_ems.repositories.transfer;

import com.hp.dit.election_ems.entities.SubModuleMaster;
import com.hp.dit.election_ems.entities.TransferRequestEntities;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends DataTablesRepository<TransferRequestEntities, Integer>, TransferRepositoryCustom {

    @Query(value = "select t.bank_id, b.bank_name, count(t.bank_id) as total from mst_transfer_request_entries t join mst_bank b ON b.bank_id = t.bank_id  group by t.bank_id, b.bank_name", nativeQuery = true)
    List<Object[]> getPassGeneratedBarrierWiseTotal();

}
