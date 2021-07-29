package com.hp.dit.beetbook.repositories.beats;

import com.hp.dit.beetbook.entities.BeatMaster;
import com.hp.dit.beetbook.entities.DistrictMaster;
import com.hp.dit.beetbook.modals.beats.BeatsNameId;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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
