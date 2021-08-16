package com.ase.springboot.service;

import java.util.List;

import com.ase.springboot.persistence.entities.UserEntity;

public interface UserService {

	UserEntity createUser(UserEntity user);
	
	//add methods for users,
	//find userbyId, find User
	//create user, update user (register)
	
	//add access Token
	//updateAccessToken
	
	UserEntity updateUser(UserEntity userU);
	
	List<UserEntity> findAll();
	
	
	
}
