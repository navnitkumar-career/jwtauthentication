package com.jwt;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jwt.model.User;
import com.jwt.repository.UserRepository;

@SpringBootApplication
public class JwtauthenticationApplication implements CommandLineRunner {
	
	@Autowired
	public UserRepository userRepository;

	static Random random = new SecureRandom();
	
	public  void createUser()
	{
		User user = new User();
		Long id = new Long(random.nextInt(100));
		user.setUserId(id);
		user.setUserName("Brijesh");
		user.setEmail("brijeshsavaliya"+id+"@gmail.com");
		user.setPassword("brijesh@"+id);
		user.setEnabled(true);
		user.setRole("ADMIN");
		
	    User addUser = this.userRepository.save(user);
	    System.out.println(addUser);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(JwtauthenticationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		createUser();
	}

}
