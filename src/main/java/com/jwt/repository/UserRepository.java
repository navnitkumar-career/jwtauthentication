package com.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	public User findByUserName(String username);

}
