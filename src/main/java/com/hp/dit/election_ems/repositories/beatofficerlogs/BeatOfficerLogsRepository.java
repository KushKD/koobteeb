package com.hp.dit.election_ems.repositories.beatofficerlogs;

import com.hp.dit.election_ems.entities.BeatOfficerLogs;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeatOfficerLogsRepository extends DataTablesRepository<BeatOfficerLogs,Long> {
}
