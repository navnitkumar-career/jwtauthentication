package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.helper.JwtUtil;
import com.jwt.model.JwtResponse;
import com.jwt.model.JwtRequest;
import com.jwt.services.CustomeUserDetailsService;

@RestController
@CrossOrigin(origins = "*")
public class JwtController {

	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	@Autowired
	private JwtUtil JwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest JwtRequest) throws Exception {
		System.out.println(JwtRequest); 
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(JwtRequest.getUserName(), JwtRequest.getPassword()));
		} catch (UsernameNotFoundException usernameNotFoundException) {
			usernameNotFoundException.printStackTrace();
			throw new Exception("Bad Credentials user name not found");
		} catch (BadCredentialsException badCredentialsException) {
			badCredentialsException.printStackTrace();
			throw new Exception("Bad Credentials 123");
		}
		UserDetails loadUserByUsername = this.customeUserDetailsService.loadUserByUsername(JwtRequest.getUserName());
		String generateToken = this.JwtUtil.generateToken(loadUserByUsername);
		System.out.println("JWT TOKEN : " + generateToken);

		return ResponseEntity.ok(new JwtResponse(generateToken));
	}
}
