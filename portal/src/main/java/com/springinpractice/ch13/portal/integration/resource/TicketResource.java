package com.springinpractice.ch13.portal.integration.resource;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketResource extends ResourceSupport {
	public String username;
	public TicketCategoryResource category;
	public String description;
	public Date dateCreated;
	
	// Need the getters to render this resource in the JSPs.
	
	public String getUsername() { return username; }
	
	public void setUsername(String username) { this.username = username; }
	
//	@NotNull
	public TicketCategoryResource getCategory() { return category; }
	
	public void setCategory(TicketCategoryResource category) { this.category = category; }
	
	@NotNull
	@Size(max = 4000)
	public String getDescription() { return description; }
	
	public void setDescription(String description) { this.description = description; }
	
	public Date getDateCreated() { return dateCreated; }
	
	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }
}
