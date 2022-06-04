package com.hp.dit.beetbook.repositories.comments;

import com.hp.dit.beetbook.entities.CommentsMaster;
import com.hp.dit.beetbook.entities.DistrictMaster;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheConfig(cacheNames = "districts_")
public interface CommentsRepositoryCustom {


    List<CommentsMaster> getAllActiveComments(Integer Id) throws Exception;
}
