/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.helpdesk.integration.transformer;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;

import com.springinpractice.ch13.cdm.Customer;
import com.springinpractice.ch13.cdm.Ticket;
import com.springinpractice.ch13.cdm.TicketCategory;
import com.springinpractice.ch13.cdm.TicketStatus;
import com.springinpractice.ch13.helpdesk.model.TicketCategoryEntity;
import com.springinpractice.ch13.helpdesk.model.TicketEntity;
import com.springinpractice.ch13.helpdesk.model.TicketStatusEntity;
import com.springinpractice.ch13.helpdesk.repo.TicketCategoryRepository;
import com.springinpractice.ch13.helpdesk.repo.TicketStatusRepository;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Component
public class TicketTransformer {
	@Inject private TicketCategoryRepository ticketCategoryRepo;
	@Inject private TicketStatusRepository ticketStatusRepo;
	
	@Inject private TicketCategoryTransformer ticketCategoryTransformer;
	@Inject private TicketStatusTransformer ticketStatusTransformer;
	
	private TicketCategory generalCategoryDto;
	private TicketStatus openStatusDto;
	
	@PostConstruct
	public void postConstruct() {
		TicketCategoryEntity generalCategoryEntity = ticketCategoryRepo.findByKey("general");
		this.generalCategoryDto = ticketCategoryTransformer.toDto(generalCategoryEntity);
		
		TicketStatusEntity openStatusEntity = ticketStatusRepo.findByKey("open");
		this.openStatusDto = ticketStatusTransformer.toDto(openStatusEntity);
	}
	
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
	
	// Recipe 13.4
	public Ticket toDto(MimeMessage email) throws MessagingException, IOException {
		InternetAddress from = (InternetAddress) email.getFrom()[0];
		
		// FIXME This may or may not be a MimeMultipart
		MimeMultipart content = (MimeMultipart) email.getContent();
		BodyPart body = content.getBodyPart(0);
		
		Ticket ticketDto = new Ticket();
		ticketDto.setCategory(generalCategoryDto);
		
		// We don't really know whether the personal name is first, last or what, so just use the last name for now.
		Customer customerDto = new Customer();
		customerDto.setEmail(from.getAddress());
		customerDto.setFirstName(null);
		customerDto.setLastName(from.getPersonal());
		ticketDto.setCreatedBy(customerDto);
		
		ticketDto.setDateCreated(email.getSentDate());
		ticketDto.setDescription("[" + email.getSubject() + "] " + body.getContent());
		ticketDto.setStatus(openStatusDto);
		return ticketDto;
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
