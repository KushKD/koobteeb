package com.hp.dit.election_ems.repositories.comments;

import com.hp.dit.election_ems.entities.CommentsMaster;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheConfig(cacheNames = "districts_")
public interface CommentsRepositoryCustom {


    List<CommentsMaster> getAllActiveComments(Integer Id) throws Exception;
}
