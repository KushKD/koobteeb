package com.hp.dit.election_ems.repositories.userdatatable;

import com.hp.dit.election_ems.entities.UserDatatableEntity;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface UserDatatableRepository extends DataTablesRepository<UserDatatableEntity,Long>,UserDatatableRepositoryCustom {



}
