package com.springinpractice.ch13.helpdesk.integration.transformer;

import org.springframework.stereotype.Component;

import com.springinpractice.ch13.cdm.TicketCategory;
import com.springinpractice.ch13.helpdesk.model.TicketCategoryEntity;

@Component
public class TicketCategoryTransformer {
	
	public TicketCategory toDto(TicketCategoryEntity categoryEntity) {
		TicketCategory categoryDto = new TicketCategory();
		categoryDto.setId(categoryEntity.getId());
		categoryDto.setKey(categoryEntity.getKey());
		categoryDto.setName(categoryEntity.getName());
		return categoryDto;
	}
	
	public TicketCategoryEntity toEntity(TicketCategory categoryDto) {
		TicketCategoryEntity categoryEntity = new TicketCategoryEntity();
		categoryEntity.setId(categoryDto.getId());
		categoryEntity.setKey(categoryDto.getKey());
		categoryEntity.setName(categoryDto.getName());
		return categoryEntity;
	}
}
