package com.hp.dit.beetbook.repositories.comments;

import com.hp.dit.beetbook.entities.CommentsMaster;
import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.repositories.districtRepository.DistrictRepositoryCustom;
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
public class CommentsRepositoryCustomImpl implements CommentsRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CommentsMaster> getAllActiveComments(Integer id) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CommentsMaster> cq = cb.createQuery(CommentsMaster.class);
        Root<CommentsMaster> book = cq.from(CommentsMaster.class);
        Predicate informationId = cb.equal(book.get("informationId"), id);
        Predicate active = cb.equal(book.get("active"), true);
        Predicate deleted = cb.equal(book.get("deleted"), false);
        cq.where(informationId,active,deleted);
        TypedQuery<CommentsMaster> query =  entityManager.createQuery(cq);
        return query.getResultList();
    }


}
