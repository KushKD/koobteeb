package com.hp.dit.beetbook.repositories.comments;

import com.hp.dit.beetbook.entities.CommentsMaster;
import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.repositories.districtRepository.DistrictRepositoryCustom;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentsRepository extends DataTablesRepository <CommentsMaster, Integer>, CommentsRepositoryCustom {

}
