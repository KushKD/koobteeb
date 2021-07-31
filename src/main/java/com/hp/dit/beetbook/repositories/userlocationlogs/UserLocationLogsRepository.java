package com.hp.dit.beetbook.repositories.userlocationlogs;

import com.hp.dit.beetbook.entities.UserLocationLogsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLocationLogsRepository extends CrudRepository<UserLocationLogsEntity,Integer> {
}
