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

import com.springinpractice.ch13.cdm.Ticket;
import com.springinpractice.ch13.cdm.TicketCategory;
import com.springinpractice.ch13.cdm.TicketStatus;
import com.springinpractice.ch13.helpdesk.model.TicketCategoryEntity;
import com.springinpractice.ch13.helpdesk.model.TicketEntity;
import com.springinpractice.ch13.helpdesk.model.TicketStatusEntity;
import com.springinpractice.ch13.helpdesk.repo.TicketCategoryRepository;
import com.springinpractice.ch13.helpdesk.repo.TicketStatusRepository;

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
		ticketEntity.setCustomerUsername(ticketDto.getCreatedBy());
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
		ticketDto.setDateCreated(email.getSentDate());
		ticketDto.setDescription(
				"From: " + from.getPersonal() + " <" + from.getAddress() + ">" +
				"\nSubject: " + email.getSubject() +
				"\n\n" + body.getContent());
		ticketDto.setStatus(openStatusDto);
		return ticketDto;
	}
}
