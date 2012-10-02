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
public class TicketStatus {
	
	// Spring Data REST/JPA wants the JPA annotations to be on the fields, not the methods. Otherwise the fields won't
	// show up in the JSON.
	
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
}
