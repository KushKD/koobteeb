package com.hp.dit.beetbook.repositories.user;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hp.dit.beetbook.entities.UserEntity;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long>, UserRepositoryCustom {


	UserEntity findByUsername(String username);

	@Query(value="SELECT u.user_id, u.first_name, u.last_name, u.username,urm.role_id, r.role_name, u.mobile_number, u.state_id,state.state_name, u.district_id,district.district_name ,u.beat_id, beat.beat_name FROM users as u INNER JOIN user_role_mapping as urm on urm.user_id = u.user_id INNER JOIN roles as r on r.role_id = urm.role_id INNER JOIN mst_state as state on state.state_id = u.state_id INNER JOIN mst_district as district on district.district_id = u.district_id INNER JOIN mst_beat as beat on beat.beat_id = u.beat_id", nativeQuery = true)
	List<Object[]> getUsersDetails();


	@Query(value="SELECT u.user_id, u.first_name, u.last_name, u.username,urm.role_id, r.role_name, u.mobile_number, u.state_id,state.state_name, u.district_id,district.district_name ,u.beat_id, beat.beat_name ,u.rank, u.sosdpo_id, u.ps_id FROM users as u INNER JOIN user_role_mapping as urm on urm.user_id = u.user_id INNER JOIN roles as r on r.role_id = urm.role_id INNER JOIN mst_state as state on state.state_id = u.state_id INNER JOIN mst_district as district on district.district_id = u.district_id INNER JOIN mst_beat as beat on beat.beat_id = u.beat_id WHERE u.user_id =:userId AND urm.role_id =:roleId", nativeQuery = true)
	List<Object[]> getSpecificUserViaUserRoleId(@Param("userId")  Integer userId, @Param("roleId") Integer role_id);



	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE users SET first_name =:firstname, last_name =:lastname, username =:username, password =:password, mobile_number =:mobileNumber, state_id =:stateId, district_id =:districtId, beat_id =:beatId, sosdpo_id =:sosdpoId, ps_id =:psId, rank =:rank, active =:active, lastmodifieddate =:lastmodifieddate WHERE user_id =:userId" , nativeQuery = true)
	int updateUser(@Param("firstname") String firstname,
				   @Param("lastname") String lastname,
				   @Param("username") String username,
				   @Param("password") String password,
				   @Param("mobileNumber") BigInteger mobileNumber,
				   @Param("stateId") Integer stateId,
				   @Param("districtId") Integer districtId,
				   @Param("beatId") Integer beatId,
				   @Param("active") Boolean active,
				   @Param("lastmodifieddate") Date lastmodifieddate,
				   @Param("userId") Integer userId,
				   @Param("sosdpoId") Integer sosdpoId,
				   @Param("psId") Integer psId,
				   @Param("rank") String rank);


	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE user_role_mapping SET role_id =:roleId WHERE user_id =:userId AND role_id =:oldRoleId" , nativeQuery = true)
	int updateRole(@Param("roleId") Integer roleId,
				   @Param("userId") Integer userId,
				   @Param("oldRoleId") Integer oldRoleId);



	@Query(value="SELECT concat(u.first_name,' ', u.last_name) as cname,  u.user_id FROM users as u INNER JOIN user_role_mapping as urm ON urm.user_id = u.user_id INNER JOIN roles as r ON r.role_id = urm.role_id WHERE state_id =:stateId AND district_id=:districtId AND barrier_id =:barrierId AND r.role_name =:roleName", nativeQuery = true)
	List<Object[]> getRevenueUsersViaBarrier(@Param("stateId")  Integer stateId, @Param("districtId") Integer districtId, @Param("barrierId") Integer barrierId, @Param("roleName") String roleName);



}