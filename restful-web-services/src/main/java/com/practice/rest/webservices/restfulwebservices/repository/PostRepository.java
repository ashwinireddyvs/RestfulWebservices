package com.practice.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.rest.webservices.restfulwebservices.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
