package com.practice.rest.webservices.restfulwebservices.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.practice.rest.webservices.restfulwebservices.entity.User;


@Component
public class UserDAOService {
	private static List<User> users=new ArrayList<>();
	private static int count=3;
	
	static {
		users.add(new User(1,"aaa",LocalDate.now().minusYears(30)));
		users.add(new User(2,"bbb",LocalDate.now().minusYears(25)));
		users.add(new User(3,"ccc",LocalDate.now().minusYears(20)));
	}
	
	public List<User> findAll(){
		return users;
	}

	public User findById(int id) {
		// TODO Auto-generated method stub
		//User user=users.stream().filter(usr->usr.getId().equals(id)).findFirst().get();

		Predicate<? super User> predicate = user->user.getId().equals(id);
		//User user=users.stream().filter(predicate).findFirst().get(); //it returns noSuchElementException
		
		User user=users.stream().filter(predicate).findFirst().orElse(null);
		return user;
	}
	
	public User saveUser(User user) {
		user.setId(++count);
		users.add(user);
		return user;
	}

	public void deleteById(int id) {
		Predicate<? super User> predicate=user->user.getId().equals(id);
		// TODO Auto-generated method stub
		users.removeIf(predicate);
		
	}
}
