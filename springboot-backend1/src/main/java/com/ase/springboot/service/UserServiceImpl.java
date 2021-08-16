package com.ase.springboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ase.springboot.persistence.entities.UserEntity;
import com.ase.springboot.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserEntity createUser(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	public UserEntity updateUser(UserEntity userU) {
		return userRepository.saveAndFlush(userU);
	}

	@Override
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

}
