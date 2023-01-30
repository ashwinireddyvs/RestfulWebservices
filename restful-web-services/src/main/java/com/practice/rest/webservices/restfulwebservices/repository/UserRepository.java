package com.practice.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.rest.webservices.restfulwebservices.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
