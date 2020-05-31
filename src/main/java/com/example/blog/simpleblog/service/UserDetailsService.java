package com.example.blog.simpleblog.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blog.simpleblog.repository.UserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.example.blog.simpleblog.model.User user = userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("No User found" + username));
		return new User(user.getUserName(), user.getPassword(), getAuthorities("ROLE_USER"));
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(String role_user){
		return Collections.singletonList(new SimpleGrantedAuthority(role_user));
	}
}
