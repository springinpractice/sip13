/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.portal.service.impl;

import static org.springframework.util.Assert.notNull;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springinpractice.ch13.portal.model.User;
import com.springinpractice.ch13.portal.repo.UserRepository;
import com.springinpractice.ch13.portal.service.UserService;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Inject private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		notNull(username, "username can't be null");
		User user = userRepo.findByUsername(username);
		if (user == null) { throw new UsernameNotFoundException(username); }
		return user;
	}
}
