package com.practice.rest.webservices.restfulwebservices.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name="user_details")
public class User {
	
	public User(int i, String string, LocalDate minusYears) {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
	//filtering {2 types :static(same for all requests) and dynamic}
	private Integer id;
	
	@NonNull
		//validation
	@Size(min = 2,message = "name should always atleast 2 characters")
		//customising name in response
	//@JsonProperty("user_name")
	private String name;
	
	@NonNull
	@Past(message = "Birth date should be always past")
	private LocalDate birthdate;
	
	@OneToMany(mappedBy = "user")
	//ignoring in responce
	@JsonIgnore
	private List<Post> posts;
	
	
	
}
