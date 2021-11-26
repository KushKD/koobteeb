package com.hp.dit.police.inventory.services;

import com.hp.dit.police.inventory.entities.RolesEntity;
import com.hp.dit.police.inventory.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
	
	@Autowired
	private RolesRepository rolesRepository;


	public RolesRepository getRolesRepository() {
		return rolesRepository;
	}

	public void setRolesRepository(RolesRepository rolesRepository) {
		this.rolesRepository = rolesRepository;
	}

	public RolesEntity saveRole(RolesEntity entity) {
		return rolesRepository.save(entity);
		
	}


	public RolesEntity checkRoleName(String roleName) {
		return rolesRepository.checkRole(roleName);
	}

	public Optional<RolesEntity> getRoleDetails(String id){
		return rolesRepository.findById(Integer.parseInt(id));
	}
}
