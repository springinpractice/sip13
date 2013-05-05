/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.cdm;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TicketCategory {
	private Long id;
	private String key;
	private String name;
	
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id; }
	
	public String getKey() { return key; }
	
	public void setKey(String key) { this.key = key; }
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
	
	@XmlRootElement
	public static class TicketCategoryList {
		private List<TicketCategory> elements;
		
		public TicketCategoryList() { }
		
		public TicketCategoryList(List<TicketCategory> elements) { this.elements = elements; }
		
		@XmlElementWrapper
		@XmlElement(name = "ticketCategory")
		public List<TicketCategory> getElements() { return elements; }
		
		public void setElements(List<TicketCategory> elements) { this.elements = elements; }
	}
}
