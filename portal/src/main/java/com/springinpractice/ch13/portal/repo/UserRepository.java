/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.portal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springinpractice.ch13.portal.model.User;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
}
