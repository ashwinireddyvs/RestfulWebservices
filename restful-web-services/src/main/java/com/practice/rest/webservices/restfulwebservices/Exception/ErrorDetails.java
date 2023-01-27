package com.practice.rest.webservices.restfulwebservices.Exception;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorDetails {
	@NonNull
	private LocalDate timestamp;
	@NonNull
	private String message;
	@NonNull
	private String details;

}
