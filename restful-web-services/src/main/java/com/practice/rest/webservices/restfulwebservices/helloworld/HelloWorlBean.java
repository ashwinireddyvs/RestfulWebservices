package com.practice.rest.webservices.restfulwebservices.helloworld;

import lombok.Data;
import lombok.NonNull;


@Data
public class HelloWorlBean {
	@NonNull
	private  String message;
}
