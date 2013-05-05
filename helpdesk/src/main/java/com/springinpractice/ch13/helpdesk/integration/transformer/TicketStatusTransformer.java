/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.helpdesk.integration.transformer;

import org.springframework.stereotype.Component;

import com.springinpractice.ch13.cdm.TicketStatus;
import com.springinpractice.ch13.helpdesk.model.TicketStatusEntity;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Component
public class TicketStatusTransformer {
	
	public TicketStatus toDto(TicketStatusEntity statusEntity) {
		TicketStatus statusDto = new TicketStatus();
		statusDto.setId(statusEntity.getId());
		statusDto.setKey(statusEntity.getKey());
		statusDto.setName(statusEntity.getName());
		return statusDto;
	}
	
	public TicketStatusEntity toEntity(TicketStatus statusDto) {
		TicketStatusEntity statusEntity = new TicketStatusEntity();
		statusEntity.setId(statusDto.getId());
		statusEntity.setKey(statusDto.getKey());
		statusEntity.setName(statusDto.getName());
		return statusEntity;
	}
}
