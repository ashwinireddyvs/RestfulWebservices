package com.practice.rest.webservices.restfulwebservices.SocialMedia;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.practice.rest.webservices.restfulwebservices.Exception.UserNotFoundException;
import com.practice.rest.webservices.restfulwebservices.entity.Post;
import com.practice.rest.webservices.restfulwebservices.entity.User;
import com.practice.rest.webservices.restfulwebservices.repository.PostRepository;
import com.practice.rest.webservices.restfulwebservices.repository.UserRepository;
import com.practice.rest.webservices.restfulwebservices.service.UserDAOService;

import io.swagger.v3.oas.annotations.links.Link;
import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/jpa")
public class UserJpaResource {

	//constructor injection
	/*
	 * private UserDAOService userDAOService; public UserResource(UserDAOService
	 * userDAOService) { this.userDAOService=userDAOService; }
	 */
	
	@Value("${profile}")
	String profile;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		System.out.println("profile========>"+profile);
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUserById(@PathVariable int id) {
		
		Optional<User> user=userRepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("user with the id:"+id+" not found");//
		//start - hateoas implemetation
			EntityModel<User> entityModel=EntityModel.of(user.get());
		
			//add link to this specific method 
			WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAllUsers());
			
			//add link to entitymodel
			entityModel.add(link.withRel("all-users"));
		
			
		return entityModel;
		//end - hateoas implemetation
		
		//return user;
		
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		//returns the path to access the new user created
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
		
		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retrievePostById(@PathVariable int id) {
		
		Optional<User> user=userRepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("user with the id:"+id+" not found");//

		return user.get().getPosts();
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Post post,@PathVariable int id) {
		Optional<User> user=userRepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("user with the id:"+id+" not found");
		
		post.setUser(user.get());
		Post savedPost = postRepository.save(post);
		
		//returns the path to access the new user created
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	//http://localhost:8080/jpa/users/id?id=1001
	@GetMapping("/users/id")
	public User getUserByID(@RequestParam(value = "id") int id) {
	Optional<User> user = userRepository.findById(id);
//	if(user.isEmpty())
//		throw new UserNotFoundException("User with ID "+id+" doesnot exists");
//	else
		
		return user.get();
		
	}
	
}
