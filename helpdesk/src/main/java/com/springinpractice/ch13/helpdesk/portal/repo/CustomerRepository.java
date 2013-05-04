/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.helpdesk.portal.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springinpractice.ch13.helpdesk.portal.model.CustomerEntity;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	
	CustomerEntity findByUsername(String username);
	
	List<CustomerEntity> findByUsernameIn(Collection<String> usernames);
}
