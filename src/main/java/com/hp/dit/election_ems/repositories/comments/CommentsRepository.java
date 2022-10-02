package com.hp.dit.election_ems.repositories.comments;

import com.hp.dit.election_ems.entities.CommentsMaster;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentsRepository extends DataTablesRepository <CommentsMaster, Integer>, CommentsRepositoryCustom {

}
