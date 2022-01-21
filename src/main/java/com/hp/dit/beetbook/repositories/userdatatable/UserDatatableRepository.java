package com.hp.dit.beetbook.repositories.userdatatable;

import com.hp.dit.beetbook.entities.UserDatatableEntity;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface UserDatatableRepository extends DataTablesRepository<UserDatatableEntity,Long> {
}
