/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.cdm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TicketStatus {
	private Long id;
	private String key;
	private String name;
	
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id; }
	
	public String getKey() { return key; }
	
	public void setKey(String key) { this.key = key; }
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
}
