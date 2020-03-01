package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.UserEntity;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;

@RestController
public class Controller {
	
	@Autowired
	UserRepository userRepo;

	@GetMapping("/name/{name}")
	public String getName(@PathVariable String name) {
		return "Hello World   ...  "+ name;
	}
	
	@GetMapping("/getUser/{id}")
	public UserEntity getUser(@PathVariable int id) {
		  Optional<UserEntity> findById = userRepo.findById(id);
		  if(findById.isPresent()){
			  return findById.get();
		  }else return null;
	}
	
	@PostMapping("/postName")
	public String postName(@RequestBody User user) {
		user.setName(user.getName()+" Singh");
		
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setName(user.getName());
		entity.setAddress(user.getAddress());
		entity.setPhoneNo(user.getPhoneNo());
		userRepo.save(entity);
		
		return "Data saved successfully";
	}
	
	@PostMapping("/postName/get")
	public User postNameGet(@RequestBody User user) {
		user.setName(user.getName()+" Singh");
		return user;
	}

}
