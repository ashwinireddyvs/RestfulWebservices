package com.practice.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//step 1: all req should be authenticated
		http.authorizeHttpRequests(
				auth->auth.anyRequest().authenticated()
				);
		
		//step 2: show default authentication page(login page)
		http.httpBasic(withDefaults());
		
		//step 3: CSRF: post, put
		http.csrf().disable();
		return http.build();
	}
}
