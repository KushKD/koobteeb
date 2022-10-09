package com.hp.dit.election_ems.Controller.transfer;

import com.hp.dit.election_ems.Controller.beat.BeatRestController;
import com.hp.dit.election_ems.entities.BankMaster;
import com.hp.dit.election_ems.entities.SubModuleMaster;
import com.hp.dit.election_ems.entities.TransferRequestEntities;
import com.hp.dit.election_ems.modals.LoggedInUserSession;
import com.hp.dit.election_ems.repositories.bank.BankRepository;
import com.hp.dit.election_ems.repositories.transfer.TransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class TransferRequestRestController {

    @Autowired
    TransferRepository transferRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(TransferRequestEntities.class);

    @RequestMapping(value = "/apidataTable/viewTransferRequests", method = RequestMethod.GET)
    public DataTablesOutput<TransferRequestEntities> allDistricts_(@Valid DataTablesInput input, HttpServletRequest request) {
        System.out.println(input);
        LoggedInUserSession user = (LoggedInUserSession) request.getSession().getAttribute("UserData");
        System.out.println("-=-=-=-=-=-="+user);


        Specification<TransferRequestEntities> userId = (Specification<TransferRequestEntities>) (root, query, criteriaBuilder) -> {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<TransferRequestEntities> cq = cb.createQuery(TransferRequestEntities.class);
            Root<TransferRequestEntities> book = cq.from(TransferRequestEntities.class);
            Predicate stateId_ = cb.equal(book.get("enteredBy").<Long>get("userId"), user.getUserId());
            cq.where(stateId_);

            TypedQuery<TransferRequestEntities> queryq =  entityManager.createQuery(cq);
            return cq.getRestriction();
        };


        return transferRepository.findAll(input,userId);
    }
}
