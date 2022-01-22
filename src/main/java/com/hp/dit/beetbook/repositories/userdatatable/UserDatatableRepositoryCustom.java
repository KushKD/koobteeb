package com.hp.dit.beetbook.repositories.userdatatable;

import com.hp.dit.beetbook.entities.UserDatatableEntity;
import com.hp.dit.beetbook.modals.usersviabeat.UsersViaBeat;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDatatableRepositoryCustom  {

    List<UsersViaBeat> getActiveUsersViaBeat(Integer beatId);
}
