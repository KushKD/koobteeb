package com.hp.dit.police.inventory.repositories.policelines;

import com.hp.dit.police.inventory.entities.PoliceLines;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PoliceLinesRepository extends CrudRepository<PoliceLines, Integer>, PoliceLinesRepositoryCustom {

}
