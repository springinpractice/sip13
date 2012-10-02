package com.springinpractice.ch13.helpdesk.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Entity
@Table(name = "ticket")
public class Ticket {
	
	// Spring Data REST wants the JPA annotations to be on the fields, at least for now. [WLW]
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ticket_status_id")
	private TicketStatus status;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "date_created")
	private Date dateCreated;
	
	public Long getId() { return id; }
	
	@SuppressWarnings("unused")
	private void setId(Long id) { this.id = id; }
	
	public TicketStatus getStatus() { return status; }
	
	public void setStatus(TicketStatus status) { this.status = status; }

	public String getUserName() { return userName; }
	
	public void setUserName(String name) { this.userName = name; }

	public String getUserEmail() { return userEmail; }

	public void setUserEmail(String email) { this.userEmail = email; }
	
	public String getDescription() { return description; }

	public void setDescription(String desc) { this.description = desc; }
	
	public Date getDateCreated() { return dateCreated; }

	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }
}
