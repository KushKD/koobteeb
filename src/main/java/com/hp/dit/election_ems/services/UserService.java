package com.hp.dit.election_ems.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hp.dit.election_ems.entities.UserEntity;
import com.hp.dit.election_ems.repositories.user.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;


	

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	
	public UserEntity getUserDetails(Long mobileNumber) {
		return userRepository.getUserDetails(mobileNumber);
		
	}

	public UserEntity saveUser(UserEntity entity) {
		return userRepository.save(entity);

	}

//	public List<Object[] > getUserId(String username){
//		return userRepository.getUserID(username);
//	}

}
