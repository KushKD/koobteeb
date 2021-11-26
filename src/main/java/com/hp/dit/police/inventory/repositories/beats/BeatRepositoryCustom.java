package com.hp.dit.police.inventory.repositories.beats;

import com.hp.dit.police.inventory.entities.BeatMaster;
import com.hp.dit.police.inventory.modals.beats.BeatsNameId;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "beats_")
public interface BeatRepositoryCustom {


    List<BeatMaster> findBeatByPSId(Integer polise_station) throws Exception;
    List<BeatsNameId> findBeatNameIdByPSId(Integer polise_station) throws Exception;
    List<BeatMaster> getAllBeats() throws Exception;
    List<BeatMaster> getAllActiveBeats() throws Exception;
    BeatMaster getBeatViaId(Integer beatId) throws Exception;
    Integer beatCount(String beatName);


}
