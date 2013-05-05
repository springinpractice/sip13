/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.helpdesk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.repository.annotation.RestResource;

import com.springinpractice.ch13.helpdesk.model.UserEntity;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@RestResource(path = "users")
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	@RestResource(path = "find-by-username")
	UserEntity findByUsername(String username);
}
