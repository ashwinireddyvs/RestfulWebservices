package com.practice.rest.webservices.restfulwebservices.SocialMedia;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	//constructor injection
	/*
	 * private UserDAOService userDAOService; public UserResource(UserDAOService
	 * userDAOService) { this.userDAOService=userDAOService; }
	 */
	
	@Autowired
	private UserDAOService userService;
	
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUserById(@PathVariable int id) {
		User user=userService.findById(id);
		if(user==null)
			throw new UserNotFoundException("user with the id:"+id+" not found");
		return user;
		
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.saveUser(user);
		
		//returns the path to access the new user created
		URI location=ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		userService.deleteById(id);
	}
	
}
