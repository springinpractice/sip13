package com.springinpractice.ch13.helpdesk.formatter;

import java.text.ParseException;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.springinpractice.ch13.helpdesk.model.TicketCategory;
import com.springinpractice.ch13.helpdesk.repo.TicketCategoryRepository;

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
		log.debug("Parsing TicketCategory: id={}", id);
		
		// Note that this method needs to parse the ID into a TicketCategory that equals() one of the TicketCategory
		// instances in the dropdown, or else form field prepopulation won't work. Here we're loading the TicketCategory
		// from the repo since the equals() method takes all fields into account. But if the equals() implementation
		// used only the ID, then we'd be able to create an empty TicketCategory with only the ID set and it woulnd work
		// fine.
		return ticketCategoryRepo.findOne(Long.parseLong(id));
	}
}
