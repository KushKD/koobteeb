package com.hp.dit.election_ems.repositories.transfer;

import com.hp.dit.election_ems.entities.TransferRequestEntities;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class TransferRepositoryCustomImpl implements TransferRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;



}
