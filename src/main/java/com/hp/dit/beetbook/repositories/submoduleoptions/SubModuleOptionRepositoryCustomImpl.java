package com.hp.dit.beetbook.repositories.submoduleoptions;

import com.hp.dit.beetbook.entities.ModuleMaster;
import com.hp.dit.beetbook.entities.OptionsMaster;
import com.hp.dit.beetbook.entities.SubModuleMaster;
import com.hp.dit.beetbook.modals.submoduleModal.SubModuleRoleList;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class SubModuleOptionRepositoryCustomImpl implements SubModuleOptionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<OptionsMaster> getOptionsViaSubModuleId(Integer SubModuleId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OptionsMaster> cq = cb.createQuery(OptionsMaster.class);
        Root<OptionsMaster> book = cq.from(OptionsMaster.class);
        Predicate isActive_ = cb.equal(book.get("active"), true);
        cq.where(isActive_);
        TypedQuery<OptionsMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }
}
