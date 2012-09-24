package com.springinpractice.ch13.helpdesk.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TicketDto {
	private Long id;
	private String userName;
	private String userEmail;
	private String description;
	private Date dateCreated;
	
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id; }
	
	@NotNull
	@Size(min = 1, max = 80)
	public String getUserName() { return userName; }
	
	public void setUserName(String name) { this.userName = name; }
	
	@NotNull
	@Size(min = 1, max = 80)
	@Email
	public String getUserEmail() { return userEmail; }

	public void setUserEmail(String email) { this.userEmail = email; }

	@NotNull
	@Size(min = 1, max = 4000)
	public String getDescription() { return description; }

	public void setDescription(String desc) { this.description = desc; }
	
	public Date getDateCreated() { return dateCreated; }

	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }
}
