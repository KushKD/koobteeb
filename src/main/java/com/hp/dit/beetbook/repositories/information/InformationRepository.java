package com.hp.dit.beetbook.repositories.information;

import com.hp.dit.beetbook.entities.InformationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends CrudRepository<InformationEntity,Integer> ,InformationRepositoryCustom {


}
