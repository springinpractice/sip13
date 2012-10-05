package com.springinpractice.ch13.portal.formatter;

import java.text.ParseException;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.springinpractice.ch13.portal.model.TicketCategory;
import com.springinpractice.ch13.portal.repo.TicketCategoryRepository;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Component
public class TicketCategoryFormatter implements Formatter<TicketCategory> {
	private static final Logger log = LoggerFactory.getLogger(TicketCategoryFormatter.class);
	
	@Inject private TicketCategoryRepository ticketCategoryRepo;

	@Override
	public String print(TicketCategory category, Locale locale) {
		log.debug("Printing TicketCategory: {}", category);
		return category.getId().toString();
	}

	@Override
	public TicketCategory parse(String id, Locale locale) throws ParseException {
		log.debug("Parsing ticket category ID: {}", id);
		
		// This fails because our equals() method takes the category name into account.
//		TicketCategory category = new TicketCategory();
//		category.setId(Long.parseLong(id));
//		return category;
		
		// So do this instead.
		return ticketCategoryRepo.findOne(Long.parseLong(id));
	}
}
