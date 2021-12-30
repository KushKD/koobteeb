package com.hp.dit.police.inventory.repositories.stockin;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class StockInRepositoryCustomImpl implements StockInRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
}
