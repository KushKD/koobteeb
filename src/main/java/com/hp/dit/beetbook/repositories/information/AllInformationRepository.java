package com.hp.dit.beetbook.repositories.information;

import com.hp.dit.beetbook.entities.AllInformationEntity;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllInformationRepository extends DataTablesRepository<AllInformationEntity,Integer> {
}
