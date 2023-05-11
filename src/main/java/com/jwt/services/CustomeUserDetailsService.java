package com.jwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.model.CustomUserDetails;
import com.jwt.model.User;
import com.jwt.repository.UserRepository;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		/*
		 * if (userName.equals("Brijesh")) { return new User("Brijesh", "Brijesh@2002",
		 * new ArrayList<>()); } else { throw new
		 * UsernameNotFoundException("User Not Found"); }
		 */

		User findByUsername = this.userRepository.findByUserName(userName);
		if (findByUsername == null) {
			throw new UsernameNotFoundException("User Not Found");
		} else {
			return new CustomUserDetails(findByUsername);
		}

	}
}
