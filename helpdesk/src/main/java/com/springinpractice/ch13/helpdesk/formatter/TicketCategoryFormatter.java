/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.helpdesk.formatter;

import java.text.ParseException;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.springinpractice.ch13.helpdesk.model.TicketCategoryEntity;
import com.springinpractice.ch13.helpdesk.repo.TicketCategoryRepository;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Component
public class TicketCategoryFormatter implements Formatter<TicketCategoryEntity> {
	private static final Logger log = LoggerFactory.getLogger(TicketCategoryFormatter.class);
	
	@Inject private TicketCategoryRepository ticketCategoryRepo;

	@Override
	public String print(TicketCategoryEntity category, Locale locale) {
		log.debug("Printing TicketCategoryEntity: {}", category);
		return category.getId().toString();
	}

	@Override
	public TicketCategoryEntity parse(String id, Locale locale) throws ParseException {
		log.debug("Parsing TicketCategoryEntity: id={}", id);
		
		// Note that this method needs to parse the ID into a TicketCategoryEntity that equals() one of the TicketCategoryEntity
		// instances in the dropdown, or else form field prepopulation won't work. Here we're loading the TicketCategoryEntity
		// from the repo since the equals() method takes all fields into account. But if the equals() implementation
		// used only the ID, then we'd be able to create an empty TicketCategoryEntity with only the ID set and it woulnd work
		// fine.
		return ticketCategoryRepo.findOne(Long.parseLong(id));
	}
}
