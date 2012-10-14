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
		
		// Have to break this up into two steps? (Not good: category is @NotNull.) See
		// https://github.com/SpringSource/spring-data-rest/issues/41
		// http://stackoverflow.com/questions/12879975/spring-data-rest-uri-vs-entity-id
		// https://github.com/SpringSource/spring-data-rest/wiki/JPA-Repository-REST-Exporter
		TicketCategoryResource category = ticket.getCategory();
		ticket.setCategory(null);
		
		String uri = baseUri + "/tickets";
		restTemplate.postForLocation(uri, ticket);
		
		// Now post the association per
		// https://github.com/SpringSource/spring-data-rest/wiki/JPA-Repository-REST-Exporter
		// TODO
	}
	
	@Override
	public List<TicketCategoryResource> findTicketCategories() {
		String uri = baseUri + "/ticketcategories?sort=name";
		TicketCategoryResources categories = restTemplate.getForObject(uri, TicketCategoryResources.class);
		return new ArrayList<TicketCategoryResource>(categories.getContent());
	}
	
	@Override
	public TicketCategoryResource findTicketCategory(String uri) {
		notNull(uri, "id can't be null");
		return restTemplate.getForObject(uri, TicketCategoryResource.class);
	}
}
