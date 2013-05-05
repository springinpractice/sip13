/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.portal.integration.resource;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketResource extends ResourceSupport {
	private String username;
	private Link statusLink;
	private Link categoryLink;
	private String description;
	private Date dateCreated;
	
	// Need the getters to render this resource in the JSPs.
	
	// Use @JsonProperty here, not @RestResource. Spring Data REST never sees this class.
	@JsonProperty("customerUsername")
	public String getUsername() { return username; }
	
	public void setUsername(String username) { this.username = username; }
	
	// Don't use this constraint, because the ticket form doesn't set a status.
//	@NotNull
	public Link getStatus() { return statusLink; }
	
	public void setStatus(Link statusLink) { this.statusLink = statusLink; }
	
	@NotNull
	public Link getCategory() { return categoryLink; }
	
	public void setCategory(Link categoryLink) { this.categoryLink = categoryLink; }
	
	@NotNull
	@Size(max = 4000)
	public String getDescription() { return description; }
	
	public void setDescription(String description) { this.description = description; }
	
	public Date getDateCreated() { return dateCreated; }
	
	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

	@Override
	public String toString() {
		return "TicketResource [username=" + username
				+ ", statusLink=" + statusLink
				+ ", categoryLink=" + categoryLink
				+ ", description=" + description
				+ ", dateCreated=" + dateCreated
				+ "]";
	}
}
