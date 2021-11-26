package com.hp.dit.police.inventory.repositories.userlocationlogs;

import com.hp.dit.police.inventory.entities.UserLocationLogsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLocationLogsRepository extends CrudRepository<UserLocationLogsEntity,Integer> {

    @Query(value="SELECT DISTINCT log.beat_id, username ,user_id,role_id,mobile_number,mb.beat_name,ps.ps_name, to_char(log.createddate,'DD-MM-YYYY') FROM user_location_logs  log INNER JOIN mst_beat mb ON mb.beat_id = log.beat_id INNER JOIN mst_policestation ps ON ps.ps_id = mb.ps_id where to_char(log.createddate,'DD-MM-YYYY') =:date" ,nativeQuery = true)
    List<Object[]> getActiveBeats(@Param("date")String Date_);
}
