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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Entity
@Table(name = "ticket")
public class TicketEntity {
	private static final Logger log = LoggerFactory.getLogger(TicketEntity.class);
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ticket_status_id")
	private TicketStatusEntity status;
	
	@Column(name = "customer_username")
	private String customerUsername;
	
	@ManyToOne
	@JoinColumn(name = "ticket_category_id")
	private TicketCategoryEntity category;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "date_created")
	private Date dateCreated;
	
	public Long getId() { return id; }
	
	@SuppressWarnings("unused")
	private void setId(Long id) { this.id = id; }
	
	public TicketStatusEntity getStatus() { return status; }
	
	public void setStatus(TicketStatusEntity status) { this.status = status; }

//	@NotNull
	@Size(min = 1, max = 20)
	public String getCustomerUsername() { return customerUsername; }
	
	public void setCustomerUsername(String customerUsername) {
		log.debug("Setting customerUsername={}", customerUsername);
		this.customerUsername = customerUsername;
	}
	
//	@NotNull
	public TicketCategoryEntity getCategory() { return category; }
	
	public void setCategory(TicketCategoryEntity category) { this.category = category; }
	
	@NotNull
	@Size(min = 1, max = 4000)
	public String getDescription() { return description; }

	public void setDescription(String desc) { this.description = desc; }
	
	public Date getDateCreated() { return dateCreated; }

	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

	@Override
	public int hashCode() {
		final int prime = 17;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((customerUsername == null) ? 0 : customerUsername.hashCode());
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		
		TicketEntity other = (TicketEntity) obj;
		
		if (category == null) {
			if (other.category != null) { return false; }
		} else if (!category.equals(other.category)) {
			return false;
		}
		
		if (customerUsername == null) {
			if (other.customerUsername != null) { return false; }
		} else if (!customerUsername.equals(other.customerUsername)) {
			return false;
		}
		
		if (dateCreated == null) {
			if (other.dateCreated != null) { return false; }
		} else if (!dateCreated.equals(other.dateCreated)) {
			return false;
		}
		
		if (description == null) {
			if (other.description != null) { return false; }
		} else if (!description.equals(other.description)) {
			return false;
		}
		
		if (id == null) {
			if (other.id != null) { return false; }
		} else if (!id.equals(other.id)) {
			return false;
		}
		
		if (status == null) {
			if (other.status != null) { return false; }
		} else if (!status.equals(other.status)) {
			return false;
		}
		
		return true;
	}
}
