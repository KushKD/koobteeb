package com.hp.dit.election_ems.repositories.transfer;

import com.hp.dit.election_ems.entities.SubModuleMaster;
import com.hp.dit.election_ems.entities.TransferRequestEntities;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class TransferRepositoryCustomImpl implements TransferRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public TransferRequestEntities getTransactionViaId(Integer transactionId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TransferRequestEntities> cq = cb.createQuery(TransferRequestEntities.class);
        Root<TransferRequestEntities> book = cq.from(TransferRequestEntities.class);
        Predicate transactionId_ = cb.equal(book.get("transferRequestID"), transactionId);
        cq.where(transactionId_);
        TypedQuery<TransferRequestEntities> query =  entityManager.createQuery(cq);
        return query.getResultList().get(0);
    }
}
