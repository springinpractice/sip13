package com.springinpractice.ch13.helpdesk.integration.transformer;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.springinpractice.ch13.cdm.Ticket;
import com.springinpractice.ch13.helpdesk.model.TicketEntity;

@Component
public class TicketTransformer {
	@Inject private TicketCategoryTransformer ticketCategoryTransformer;
	@Inject private TicketStatusTransformer ticketStatusTransformer;
	
	public TicketEntity toEntity(Ticket ticketDto) {
		TicketEntity ticketEntity = new TicketEntity();
		ticketEntity.setCategory(ticketCategoryTransformer.toEntity(ticketDto.getCategory()));
		ticketEntity.setCustomerUsername(ticketDto.getCreatedBy().getUsername());
		ticketEntity.setDateCreated(ticketDto.getDateCreated());
		ticketEntity.setDescription(ticketDto.getDescription());
		ticketEntity.setStatus(ticketStatusTransformer.toEntity(ticketDto.getStatus()));
		return ticketEntity;
	}
}
