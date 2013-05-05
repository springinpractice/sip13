/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.portal.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.repository.annotation.RestResource;

import com.springinpractice.ch13.portal.model.User;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@RestResource(path = "users")
public interface UserRepository extends JpaRepository<User, Long> {
	
	@RestResource(path = "find-by-username")
	User findByUsername(@Param("username") String username);
	
	@RestResource(path = "find-by-username-in")
	List<User> findByUsernameIn(@Param("username") Collection<String> usernames);
}
