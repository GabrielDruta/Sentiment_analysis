package com.ase.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ase.springboot.persistence.entities.UserEntity;
import com.ase.springboot.repository.UserRepository;
import com.ase.springboot.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("user/")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("users")
	public List<UserEntity> getUsers() {
		return this.userService.findAll();
	}

	@PostMapping("register")
	public ResponseEntity<String> registerUser(@RequestParam String email, @RequestParam String password,
			@RequestParam String firstName, @RequestParam String lastName, @RequestParam String accessToken) {

		UserEntity savedUser = userService
				.createUser(new UserEntity(email, password, firstName, lastName, accessToken));

		return ResponseEntity.ok().body("User created successfully: id  "+savedUser.getId());
	}

	@GetMapping("users/{nameGG}")
	public List<UserEntity> getNames(@RequestParam String namegg) {
		// return this.userRepository.findAll();
		System.out.println(" /n/n\n\n\n \n ************\n" + namegg);
	
		return null;
	}

}