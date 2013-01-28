package com.springinpractice.ch13.cdm;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Ticket {
	private TicketCategory category;
	private TicketStatus status;
	private String description;
	private String createdBy;
	private Date dateCreated;
	
	public TicketCategory getCategory() { return category; }
	
	public void setCategory(TicketCategory category) { this.category = category; }
	
	public TicketStatus getStatus() { return status; }
	
	public void setStatus(TicketStatus status) { this.status = status; }
	
	public String getDescription() { return description; }
	
	public void setDescription(String description) { this.description = description; }
	
	public String getCreatedBy() { return createdBy; }
	
	public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
	
	public Date getDateCreated() { return dateCreated; }
	
	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }
}
