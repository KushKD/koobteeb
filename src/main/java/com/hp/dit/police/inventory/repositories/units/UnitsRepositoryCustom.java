package com.hp.dit.police.inventory.repositories.units;

import com.hp.dit.police.inventory.entities.StoreEntity;
import com.hp.dit.police.inventory.entities.UnitsEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitsRepositoryCustom {

    List<UnitsEntity> getAllUnits();
    List<UnitsEntity> getAllActiveUnits();
    Integer unitCount(String unitName);
    UnitsEntity getUnitViaUnitId(Integer unitId);
}