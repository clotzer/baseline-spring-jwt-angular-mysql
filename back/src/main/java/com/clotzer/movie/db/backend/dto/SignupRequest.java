package com.clotzer.movie.db.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

	private String name;

	private String username;
	
	private String password;
	
}