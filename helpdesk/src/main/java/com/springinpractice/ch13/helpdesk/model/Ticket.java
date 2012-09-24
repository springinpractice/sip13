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
 * TicketDto domain object.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Entity
@Table(name = "ticket")
public class Ticket {
	private Long id;
	private TicketStatus status;
	private String userName;
	private String userEmail;
	private String description;
	private Date dateCreated;
	
	/**
	 * @return ID
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() { return id; }
	
	/**
	 * @param id ID
	 */
	@SuppressWarnings("unused")
	private void setId(Long id) { this.id = id; }
	
	@ManyToOne
	@JoinColumn(name = "ticket_status_id")
	public TicketStatus getStatus() { return status; }
	
	public void setStatus(TicketStatus status) { this.status = status; }

	/**
	 * @return user name
	 */
	@Column(name = "user_name")
	public String getUserName() { return userName; }
	
	/**
	 * @param name user name
	 */
	public void setUserName(String name) { this.userName = name; }

	/**
	 * @return user e-mail address
	 */
	@Column(name = "user_email")
	public String getUserEmail() { return userEmail; }

	/**
	 * @param email user e-mail address
	 */
	public void setUserEmail(String email) { this.userEmail = email; }
	
	/**
	 * @return description
	 */
	@Column(name = "description")
	public String getDescription() { return description; }

	/**
	 * @param desc description
	 */
	public void setDescription(String desc) { this.description = desc; }
	
	/**
	 * @return date created
	 */
	@Column(name = "date_created")
	public Date getDateCreated() { return dateCreated; }

	/**
	 * @param dateCreated date created
	 */
	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[TicketDto: userName=" + userName
			+ ", userEmail=" + userEmail
			+ ", description=" + description
			+ ", dateCreated=" + dateCreated
			+ "]";
	}
}
