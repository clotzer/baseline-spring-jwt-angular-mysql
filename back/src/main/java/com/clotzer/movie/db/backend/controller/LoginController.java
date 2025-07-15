package com.clotzer.movie.db.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clotzer.movie.db.backend.config.JWTService;
import com.clotzer.movie.db.backend.dto.DashboardResponse;
import com.clotzer.movie.db.backend.dto.LoginRequest;
import com.clotzer.movie.db.backend.dto.LoginResponse;
import com.clotzer.movie.db.backend.dto.SignupRequest;
import com.clotzer.movie.db.backend.dto.SignupResponse;
import com.clotzer.movie.db.backend.service.LoginService;

@RestController
@RequestMapping("/")
@CrossOrigin
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		LoginResponse response = new LoginResponse();

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		if (authentication.isAuthenticated()) {
			response.setToken(jwtService.generateToken(request.getUsername()));
		}

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	//@PreAuthorize("hasRole('USER')")
	@GetMapping("/dashboard")
	public ResponseEntity<DashboardResponse> dashboard() {
		DashboardResponse response = new DashboardResponse();
		response.setResponse("Success");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<SignupResponse> register(@RequestBody SignupRequest request) {
		return new ResponseEntity<>(loginService.register(request), HttpStatus.CREATED);
	}


	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin_dashboard")
	public ResponseEntity<DashboardResponse> admin_dashboard() {
		DashboardResponse response = new DashboardResponse();
		response.setResponse("Success");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}