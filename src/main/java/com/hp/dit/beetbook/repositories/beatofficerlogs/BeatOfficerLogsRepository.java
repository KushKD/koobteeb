package com.hp.dit.beetbook.repositories.beatofficerlogs;

import com.hp.dit.beetbook.entities.BeatOfficerLogs;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeatOfficerLogsRepository extends DataTablesRepository<BeatOfficerLogs,Long> {
}
