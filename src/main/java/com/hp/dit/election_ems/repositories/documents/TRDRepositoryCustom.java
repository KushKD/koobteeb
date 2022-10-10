package com.hp.dit.election_ems.repositories.documents;

import com.hp.dit.election_ems.entities.TrdocumentsEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TRDRepositoryCustom {

    List<TrdocumentsEntity> getDocumentsViaId(Integer id);
}
