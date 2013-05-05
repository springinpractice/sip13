/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.portal.integration.resource;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketStatusResource extends ResourceSupport {
	private String key;
	private String name;
	
	public String getKey() { return key; }
	
	public String getName() { return name; }
}
