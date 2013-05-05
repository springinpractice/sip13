/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.helpdesk.integration.gateway.impl;

import static org.springframework.util.Assert.notNull;

import java.util.Collection;

import org.springframework.web.client.RestTemplate;

import com.springinpractice.ch13.helpdesk.integration.gateway.PortalGateway;
import com.springinpractice.ch13.helpdesk.integration.resource.CustomerResource;
import com.springinpractice.ch13.helpdesk.integration.resource.CustomerResources;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class PortalGatewayImpl implements PortalGateway {
	private static final int FIND_BY_USERNAME_IN_LIMIT = 20;
	
	private RestTemplate restTemplate;
	private String baseUrl;
	
	public PortalGatewayImpl(RestTemplate restTemplate, String baseUrl) {
		this.restTemplate = restTemplate;
		this.baseUrl = baseUrl;
	}
	
	@Override
	public CustomerResource findCustomerByUsername(String username) {
		notNull(username, "username can't be null");
		String url = baseUrl + "/users/search/find-by-username?username={username}";
		CustomerResources customers = restTemplate.getForObject(url, CustomerResources.class, username);
		CustomerResource customer = customers.getContent().iterator().next();
		return customer;
	}
	
	@Override
	public Collection<CustomerResource> findCustomersByUsernameIn(Collection<String> usernames) {
		notNull(usernames, "usernames can't be null");
		
		// URL can can grow very long if the query isn't constrained.
		if (usernames.size() > FIND_BY_USERNAME_IN_LIMIT) {
			throw new IllegalArgumentException(
					"Max # usernames is " + FIND_BY_USERNAME_IN_LIMIT + ". Consider pagination.");
		}
		
		// Doing this because I don't see a way to do it with RestTemplate.
		StringBuilder builder = new StringBuilder(baseUrl + "/users/search/find-by-username-in?");
		for (String username : usernames) {
			builder.append("username=");
			
			// Raw username is correct since getForObject(String, ...) assumes unencoded URL.
			builder.append(username);
			
			builder.append("&");
		}
		
		// Remove the trailing ? or &
		String url = builder.substring(0, builder.length() - 1);
		
		CustomerResources customers = restTemplate.getForObject(url, CustomerResources.class);
		return customers.getContent();
	}
}
