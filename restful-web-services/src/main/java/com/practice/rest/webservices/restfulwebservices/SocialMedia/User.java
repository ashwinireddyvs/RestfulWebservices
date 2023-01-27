package com.practice.rest.webservices.restfulwebservices.SocialMedia;

import java.time.LocalDate;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class User {
	@NonNull
	private Integer id;
	
	@NonNull
	@Size(min = 2,message = "name should always atleast 2 characters")
	private String name;
	
	@NonNull
	@Past(message = "Birth date should be always past")
	private LocalDate birthDate;
	
}
