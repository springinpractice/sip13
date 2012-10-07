package com.springinpractice.ch13.helpdesk.portal.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springinpractice.ch13.helpdesk.portal.model.Customer;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findByUsername(String username);
	
	List<Customer> findByUsernameIn(Collection<String> usernames);
}
