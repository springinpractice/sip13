/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.helpdesk.integration.resource;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerResource extends ResourceSupport {
	public String username;
	public String firstName;
	public String lastName;
	public String email;
	
	// Need the getters to render this resource in the JSPs.
	
	public String getUsername() { return username; }

	public String getFirstName() { return firstName; }

	public String getLastName() { return lastName; }

	public String getEmail() { return email; }

	public String getFirstNameLastName() { return firstName + " " + lastName; }
}
