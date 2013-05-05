/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.portal.formatter;

import java.text.ParseException;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.springinpractice.ch13.portal.integration.gateway.TicketGateway;
import com.springinpractice.ch13.portal.integration.resource.TicketCategoryResource;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Component
public class TicketCategoryFormatter implements Formatter<TicketCategoryResource> {
	private static final Logger log = LoggerFactory.getLogger(LinkFormatter.class);
	
	@Inject private TicketGateway ticketGateway;
	
	@Override
	public String print(TicketCategoryResource category, Locale locale) {
		log.debug("Printing TicketCategory: {}", category);
		return category.getId().toString();
	}

	@Override
	public TicketCategoryResource parse(String uri, Locale locale) throws ParseException {
		log.debug("Parsing TicketCategory: uri={}", uri);
		return ticketGateway.findTicketCategory(uri);
	}
}
