/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.portal.integration.gateway.impl;

import static org.springframework.util.Assert.notNull;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.springinpractice.ch13.portal.integration.gateway.TicketGateway;
import com.springinpractice.ch13.portal.integration.resource.TicketCategoryResource;
import com.springinpractice.ch13.portal.integration.resource.TicketCategoryResources;
import com.springinpractice.ch13.portal.integration.resource.TicketResource;
import com.springinpractice.ch13.portal.integration.resource.TicketStatusResource;
import com.springinpractice.ch13.portal.integration.resource.TicketStatusResources;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class TicketGatewayImpl implements TicketGateway {
	private static final Logger log = LoggerFactory.getLogger(TicketGatewayImpl.class);
	
	private RestTemplate restTemplate;
	private String baseUri;
	
	public TicketGatewayImpl(RestTemplate restTemplate, String baseUri) {
		this.restTemplate = restTemplate;
		this.baseUri = baseUri;
	}
	
	@Override
	public void createTicket(TicketResource ticket) {
		notNull(ticket, "ticket can't be null");
		log.info("Creating ticket: {}", ticket);
		String uri = baseUri + "/tickets";
		restTemplate.postForLocation(uri, ticket);
	}
	
	public TicketStatusResource findOpenTicketStatus() {
		String uri = baseUri + "/ticketstatuses/search/find-by-key?key=open";
		TicketStatusResources statuses = restTemplate.getForObject(uri, TicketStatusResources.class);
		return statuses.getContent().iterator().next();
	}
	
	@Override
	public List<TicketCategoryResource> findTicketCategories() {
		String uri = baseUri + "/ticketcategories?sort=name";
		TicketCategoryResources categories = restTemplate.getForObject(uri, TicketCategoryResources.class);
		return new ArrayList<TicketCategoryResource>(categories.getContent());
	}
	
	@Override
	public TicketCategoryResource findTicketCategory(String uri) {
		notNull(uri, "uri can't be null");
		return restTemplate.getForObject(uri, TicketCategoryResource.class);
	}
}
