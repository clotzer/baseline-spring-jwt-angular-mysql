package com.clotzer.movie.db.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clotzer.movie.db.backend.dto.LoginRequest;
import com.clotzer.movie.db.backend.dto.SignupRequest;
import com.clotzer.movie.db.backend.dto.SignupResponse;
import com.clotzer.movie.db.backend.dto.UserEntity;
import com.clotzer.movie.db.backend.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String login(LoginRequest request) {
		Optional<UserEntity> user = loginRepository.findByUsername(request.getUsername());

		if (user.isPresent()) {
			return "user details found";
		}

		return "user details not found";
	}
	
	public SignupResponse register(SignupRequest request) {
		Optional<UserEntity> user = loginRepository.findByUsername(request.getUsername());
		
		SignupResponse response = new SignupResponse();
		
		if (user.isPresent()) {
			response.setResponse("user already exists");
			return response;
		}
		
		UserEntity _userEntity = new UserEntity();
		_userEntity.setName(request.getName());
		_userEntity.setUsername(request.getUsername());
		_userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
		loginRepository.save(_userEntity);
		response.setResponse("user created with id " + _userEntity.getId());
		return response;
	}

}