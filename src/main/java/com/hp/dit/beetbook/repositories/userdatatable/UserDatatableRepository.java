package com.hp.dit.beetbook.repositories.userdatatable;

import com.hp.dit.beetbook.entities.UserDatatableEntity;
import com.hp.dit.beetbook.modals.usersviabeat.UsersViaBeat;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import java.util.List;

public interface UserDatatableRepository extends DataTablesRepository<UserDatatableEntity,Long>,UserDatatableRepositoryCustom {



}
