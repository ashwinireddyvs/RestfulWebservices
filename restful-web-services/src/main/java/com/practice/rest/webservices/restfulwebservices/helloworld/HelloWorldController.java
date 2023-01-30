package com.practice.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;


@RestController
public class HelloWorldController {

	private MessageSource messageSource;
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource=messageSource;
	}
	
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
	
	@GetMapping("/hello-world-internationalization")
	public String helloWorldInternationalization(){
		
		Locale locale=LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null,"Default Message", locale);
	}
	
}
