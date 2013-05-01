package com.springinpractice.ch13.cdm;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Ticket {	
	private TicketCategory category;
	private TicketStatus status;
	private String description;
	private Customer createdBy;
	private Date dateCreated;
	
	@NotNull
	public TicketCategory getCategory() { return category; }
	
	public void setCategory(TicketCategory category) { this.category = category; }
	
	public TicketStatus getStatus() { return status; }
	
	public void setStatus(TicketStatus status) { this.status = status; }
	
	@NotNull
	@Size(min = 1, max = 4000)
	public String getDescription() { return description; }
	
	public void setDescription(String description) { this.description = description; }
	
	@NotNull(groups = { CsrGroup.class })
	public Customer getCreatedBy() { return createdBy; }
	
	public void setCreatedBy(Customer createdBy) { this.createdBy = createdBy; }
	
	public Date getDateCreated() { return dateCreated; }
	
	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }
	
	public static class CustomerGroup { }
	
	public static class CsrGroup { }
}
