package com.clotzer.movie.db.backend.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.clotzer.movie.db.backend.dto.UserEntity;

public class UserInfoUserDetails implements UserDetails {

	private static final long serialVersionUID = -8773921465190832995L;
	private String name;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserInfoUserDetails(UserEntity userEntityInfo) {
		name = userEntityInfo.getUsername();
		password = userEntityInfo.getPassword();

		authorities = Arrays.stream("USER".split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

}