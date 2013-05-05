/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.helpdesk.integration.transformer;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.springinpractice.ch13.cdm.Customer;
import com.springinpractice.ch13.cdm.Ticket;
import com.springinpractice.ch13.helpdesk.model.TicketEntity;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Component
public class TicketTransformer {
	@Inject private TicketCategoryTransformer ticketCategoryTransformer;
	@Inject private TicketStatusTransformer ticketStatusTransformer;
	
	public TicketEntity toEntity(Ticket ticketDto) {
		TicketEntity ticketEntity = new TicketEntity();
		ticketEntity.setCategory(ticketCategoryTransformer.toEntity(ticketDto.getCategory()));
		
		// We do either (1) the username or else (2) the e-mail and full name, but not both.
		Customer customerDto = ticketDto.getCreatedBy();
		String username = customerDto.getUsername();
		if (username != null) {
			ticketEntity.setCustomerUsername(username);
		} else {
			ticketEntity.setCustomerEmail(customerDto.getEmail());
			ticketEntity.setCustomerFullName(getFullName(customerDto));
		}
		
		ticketEntity.setDateCreated(ticketDto.getDateCreated());
		ticketEntity.setDescription(ticketDto.getDescription());
		ticketEntity.setStatus(ticketStatusTransformer.toEntity(ticketDto.getStatus()));
		return ticketEntity;
	}
	
	private String getFullName(Customer customerDto) {
		String firstName = customerDto.getFirstName();
		String lastName = customerDto.getLastName();
		if (firstName == null) {
			return (lastName == null ? "[Unknown]" : lastName).trim();
		} else {
			return (lastName == null ? firstName : firstName + " " + lastName).trim();
		}
	}
}
