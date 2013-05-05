/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.helpdesk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Entity
@Table(name = "ticket_status")
public class TicketStatusEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "ukey")
	private String key;
	
	@Column(name = "name")
	private String name;
	
	public Long getId() { return id; }
	
	@SuppressWarnings("unused")
	private void setId(Long id) { this.id = id; }
	
	public String getKey() { return key; }
	
	public void setKey(String key) { this.key = key; }
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		
		TicketStatusEntity other = (TicketStatusEntity) obj;
		
		if (id == null) {
			if (other.id != null) { return false; }
		} else if (!id.equals(other.id)) {
			return false;
		}
		
		if (key == null) {
			if (other.key != null) { return false; }
		} else if (!key.equals(other.key)) {
			return false;
		}
		
		if (name == null) {
			if (other.name != null) { return false; }
		} else if (!name.equals(other.name)) {
			return false;
		}
		
		return true;
	}
}
