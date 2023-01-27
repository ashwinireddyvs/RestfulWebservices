package com.practice.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;


@RestController
public class HelloWorldController {

	@GetMapping("/hello-world")
	public String helloWorld(){
		return "Hello World";
	}
	
	@RequestMapping(method=RequestMethod.GET,path="hello-world1")
	public String helloworld1() {
		return "Hello wotld 1";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorlBean helloWorldBean(){
		return new HelloWorlBean("Good Morning");
	}
	

	@GetMapping("/hello-world-bean/path-variable/{name}")
	public HelloWorlBean helloWorldBeanPathVariable(@PathVariable String name){
		return new HelloWorlBean(String.format("Good Morning, %s", name));
	}
	
}
