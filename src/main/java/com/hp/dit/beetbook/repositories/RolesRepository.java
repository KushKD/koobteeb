package com.hp.dit.beetbook.repositories;


import com.hp.dit.beetbook.ModalInterfaces.RoleIdName;
import com.hp.dit.beetbook.modals.RolesModal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hp.dit.beetbook.entities.RolesEntity;
import com.hp.dit.beetbook.entities.UserEntity;

import java.util.List;
import java.util.Set;


@Repository
public interface RolesRepository extends CrudRepository<RolesEntity,Integer> {

    @Query(value = "SELECT role_id, role_name from roles where active = true", nativeQuery = true)
     List<Object[]> getRoles();

    @Query(value = "SELECT * from roles where active = true AND role_name =:role_name_" , nativeQuery = true)
     RolesEntity checkRole(@Param("role_name_") String rolenmae);

    @Query(value = "SELECT urm.role_id,  role.role_name FROM user_role_mapping as urm INNER JOIN  roles as role ON urm.role_id = role.role_id WHERE user_id =:userID" , nativeQuery = true)
    List<Object[]> getRoleViaUser(@Param("userID") Long userID);



}