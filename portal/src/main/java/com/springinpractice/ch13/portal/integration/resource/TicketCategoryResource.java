package com.springinpractice.ch13.portal.integration.resource;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketCategoryResource extends ResourceSupport {
	public String key;
	public String name;
	
	// Need the getters to render this resource in the JSPs.
	
	public String getKey() { return key; }
	
	public String getName() { return name; }
}
