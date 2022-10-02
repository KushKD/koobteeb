package com.hp.dit.election_ems.repositories.userdatatable;

import com.hp.dit.election_ems.modals.usersviabeat.UsersViaBeat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDatatableRepositoryCustom  {

    List<UsersViaBeat> getActiveUsersViaBeat(Integer beatId);
}
