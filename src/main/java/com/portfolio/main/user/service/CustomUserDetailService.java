package com.portfolio.main.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.portfolio.main.exception.CUserNotFoundException;
import com.portfolio.main.repository.UserRepository;
import com.portfolio.main.user.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Optional<User> userInfo = userRepository.findByUserId(userId);
		
		return userInfo.orElseThrow(CUserNotFoundException::new);
	}
}
