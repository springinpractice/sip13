package com.springinpractice.ch13.helpdesk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springinpractice.ch13.helpdesk.model.User;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
}
