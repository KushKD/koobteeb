package com.hp.dit.election_ems.repositories.beats;

import com.hp.dit.election_ems.entities.BeatMaster;
import com.hp.dit.election_ems.modals.beats.BeatsNameId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheConfig(cacheNames = "beats_")
public interface BeatRepositoryCustom {


    List<BeatMaster> findBeatByPSId(Integer polise_station) throws Exception;
    List<BeatsNameId> findBeatNameIdByPSId(Integer polise_station) throws Exception;
    List<BeatMaster> getAllBeats() throws Exception;
    List<BeatMaster> getAllActiveBeats() throws Exception;
    BeatMaster getBeatViaId(Integer beatId) throws Exception;
    Integer beatCount(String beatName);


}
