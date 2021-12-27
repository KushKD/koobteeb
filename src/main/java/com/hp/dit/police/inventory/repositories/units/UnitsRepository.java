package com.hp.dit.police.inventory.repositories.units;

import com.hp.dit.police.inventory.entities.UnitsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitsRepository extends CrudRepository<UnitsEntity,Integer> , UnitsRepositoryCustom {
}
