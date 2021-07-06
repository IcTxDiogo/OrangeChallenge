package com.desafio.comics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.comics.entidades.User;
import com.desafio.comics.repository.UserRepos;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserRepos userRepos;
	
	@PostMapping
	public ResponseEntity<Void> cadastrar(@RequestBody User user) {
		try {
			userRepos.save(user);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
